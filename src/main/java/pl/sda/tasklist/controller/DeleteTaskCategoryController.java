package pl.sda.tasklist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.tasklist.service.TaskCategoryService;

@RequiredArgsConstructor
@Controller
@RequestMapping("delete-category")
public class DeleteTaskCategoryController {

    TaskCategoryService taskCategoryService;

    @PostMapping
    String deleteTaskCategory(@ModelAttribute String urlName) {
        taskCategoryService.deleteTaskCategory(urlName);
        return "redirect:/";
    }
}
