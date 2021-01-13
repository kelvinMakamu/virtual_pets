package components.monsters;

import components.data.Database;
import org.sql2o.Connection;

import java.util.List;
import java.util.Objects;

public class Monster {

    private int id;
    private String name;
    private int personId;

    public Monster(String name,int personId){
        this.name     = name;
        this.personId = personId;
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
