package components.persons;

import components.data.DatabaseRule;
import org.junit.Rule;
import org.junit.Test;

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
        assertEquals("[email protected]",person.getEmail());
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
    public void all_returnsPersonInstances_true(){
        Person firstPerson  = setUpNewPerson();
        firstPerson.save();
        Person secondPerson  = new Person("Gerald","gerald@gerald.com");
        secondPerson.save();
        assertEquals(true,Person.all().get(0).equals(firstPerson));
        assertEquals(true,Person.all().get(1).equals(secondPerson));
    }

    // HELPERS
    public Person setUpNewPerson(){
        return new Person("Henry","henry@henry.com");
    }

}