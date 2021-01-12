package components.persons;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

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

    // HELPERS
    public Person setUpNewPerson(){
        return new Person("Henry","[email protected]");
    }

}