package components.persons;

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