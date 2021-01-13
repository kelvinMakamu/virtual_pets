package components.monsters;

import components.data.DatabaseRule;
import components.persons.Person;
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
        assertEquals(true,Monster.all().get(0).equals(firstMonster));
    }

    @Test
    public void save_assignsIdToSavedObject(){
        Monster firstMonster  = setUpNewMonster();
        firstMonster.save();
        Monster savedMonster  = Monster.all().get(0);
        assertEquals(firstMonster.getId(),savedMonster.getId());
    }

    @Test
    public void all_returnsAllInstancesOfMonster_true() {
        Monster firstMonster = setUpNewMonster();
        firstMonster.save();
        Monster secondMonster = new Monster("Spud", 1);
        secondMonster.save();
        assertEquals(true, Monster.all().get(0).equals(firstMonster));
        assertEquals(true, Monster.all().get(1).equals(secondMonster));
    }

    @Test
    public void findById_returnsMonsterWithAnId_second(){
        Monster firstMonster = setUpNewMonster();
        firstMonster.save();
        Monster secondMonster = new Monster("Spud", 1);
        secondMonster.save();
        assertEquals(Monster.findById(secondMonster.getId()),secondMonster);
    }

    public Monster setUpNewMonster(){
        return new Monster("Cat",12);
    }


}