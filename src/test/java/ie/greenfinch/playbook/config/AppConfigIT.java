package ie.greenfinch.playbook.config;

import ie.greenfinch.playbook.model.ModelClass;
import ie.greenfinch.playbook.service.TestMeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class AppConfigIT {

    @Autowired
    private ApplicationContext context;

    @Test
    public void contextCreatedSuccessfully() throws Exception {
        assertThat(context.getApplicationName(), is(notNullValue()));
    }
}
