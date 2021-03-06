package ro.stancalau.lombok.tests.person;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ro.stancalau.lombok.api.ImmutablePerson;
import ro.stancalau.lombok.tests.base.PersonBaseTest;
import ro.stancalau.lombok.tests.base.TestParam;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class BasicPersonEqualsAndHashTests<T extends ImmutablePerson> extends PersonBaseTest<T> {

    private ImmutablePerson person;
    private ImmutablePerson samePerson;
    private ImmutablePerson sameClone;
    private ImmutablePerson stranger;

    @SuppressWarnings("unchecked")
    public BasicPersonEqualsAndHashTests(TestParam param) {
        super((Class<T>) param.getClassUnderTest());
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
