package components.monsters;

import java.util.Objects;

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
