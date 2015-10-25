package ie.greenfinch.playbook.service;

import ie.greenfinch.playbook.config.AppConfig;
import ie.greenfinch.playbook.model.ModelClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class TestMeClassIT {

    @Autowired
    private TestMeClass testMeClass;

    @Test
    public void simpleTest() throws Exception {
        ModelClass modelClass = testMeClass.doReturnObject("id");
        assertThat(modelClass, is(notNullValue()));
    }
}
