package ie.greenfinch.playbook;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TestMeClassTest {
    private static final String ID = "id";
    private static final String PROPERTY = "property";
    @InjectMocks private TestMeClass testMeClass;
    private ModelClass modelClass;
    @Mock private DataAccessObject dataAccessObject;

    @Before
    public void onSetUp() {
        modelClass = new ModelClass();
        modelClass.setId(ID);
        modelClass.setProperty(PROPERTY);
    }

    @Test
    public void methodReturnsCorrectlyGivenArgument() throws Exception {
        when(dataAccessObject.returnsObjectOneParameter(ID)).thenReturn(modelClass);

        ModelClass result = testMeClass.doReturnObject(ID);
        assertThat(result, is(modelClass));
    }

    @Test
    public void methodReturnsCorrectlyArbitraryArgument() throws Exception {
        when(dataAccessObject.returnsObjectOneParameter(any(String.class))).thenReturn(modelClass);

        ModelClass result = testMeClass.doReturnObject("randomValueWeDontCare");
        assertThat(result, is(modelClass));
    }

    @Test
    public void methodReturnsCorrectlyGivenKnownAndUnknownArguments() throws Exception {
        when(dataAccessObject.returnsObjectTwoParameters(eq(ID), any(ModelClass.class))).thenReturn(modelClass);

        ModelClass result = testMeClass.doReturnObject(ID, modelClass);
        assertThat(result, is(modelClass));
    }


    @Test(expected = Exception.class)
    public void shouldThrowException() throws Exception {
        when(dataAccessObject.returnsObjectOneParameter(ID)).thenThrow(new Exception());

        testMeClass.doReturnObject(ID);
    }

    @Test
    public void voidMethod() throws Exception {
        doNothing().when(dataAccessObject).voidMethod(any(ModelClass.class));

        testMeClass.doSomething(modelClass);
    }

    @Test(expected = Exception.class)
    public void voidMethodThrowsException() throws Exception {
        doThrow(new Exception()).when(dataAccessObject).voidMethod(any(ModelClass.class));

        testMeClass.doSomething(modelClass);
    }

    @Test
    public void verifyMethodWasCalled() throws Exception {
        doNothing().when(dataAccessObject).voidMethod(any(ModelClass.class));

        testMeClass.doSomething(modelClass);
        verify(dataAccessObject, times(1)).voidMethod(any(ModelClass.class));
    }

    @Test
    public void verifyMethodsNotCalled() throws Exception {
        doNothing().when(dataAccessObject).voidMethod(any(ModelClass.class));

        testMeClass.doSomething(modelClass);
        verify(dataAccessObject, never()).returnsObjectOneParameter(ID);
        verify(dataAccessObject, never()).returnsObjectTwoParameters(eq(ID), any(ModelClass.class));
    }

    @Test
    public void captureArgument() throws Exception {
        ArgumentCaptor<ModelClass> captor = ArgumentCaptor.forClass(ModelClass.class);

        doNothing().when(dataAccessObject).voidMethod(captor.capture());

        testMeClass.doSomething(modelClass);
        ModelClass capturedValue = captor.getValue();
        assertThat(capturedValue, is(notNullValue()));
        assertThat(capturedValue.getId(), is(ID));
        assertThat(capturedValue.getProperty(), is(PROPERTY));
    }

    @Test
    public void multipleInvocationsOnMock() throws Exception {
        String id2 = "id2";
        String property2 = "property2";
        ModelClass secondModelClass = new ModelClass();
        secondModelClass.setId(id2);
        secondModelClass.setProperty(property2);

        when(dataAccessObject.returnsObjectOneParameter(ID)).thenReturn(modelClass).thenReturn(secondModelClass);

        ModelClass firstResult = testMeClass.doReturnObject(ID);
        ModelClass secondResult = testMeClass.doReturnObject(ID);

        verify(dataAccessObject, times(2)).returnsObjectOneParameter(any(String.class));
        assertThat(firstResult.getId(), is(ID));
        assertThat(firstResult.getProperty(), is(PROPERTY));
        assertThat(secondResult.getId(), is(id2));
        assertThat(secondResult.getProperty(), is(property2));
    }
}
