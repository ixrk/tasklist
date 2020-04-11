package pl.sda.tasklist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.tasklist.dto.CreateTaskForm;
import pl.sda.tasklist.dto.TaskCategoryDto;
import pl.sda.tasklist.exception.TaskCategoryNotFoundException;
import pl.sda.tasklist.model.Priority;
import pl.sda.tasklist.service.TaskCategoryService;
import pl.sda.tasklist.service.TaskService;

import java.security.Principal;

@RequiredArgsConstructor
@Controller
public class TaskCategoryController {

    private final TaskService taskService;
    private final TaskCategoryService taskCategoryService;

    @GetMapping("/{user}/{categoryUrlName}")
    String getTaskPage(Model model, @PathVariable String user, @PathVariable String categoryUrlName, @RequestParam(required = false) String request) {
        if (request != null && request.equals("delete")) {
            taskCategoryService.deleteTaskCategory(user, categoryUrlName);
            return "redirect:/";
        }

        TaskCategoryDto taskCategory = null;
        try {
            taskCategory = taskCategoryService.getCategoryByUrlNameAndUsername(categoryUrlName, user);
        } catch (TaskCategoryNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task category not found", e);
        }

        if (request != null && request.equals("edit")) {
            model.addAttribute("name", taskCategory.getName());
            model.addAttribute("description", taskCategory.getDescription());
            return "category-form";
        }

        model.addAttribute("category", taskCategory);
        return "tasks";
    }

    @GetMapping("/{user}/{categoryUrlName}/add-task")
    ModelAndView getNewTaskPage(@PathVariable String user, @PathVariable String categoryUrlName) {
        ModelAndView modelAndView = new ModelAndView("task-form");
        modelAndView.addObject("edit", false);
        modelAndView.addObject("taskForm", new CreateTaskForm());
        modelAndView.addObject("priorities", Priority.values());
        modelAndView.addObject("user", user);
        modelAndView.addObject("categoryUrl", categoryUrlName);

        return modelAndView;
    }

    @PostMapping("/{user}/{categoryUrlName}/add-task")
    String newTask(@ModelAttribute CreateTaskForm taskForm, @PathVariable String user, @PathVariable String categoryUrlName) {
        taskService.addTask(taskForm, categoryUrlName, user);
        return "redirect:/{user}/{categoryUrlName}";
    }

    @GetMapping("/{user}/{categoryUrlName}/delete-category")
    String deleteTaskCategory(@PathVariable String categoryUrlName, @PathVariable String user) {
        taskCategoryService.deleteTaskCategory(user, categoryUrlName);
        return "redirect:/";
    }
}
