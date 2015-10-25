package ie.greenfinch.playbook.controller;

import ie.greenfinch.playbook.model.ModelClass;
import ie.greenfinch.playbook.service.TestMeClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller(value = "/resource")
public class TestMeController {

    public static final String PARAMETER = "parameter";

    @Autowired
    private TestMeClass testMeClass;

    @RequestMapping(method = RequestMethod.GET, value = "/{resourceId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ModelClass simpleGet(@PathVariable String resourceId, HttpServletRequest request) throws Exception {
        String parameter = request.getParameter(PARAMETER);
        testMeClass.doReturnObject(parameter);
        return testMeClass.doReturnObject(resourceId);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void postMethod(@RequestBody ModelClass modelClass) throws Exception {
        testMeClass.doSomething(modelClass);
    }
}
