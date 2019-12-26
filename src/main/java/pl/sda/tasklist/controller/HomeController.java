package pl.sda.tasklist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.tasklist.dto.TaskCategoryForm;
import pl.sda.tasklist.exception.UserNotFoundException;
import pl.sda.tasklist.service.TaskCategoryService;

import java.security.Principal;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final TaskCategoryService taskCategoryService;

    @GetMapping("/")
    ModelAndView getIndex(Principal principal) throws UserNotFoundException {
        ModelAndView modelAndView;
        if (principal == null) {
            modelAndView = new ModelAndView("index-anonymous");
        } else {
            modelAndView = new ModelAndView("index-logged-in");
            modelAndView.addObject("taskCategories", taskCategoryService.getAllTaskCategoriesByUser(principal.getName()));
            modelAndView.addObject("username", principal.getName());
        }
        return modelAndView;
    }

    @GetMapping("/new-category")
    ModelAndView newTaskCategoryForm() {
        ModelAndView modelAndView = new ModelAndView("category-form");
        modelAndView.addObject("taskCategoryForm", new TaskCategoryForm());
        return modelAndView;
    }

    @PostMapping("/new-category")
    String addTaskCategory(Principal principal, @ModelAttribute TaskCategoryForm taskCategoryForm) throws UserNotFoundException {
        taskCategoryService.addTaskCategoryForUser(principal.getName(), taskCategoryForm);
        return "redirect:/";
    }
}
