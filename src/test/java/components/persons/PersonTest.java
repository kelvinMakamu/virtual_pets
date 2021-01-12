package components.persons;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PersonTest {

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

    // HELPERS
    public Person setUpNewPerson(){
        return new Person("Henry","[email protected]");
    }

}