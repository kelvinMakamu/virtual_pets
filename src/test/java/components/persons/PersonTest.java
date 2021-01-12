package components.persons;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PersonTest {

    @Test
    public void Person_instantiatesCorrectly_true(){
        Person person = new Person("Henry","[email protected]");
        assertEquals(true, person instanceof Person);
    }

    @Test
    public void getName_gettingNameOfInstantiatedPerson_string(){
        Person person = new Person("Henry","[email protected]");
        assertEquals("Henry",person.getName());
    }


}