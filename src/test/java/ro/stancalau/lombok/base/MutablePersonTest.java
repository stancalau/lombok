package ro.stancalau.lombok.base;

import org.junit.Test;
import ro.stancalau.lombok.api.MutablePerson;

import java.lang.reflect.Modifier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public abstract class MutablePersonTest<T extends MutablePerson> implements PersonFactory<T> {

    protected static final String NAME = "Joe";
    protected static final String NEW_NAME = "Jerome";

    @Test
    public void givenPersonConstructedWithNoArgsWhenGetNameThenItIsDefault() throws Exception {
        //given
        T person = createPerson();

        //when
        String name = person.getName();

        //then
        assertEquals(MutablePerson.DEFAULT_NAME, name);
    }

    @Test
    public void givenPersonWhenSetNameThenGetUpdatedName() throws Exception {
        //given
        T person = createPerson(NAME);

        //when
        person.setName(NEW_NAME);

        //then
        assertEquals(NEW_NAME, person.getName());
    }

    @Test(expected = NullPointerException.class)
    public void givenNullNameConstructingThenNullPointerExceptionExpected() throws Exception {
        //given
        String name = null;

        //when
        createPerson(name);
    }

    @Test
    public void givenPersonWhenWhenCheckingModifiersOfNameFieldThenFieldIsDeclaredPrivate() throws Exception {
        //given
        T person = createPerson(NAME);

        //when
        int nameModifiers = person.getClass().getDeclaredField("name").getModifiers();

        //then
        assertTrue(Modifier.isPrivate(nameModifiers));
    }
}