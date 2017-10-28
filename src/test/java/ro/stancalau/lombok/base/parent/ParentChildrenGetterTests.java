package ro.stancalau.lombok.base.parent;

import org.junit.Test;
import ro.stancalau.lombok.api.ImmutableParent;
import ro.stancalau.lombok.factory.ParentCreator;

import static org.junit.Assert.assertEquals;

public abstract class ParentChildrenGetterTests<T extends ImmutableParent> extends ParentCreator<T> {

    public ParentChildrenGetterTests(Class<T> clazz) {
        super(clazz);
    }

    @Test
    public void givenParentWhenMutatingChildListAsSideEffectThenParentStateDoesNotChange() throws Exception {
        //given
        T mutableParent = create(NAME, globalChildren);

        //when
        mutableParent.getChildren().add(ILLEGITIMATE_CHILD);

        //then
        assertEquals(2, mutableParent.getChildren().size());
        assertEquals(globalChildren, mutableParent.getChildren());
    }

    @Test
    public void givenParentWhenMutatingChildListAsSideEffectThenGlobalSetDoesNotChange() throws Exception {
        //given
        T mutableParent = create(NAME, globalChildren);

        //when
        mutableParent.getChildren().add(ILLEGITIMATE_CHILD);

        //then
        assertEquals(2, globalChildren.size());
    }
}