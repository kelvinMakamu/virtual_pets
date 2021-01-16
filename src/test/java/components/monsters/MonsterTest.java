package components.monsters;

import components.data.DatabaseRule;
import components.persons.Person;
import org.junit.Rule;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MonsterTest {

    @Rule
    public DatabaseRule databaseRule = new DatabaseRule();

    @Test
    public void Monster_instantiatesCorrectly_true(){
        Monster monster = setUpNewMonster();
        assertTrue(monster instanceof Monster);
    }

    @Test
    public void Monster_instantiatesWithHalfPlayLevel(){
        Monster monster = setUpNewMonster();
        assertEquals(monster.getPlayLevel(),(Monster.MAX_PLAY_LEVEL/2));
    }

    @Test
    public void Monster_instantiatesWithHalfFoodLevel(){
        Monster monster = setUpNewMonster();
        assertEquals(monster.getFoodLevel(),(Monster.MAX_FOOD_LEVEL/2));
    }

    @Test
    public void Monster_instantiatesWithHalfSleepLevel(){
        Monster monster = setUpNewMonster();
        assertEquals(monster.getSleepLevel(),(Monster.MAX_SLEEP_LEVEL/2));
    }

    @Test
    public void isAlive_confirmsIfMonsterIsAliveIfAllLevelsIsAboveMinimum_true(){
        Monster monster = setUpNewMonster();
        assertTrue(monster.isAlive());
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

    @Test
    public void save_savesPersonIdIntoDB_true(){
        Person testPerson    = new Person("Henry","henry@henry.com");
        testPerson.save();
        Monster testMonster  = new Monster("Catway", testPerson.getId());
        testMonster.save();
        Monster savedMonster = Monster.findById(testMonster.getId());
        assertEquals(savedMonster.getPersonId(),testPerson.getId());
    }

    @Test
    public void depleteLevels_reduceAllLevels(){
        Monster testMonster = setUpNewMonster();
        testMonster.depleteLevels();
        assertEquals(testMonster.getFoodLevel(), (Monster.MAX_FOOD_LEVEL / 2) - 1);
        assertEquals(testMonster.getSleepLevel(), (Monster.MAX_SLEEP_LEVEL / 2) - 1);
        assertEquals(testMonster.getPlayLevel(), (Monster.MAX_PLAY_LEVEL / 2) - 1);
    }

    @Test
    public void play_increasesMonsterPlayLevel(){
        Monster monster = setUpNewMonster();
        monster.play();
        assertTrue(monster.getPlayLevel() > (Monster.MAX_PLAY_LEVEL/2));
    }

    @Test
    public void sleep_increasesMonsterSleepLevel(){
        Monster monster = setUpNewMonster();
        monster.sleep();
        assertTrue(monster.getSleepLevel() > (Monster.MAX_SLEEP_LEVEL/2));
    }

    @Test
    public void feed_increasesMonsterFoodLevel(){
        Monster monster = setUpNewMonster();
        monster.feed();
        assertTrue(monster.getFoodLevel() > (Monster.MAX_FOOD_LEVEL/2));
    }

    @Test
    public void monster_foodLevelCannotGoBeyondMaxValue(){
        Monster testMonster = new Monster("Bubbles", 1);
        for(int i = Monster.MIN_ALL_LEVELS; i <= (Monster.MAX_FOOD_LEVEL + 2); i++){
            try {
                testMonster.feed();
            }catch(UnsupportedOperationException exception){}
        }
        assertTrue(testMonster.getFoodLevel() <= Monster.MAX_FOOD_LEVEL);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void feed_throwsExceptionIfFoodLevelIsAtMaxValue(){
        Monster testMonster = new Monster("Bubbles", 1);
        for(int i = Monster.MIN_ALL_LEVELS; i <= (Monster.MAX_FOOD_LEVEL); i++){
            testMonster.feed();
        }
    }

    @Test
    public void monster_sleepLevelCannotGoBeyondMaxValue(){
        Monster testMonster = new Monster("Bubbles", 1);
        for(int i = Monster.MIN_ALL_LEVELS; i <= (Monster.MAX_SLEEP_LEVEL + 2); i++){
            try {
                testMonster.sleep();
            }catch(UnsupportedOperationException exception){}
        }
        assertTrue(testMonster.getFoodLevel() <= Monster.MAX_SLEEP_LEVEL);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void sleep_throwsExceptionIfSleepLevelIsAtMaxValue(){
        Monster testMonster = new Monster("Bubbles", 1);
        for(int i = Monster.MIN_ALL_LEVELS; i <= (Monster.MAX_SLEEP_LEVEL); i++){
            testMonster.sleep();
        }
    }

    @Test
    public void monster_playLevelCannotGoBeyondMaxValue(){
        Monster testMonster = new Monster("Bubbles", 1);
        for(int i = Monster.MIN_ALL_LEVELS; i <= (Monster.MAX_PLAY_LEVEL + 2); i++){
            try {
                testMonster.play();
            }catch(UnsupportedOperationException exception){}
        }
        assertTrue(testMonster.getFoodLevel() <= Monster.MAX_PLAY_LEVEL);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void play_throwsExceptionIfFoodLevelIsAtMaxValue(){
        Monster testMonster = new Monster("Bubbles", 1);
        for(int i = Monster.MIN_ALL_LEVELS; i <= (Monster.MAX_PLAY_LEVEL); i++){
            testMonster.play();
        }
    }

    @Test
    public void save_recordsTimeOfCreationOfMonsterObject(){
        Monster monster = setUpNewMonster();
        monster.save();
        Timestamp birthday = Monster.findById(monster.getId()).getBirthday();
        Timestamp rightNow = new Timestamp(new Date().getTime());
        assertEquals(birthday,rightNow);
    }

    public Monster setUpNewMonster(){
        return new Monster("Cat",12);
    }


}