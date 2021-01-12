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
        Monster monster = new Monster("Cat",12);
        assertTrue(monster instanceof Monster);
    }

    @Test
    public void getName_instantiatesObjectWithAName_string(){
        Monster monster = new Monster("Cat",12);
        assertEquals("Cat",monster.getName());
    }

    @Test
    public void getPersonId_instantiatesObjectWithAPersonId_integer(){
        Monster monster = new Monster("Cat",12);
        assertEquals(12,monster.getPersonId());
    }


}