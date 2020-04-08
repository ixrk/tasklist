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
import pl.sda.tasklist.exception.TaskNotFoundException;
import pl.sda.tasklist.model.Priority;
import pl.sda.tasklist.service.TaskCategoryService;
import pl.sda.tasklist.service.TaskService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/{user}/{categoryUrlName}/{uuidHex}")
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/edit-task")
    String editTask(@PathVariable String uuidHex, @RequestParam String taskAction, Model model) throws TaskNotFoundException {
        if (taskAction.equals("switchDone")) {
            taskService.switchDone(uuidHex);
            return "redirect:/{user}/{categoryUrlName}";
        } else if (taskAction.equals("delete")){
            taskService.deleteTask(uuidHex);
            return "redirect:/{user}/{categoryUrlName}";
        } else  {
            model.addAttribute("taskForm", taskService.getTaskAsForm(uuidHex));
            return "task-form";
        }
    }

    @PostMapping("/submit-task")
    String saveTaskForm(@PathVariable String uuidHex, @RequestParam CreateTaskForm taskForm) throws TaskNotFoundException {
        taskService.editTask(taskForm, uuidHex);
        return "redirect:/{user}/{categoryUrlName}";
    }
}
