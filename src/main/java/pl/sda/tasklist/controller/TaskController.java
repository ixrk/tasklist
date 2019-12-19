package pl.sda.tasklist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.tasklist.dto.CreateTaskForm;
import pl.sda.tasklist.dto.TaskCategoryDto;
import pl.sda.tasklist.exception.TaskCategoryNotFoundException;
import pl.sda.tasklist.service.TaskCategoryService;
import pl.sda.tasklist.service.TaskService;

@RequiredArgsConstructor
@Controller
public class TaskController {

    TaskService taskService;
    ModelMapper modelMapper;

    @GetMapping("/task-list")
    ModelAndView getTaskPage() {
        return null;
    }


    @PostMapping("/edit-task")
    ModelAndView editTask(@ModelAttribute CreateTaskForm form) {
        return null;
    }

    @PostMapping("/new-task")
    ModelAndView newTask(@ModelAttribute CreateTaskForm form) {
        return null;
    }
}
