package pl.sda.tasklist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.tasklist.dto.CreateTaskForm;
import pl.sda.tasklist.dto.TaskCategoryDto;
import pl.sda.tasklist.exception.TaskCategoryNotFoundException;
import pl.sda.tasklist.model.Priority;
import pl.sda.tasklist.service.TaskCategoryService;
import pl.sda.tasklist.service.TaskService;

@RequiredArgsConstructor
@Controller
public class TaskController {

    private final TaskService taskService;
    private final TaskCategoryService taskCategoryService;

    @GetMapping("/{user}/{categoryUrl}")
    ModelAndView getTaskPage(@PathVariable String user, @PathVariable String categoryUrl) {
        ModelAndView modelAndView = new ModelAndView("tasks");

        TaskCategoryDto taskCategory = null;
        try {
            taskCategory = taskCategoryService.getCategoryByUrlNameAndUsername(categoryUrl, user);
        } catch (TaskCategoryNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task category not found", e);
        }
        modelAndView.addObject("category", taskCategory);

        return modelAndView;
    }


    @PostMapping("/edit-task")
    ModelAndView editTask(@ModelAttribute CreateTaskForm form) {
        return null;
    }

    @GetMapping("/{user}/{categoryUrl}/add-task")
    ModelAndView getNewTaskPage(@PathVariable String user, @PathVariable String categoryUrl) {
        ModelAndView modelAndView = new ModelAndView("task-form");
        modelAndView.addObject("taskForm", new CreateTaskForm());
        modelAndView.addObject("priorities", Priority.values());
        modelAndView.addObject("user", user);
        modelAndView.addObject("categoryUrl", categoryUrl);

        return modelAndView;
    }

    @PostMapping("/{user}/{categoryUrl}/add-task")
    String newTask(@ModelAttribute CreateTaskForm form, @PathVariable String user, @PathVariable String categoryUrl) {
        taskService.addTask(form, categoryUrl, user);
        return "redirect:/{user}/{categoryUrl}";
    }
}
