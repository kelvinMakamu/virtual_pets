package components.monsters;

import components.data.Database;
import org.sql2o.Connection;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class Monster {

    private int id;
    private String name;
    private int personId;
    private int foodLevel;
    private int sleepLevel;
    private int playLevel;
    // TIMESTAMPS
    private Timestamp birthday;
    private Timestamp lastSlept;
    private Timestamp lastAte;
    private Timestamp lastPlayed;
    private Timer timer;
    // CONSTANTS
    public static final int MAX_FOOD_LEVEL  = 3;
    public static final int MAX_SLEEP_LEVEL = 8;
    public static final int MAX_PLAY_LEVEL  = 12;
    public static final int MIN_ALL_LEVELS  = 0;

    public Monster(String name,int personId){
        this.name       = name;
        this.personId   = personId;
        this.playLevel  = MAX_PLAY_LEVEL/2;
        this.foodLevel  = MAX_FOOD_LEVEL/2;
        this.sleepLevel = MAX_SLEEP_LEVEL/2;
        this.timer      = new Timer();
    }

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public int getPersonId(){
        return this.personId;
    }

    public int getPlayLevel(){
        return this.playLevel;
    }

    public int getFoodLevel(){
        return this.foodLevel;
    }

    public int getSleepLevel(){
        return this.sleepLevel;
    }
    
    public Timestamp getBirthday(){
        return this.birthday;
    }
    
    public Timestamp getLastSlept(){
        return this.lastSlept;
    }

    public Timestamp getLastAte(){
        return this.lastAte;
    }
    
    public Timestamp getLastPlayed(){
        return this.lastPlayed;
    }

    public boolean isAlive(){
        return !(playLevel <= MIN_ALL_LEVELS || foodLevel <= MIN_ALL_LEVELS || sleepLevel <=MIN_ALL_LEVELS);
    }

    public void play(){
        if (this.playLevel >= MAX_PLAY_LEVEL){
            throw new UnsupportedOperationException("The monster cannot play anymore!");
        }
        String query = "UPDATE monsters SET lastPlayed=now() WHERE id=:id";
        try(Connection connection = Database.sql2o.open()){
            connection.createQuery(query)
                    .addParameter("id",this.id)
                    .executeUpdate();
        }
        this.playLevel++;
    }

    public void sleep(){
        if (this.sleepLevel >= MAX_SLEEP_LEVEL){
            throw new UnsupportedOperationException("The monster cannot sleep any longer!");
        }
        String query = "UPDATE monsters SET lastSlept=now() WHERE id=:id";
        try(Connection connection = Database.sql2o.open()){
            connection.createQuery(query)
                    .addParameter("id",this.id)
                    .executeUpdate();
        }
        this.sleepLevel++;
    }

    public void feed(){
        if (this.foodLevel >= MAX_FOOD_LEVEL){
            throw new UnsupportedOperationException("You cannot feed your monster anymore!");
        }
        String query = "UPDATE monsters SET lastAte=now() WHERE id=:id";
        try(Connection connection = Database.sql2o.open()){
            connection.createQuery(query)
                    .addParameter("id",this.id)
                    .executeUpdate();
        }
        this.foodLevel++;
    }

    public void depleteLevels(){
        if(isAlive()){
            this.playLevel--;
            this.sleepLevel--;
            this.foodLevel--;
        }
    }

    public void save(){
        String query = "INSERT INTO monsters(name,personId,birthday) VALUES(:name,:personId,now())";
        try(Connection connection = Database.sql2o.open()){
            this.id = (int)connection.createQuery(query,true)
                        .addParameter("name",this.name)
                        .addParameter("personId",this.personId)
                        .executeUpdate()
                        .getKey();
        }
    }

    public static List<Monster> all(){
        String query = "SELECT * FROM monsters";
        try(Connection connection = Database.sql2o.open()){
            return connection.createQuery(query).executeAndFetch(Monster.class);
        }
    }

    public static Monster findById(int monsterId){
        String query = "SELECT * FROM monsters WHERE id=:id";
        try(Connection connection = Database.sql2o.open()){
            return connection.createQuery(query)
                    .addParameter("id",monsterId)
                    .executeAndFetchFirst(Monster.class);
        }
    }

    public void startTimer(){
        Monster currentMonster = this;
        TimerTask timerTask    = new TimerTask(){
            @Override
            public void run() {
                if (!currentMonster.isAlive()){
                    cancel();
                }
                depleteLevels();
            }
        };
        this.timer.schedule(timerTask, 0, 600);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Monster)) return false;
        Monster monster = (Monster) o;
        return getPersonId() == monster.getPersonId() && getName().equals(monster.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPersonId());
    }
}
