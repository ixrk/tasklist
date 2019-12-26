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

@RequiredArgsConstructor
@Controller
@RequestMapping("/{user}/{categoryUrlName}/{uuidHex}")
public class TaskController {

    @PostMapping("/edit-task")
    ModelAndView editTask(@ModelAttribute CreateTaskForm form) {
        return null;
    }
}
