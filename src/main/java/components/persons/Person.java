package components.persons;

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

}