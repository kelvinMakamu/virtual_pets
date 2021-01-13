package components.monsters;

import components.data.Database;
import org.sql2o.Connection;

import java.util.List;
import java.util.Objects;

public class Monster {

    private int id;
    private String name;
    private int personId;
    private int foodLevel;
    private int sleepLevel;
    private int playLevel;
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

    public boolean isAlive(){
        return !(playLevel <= MIN_ALL_LEVELS || foodLevel <= MIN_ALL_LEVELS || sleepLevel <=MIN_ALL_LEVELS);
    }

    public void play(){
        this.playLevel++;
    }

    public void save(){
        String query = "INSERT INTO monsters(name,personId) VALUES(:name,:personId)";
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
