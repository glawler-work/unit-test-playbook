package ie.greenfinch.playbook;

public class DataAccessObject {

    public void voidMethod(ModelClass modelClass) throws Exception {}

    public ModelClass returnsObjectOneParameter(String id) throws Exception {
        return new ModelClass();
    }

    public ModelClass returnsObjectTwoParameters(String id, ModelClass modelClass) throws Exception {
        return new ModelClass();
    }
}
