package ie.greenfinch.playbook.controller;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ie.greenfinch.playbook.model.ModelClass;
import ie.greenfinch.playbook.service.TestMeClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class TestMeControllerTest {

    private static final String ID = "id";
    private static final String PARAMETER = "parameter";
    private static final String PROPERTY = "property";
    private MockHttpServletRequestBuilder requestBuilder;
    private MockMvc mockMvc;
    private ModelClass modelClass;
    private ObjectMapper objectMapper;

    @Mock private TestMeClass testMeClass;
    @InjectMocks private TestMeController testMeController;

    @Before
    public void onSetUp() throws Exception {
        modelClass = new ModelClass();
        modelClass.setId(ID);
        modelClass.setProperty(PROPERTY);
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(testMeController).build();
    }

    @Test
    public void getWithRequestParameters() throws Exception {
        when(testMeClass.doReturnObject(PARAMETER)).thenReturn(modelClass);
        when(testMeClass.doReturnObject(ID)).thenReturn(modelClass);

        requestBuilder = MockMvcRequestBuilders.get("/" + ID)
                                               .accept(MediaType.APPLICATION_JSON)
                                               .param(PARAMETER, PARAMETER);

        MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
        String resultString = result.getResponse().getContentAsString();
        assertThat(resultString, is(notNullValue()));

        JsonNode node = objectMapper.readTree(resultString);
        assertThat(node.get("id").asText(), is(ID));
        assertThat(node.get("property").asText(), is(PROPERTY));

        verify(testMeClass, times(1)).doReturnObject(ID);
        verify(testMeClass, times(1)).doReturnObject(PARAMETER);
    }

    @Test
    public void postObject() throws Exception {
        ArgumentCaptor<ModelClass> captor = ArgumentCaptor.forClass(ModelClass.class);
        doNothing().when(testMeClass).doSomething(captor.capture());
        requestBuilder = MockMvcRequestBuilders.post("/")
                                               .accept(MediaType.APPLICATION_JSON)
                                               .contentType(MediaType.APPLICATION_JSON)
                                               .content(objectMapper.writeValueAsString(modelClass));

        mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();

        verify(testMeClass, times(1)).doSomething(any(ModelClass.class));
        ModelClass argument = captor.getValue();
        assertThat(argument, is(notNullValue()));
        assertThat(argument.getId(), is(ID));
    }
}
