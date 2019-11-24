package pl.sda.tasklist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.tasklist.dto.TaskCategoryForm;

import java.security.Principal;

@Controller
@RequestMapping("/new-category")
public class NewTaskCategoryController {

    @GetMapping
    ModelAndView newTaskCategoryForm(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("category-form");
        modelAndView.addObject("taskCategoryForm", new TaskCategoryForm())
    }
}
