package components.persons;

import components.data.Database;
import components.monsters.Monster;
import org.sql2o.Connection;

import java.util.List;
import java.util.Objects;

public class Person {

    private int id;
    private String name;
    private String email;

    public Person(String name, String email) {
        this.name  = name;
        this.email = email;
    }

    public String getName(){
        return this.name;
    }

    public String getEmail(){
        return this.email;
    }

    public int getId(){
        return this.id;
    }

    public static List<Person> all(){
        String query = "SELECT * FROM persons";
        try(Connection connection = Database.sql2o.open()){
            return connection.createQuery(query)
                             .executeAndFetch(Person.class);
        }
    }

    public void save(){
        String query = "INSERT INTO persons(name,email) VALUES(:name,:email)";
        try(Connection connection = Database.sql2o.open()){
            this.id = (int) connection.createQuery(query,true)
                    .addParameter("name",this.name)
                    .addParameter("email",this.email)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static Person findById(int personId){
        String query = "SELECT * FROM persons WHERE id=:id";
        try(Connection connection = Database.sql2o.open()){
            return connection.createQuery(query)
                    .addParameter("id",personId)
                    .executeAndFetchFirst(Person.class);
        }
    }

    public List<Monster> getMonsters(){
        String query = "SELECT * FROM monsters WHERE personId=:id";
        try(Connection connection = Database.sql2o.open()){
            return connection.createQuery(query)
                    .addParameter("id",this.id)
                    .executeAndFetch(Monster.class);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return id == person.id && getName().equals(person.getName()) && getEmail().equals(person.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getName(), getEmail());
    }
}