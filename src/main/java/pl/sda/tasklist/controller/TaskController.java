package pl.sda.tasklist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TaskController {

    @GetMapping("{/{user}/{categoryName}")
    ModelAndView getTaskPage() {

        ModelAndView mvn = new ModelAndView("tasks");
        mvn.addObject("tasks")
        return new ModelAndView("tasks");
    }
}
