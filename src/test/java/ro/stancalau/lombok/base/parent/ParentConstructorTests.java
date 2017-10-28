package ro.stancalau.lombok.base.parent;

import org.junit.Test;
import ro.stancalau.lombok.api.ImmutableParent;
import ro.stancalau.lombok.api.MutablePerson;
import ro.stancalau.lombok.factory.ParentCreator;

import java.util.Set;

import static org.junit.Assert.assertEquals;

public abstract class ParentConstructorTests<T extends ImmutableParent> extends ParentCreator<T> {

    public ParentConstructorTests(Class<T> clazz) {
        super(clazz);
    }

    @Test
    public void givenValuesWhenConstructingParentThenGettersReturnOriginalValues() throws Exception {
        //when
        T mutableParent = create(NAME, globalChildren);

        //then
        assertEquals(NAME, mutableParent.getName());
        assertEquals(2, mutableParent.getChildren().size());
        assertEquals(globalChildren, mutableParent.getChildren());
    }

    @Test
    public void givenParentWhenMutatingGlobalSetThenParentChildrenDoNotChange() throws Exception {
        //given
        T mutableParent = create(NAME, globalChildren);

        //when
        globalChildren.add(ILLEGITIMATE_CHILD);

        //then
        assertEquals(2, mutableParent.getChildren().size());
    }

    @Test(expected = NullPointerException.class)
    public void givenNullChildrenSetWhenConstructingThenNullPointerExceptionExpected() throws Exception {
        //given
        Set<MutablePerson> children = null;

        //when
        create(NAME, children);
    }

    @Test(expected = NullPointerException.class)
    public void givenNullNameConstructingThenNullPointerExceptionExpected() throws Exception {
        //given
        String name = null;

        //when
        create(name, globalChildren);
    }

    @Test
    public void givenOnlyNameWhenConstructingThenChildrenSetSizeIsZero() throws Exception {
        //when
        T mutableParent = create(NAME);

        //then
        assertEquals(0, mutableParent.getChildren().size());
    }

    @Test
    public void givenNoParamsWhenConstructThenChildrenSetSizeIsZero() throws Exception {
        //when
        T mutableParent = create();

        //then
        assertEquals(0, mutableParent.getChildren().size());
    }
}
