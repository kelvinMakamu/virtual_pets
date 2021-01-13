package components.persons;

import components.data.DatabaseRule;
import components.monsters.Monster;
import org.junit.Rule;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PersonTest {

    @Rule
    public DatabaseRule databaseRule = new DatabaseRule();

    @Test
    public void Person_instantiatesCorrectly_true(){
        Person person = setUpNewPerson();
        assertEquals(true, person instanceof Person);
    }

    @Test
    public void getName_gettingNameOfInstantiatedPerson_string(){
        Person person = setUpNewPerson();
        assertEquals("Henry",person.getName());
    }

    @Test
    public void getEmail_gettingEmailOfInstantiatedPerson_string(){
        Person person = setUpNewPerson();
        assertEquals("henry@henry.com",person.getEmail());
    }

    @Test
    public void equals_comparesTwoPersonObjects_true(){
        Person firstPerson  = setUpNewPerson();
        Person secondPerson = setUpNewPerson();
        assertTrue(firstPerson.equals(secondPerson));
    }

    @Test
    public void save_insertPersonObjectIntoDatabase(){
        Person person  = setUpNewPerson();
        person.save();
        assertTrue(Person.all().get(0).equals(person));
    }

    @Test
    public void save_assignsIdToObject(){
        Person person  = setUpNewPerson();
        person.save();
        Person savedPerson = Person.all().get(0);
        assertEquals(person.getId(),savedPerson.getId());
    }

    @Test
    public void findById_returnsPersonWithAnId_second(){
        Person person        = setUpNewPerson();
        person.save();
        Person secondPerson  = new Person("Gerald","gerald@gerald.com");
        secondPerson.save();
        assertEquals(Person.findById(secondPerson.getId()),secondPerson);
    }

    @Test
    public void all_returnsPersonInstances_true(){
        Person firstPerson  = setUpNewPerson();
        firstPerson.save();
        Person secondPerson  = new Person("Gerald","gerald@gerald.com");
        secondPerson.save();
        assertEquals(true,Person.all().get(0).equals(firstPerson));
        assertEquals(true,Person.all().get(1).equals(secondPerson));
    }

    @Test
    public void getMonsters_returnsAllMonstersAssoaciatedToAPerson_monsterList(){
        Person testPerson     = setUpNewPerson();
        testPerson.save();
        Monster firstMonster  = new Monster("Cat",testPerson.getId());
        firstMonster.save();
        Monster secondMonster = new Monster("Dog",testPerson.getId());
        secondMonster.save();
        Monster [] monsters   = new Monster[] {firstMonster,secondMonster};
        assertTrue(Person.getMonsters().containsAll(Arrays.asList(monsters)));
    }

    // HELPERS
    public Person setUpNewPerson(){
        return new Person("Henry","henry@henry.com");
    }

}