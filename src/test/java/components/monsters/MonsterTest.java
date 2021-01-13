package components.monsters;

import components.data.DatabaseRule;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class MonsterTest {

    @Rule
    public DatabaseRule databaseRule = new DatabaseRule();

    @Test
    public void Monster_instantiatesCorrectly_true(){
        Monster monster = setUpNewMonster();
        assertTrue(monster instanceof Monster);
    }

    @Test
    public void getName_instantiatesObjectWithAName_string(){
        Monster monster = setUpNewMonster();
        assertEquals("Cat",monster.getName());
    }

    @Test
    public void getPersonId_instantiatesObjectWithAPersonId_integer(){
        Monster monster = setUpNewMonster();
        assertEquals(12,monster.getPersonId());
    }

    @Test
    public void equals_comparesTwoObjects_true(){
        Monster firstMonster  = setUpNewMonster();
        Monster secondMonster = setUpNewMonster();
        assertTrue(firstMonster.equals(secondMonster));
    }

    @Test
    public void save_insertMonsterObjectIntoDatabase(){
        Monster firstMonster  = setUpNewMonster();
        firstMonster.save();
        assertEquals(Monster.all().get(0).equals(firstMonster));
    }

    public Monster setUpNewMonster(){
        return new Monster("Cat",12);
    }


}