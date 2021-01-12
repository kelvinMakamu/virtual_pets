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
}