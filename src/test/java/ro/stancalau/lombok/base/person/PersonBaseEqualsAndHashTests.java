package ro.stancalau.lombok.base.person;

import org.junit.Before;
import org.junit.Test;
import ro.stancalau.lombok.api.ImmutablePerson;
import ro.stancalau.lombok.factory.PersonCreator;

import static org.junit.Assert.*;

public abstract class PersonBaseEqualsAndHashTests<T extends ImmutablePerson> extends PersonCreator<T> {

    private T person;
    private T samePerson;
    private T sameClone;
    private T stranger;

    public PersonBaseEqualsAndHashTests(Class<T> clazz) {
        super(clazz);
    }

    @Before
    public void setUp() throws Exception {
        person = create();
        samePerson = create();
        sameClone = create();
        stranger = create("Stranger");
    }

    @Test
    public void testNotEqualsToNull() throws Exception {
        assertFalse(person.equals(null));
    }

    @Test
    public void testReflexive() throws Exception {
        assertTrue(person.equals(person));
    }

    @Test
    public void testSymmetric() throws Exception {
        assertFalse(person.equals(stranger));
        assertFalse(stranger.equals(person));

        assertTrue(person.equals(samePerson));
        assertTrue(samePerson.equals(person));
    }

    @Test
    public void testTransitive() throws Exception {
        assertTrue(person.equals(samePerson));
        assertTrue(samePerson.equals(sameClone));
        assertTrue(sameClone.equals(person));
    }

    @Test
    public void givenPersonsWithSameStateWhenCompareHashCodesThenAreEqual() throws Exception {
        assertEquals(person.hashCode(), samePerson.hashCode());
    }
}