package ie.greenfinch.playbook.dao;

import ie.greenfinch.playbook.model.ModelClass;
import org.springframework.stereotype.Repository;

@Repository
public class DataAccessObject {

    public void voidMethod(ModelClass modelClass) throws Exception {}

    public ModelClass returnsObjectOneParameter(String id) throws Exception {
        return new ModelClass();
    }

    public ModelClass returnsObjectTwoParameters(String id, ModelClass modelClass) throws Exception {
        return new ModelClass();
    }
}
