package components.monsters;

public class Monster {

    private String name;
    private int personId;

    public Monster(String name,int personId){
        this.name     = name;
        this.personId = personId;
    }

    public String getName(){
        return this.name;
    }

    public int getPersonId(){
        return this.personId;
    }
}
