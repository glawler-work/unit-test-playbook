package ie.greenfinch.playbook;

import org.springframework.beans.factory.annotation.Autowired;

public class TestMeClass {

    @Autowired
    private DataAccessObject dataAccessObject;

    public void doSomething(ModelClass modelClass) throws Exception {
        dataAccessObject.voidMethod(modelClass);
    }

    public ModelClass doReturnObject(String id) throws Exception {
        return dataAccessObject.returnsObjectOneParameter(id);
    }

    public ModelClass doReturnObject(String id, ModelClass modelClass) throws Exception {
        return dataAccessObject.returnsObjectTwoParameters(id, modelClass);
    }
}
