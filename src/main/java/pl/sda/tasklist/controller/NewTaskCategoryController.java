package pl.sda.tasklist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.tasklist.dto.TaskCategoryForm;
import pl.sda.tasklist.exception.UserNotFoundException;
import pl.sda.tasklist.service.TaskCategoryService;

import java.security.Principal;

@RequiredArgsConstructor
@Controller
@RequestMapping("/new-category")
public class NewTaskCategoryController {
    private final TaskCategoryService taskCategoryService;

    @GetMapping
    ModelAndView newTaskCategoryForm() {
        ModelAndView modelAndView = new ModelAndView("category-form");
        modelAndView.addObject("taskCategoryForm", new TaskCategoryForm());
        return modelAndView;
    }

    @PostMapping
    String addTaskCategory(Principal principal, @ModelAttribute TaskCategoryForm taskCategoryForm) throws UserNotFoundException {
        taskCategoryService.addTaskCategoryForUser(principal.getName(), taskCategoryForm);
        return "redirect:/";
    }
}
