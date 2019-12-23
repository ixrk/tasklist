package pl.sda.tasklist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.tasklist.service.TaskCategoryService;

import java.security.Principal;

@RequiredArgsConstructor
@Controller
@RequestMapping("delete-category")
public class DeleteTaskCategoryController {

    TaskCategoryService taskCategoryService;

    @PostMapping
    String deleteTaskCategory(@ModelAttribute String urlName, Principal principal) {
        taskCategoryService.deleteTaskCategory(urlName, principal.getName());
        return "redirect:/";
    }
}
