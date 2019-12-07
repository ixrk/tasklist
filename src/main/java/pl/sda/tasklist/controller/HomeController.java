package pl.sda.tasklist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.tasklist.service.TaskCategoryService;

import java.security.Principal;

@RequiredArgsConstructor
@Controller
@RequestMapping
public class HomeController {

    private final TaskCategoryService taskCategoryService;

    @GetMapping("/")
    ModelAndView getIndex(Principal principal) {
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
}
