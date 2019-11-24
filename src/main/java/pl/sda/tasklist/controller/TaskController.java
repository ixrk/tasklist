package pl.sda.tasklist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.tasklist.dto.CreateTaskForm;
import pl.sda.tasklist.dto.TaskDto;
import pl.sda.tasklist.mapper.ModelMapper;
import pl.sda.tasklist.service.TaskService;

@RequiredArgsConstructor
@Controller
public class TaskController {

    TaskService taskService;


    @GetMapping("/tasklist")
    ModelAndView getTaskPage() {
        ModelAndView mvn = new ModelAndView("tasks");
        mvn.addObject("tasks", taskService.getTasks());
        return new ModelAndView("tasks");
    }


    @PostMapping("/edittask")
    ModelAndView editTask(@ModelAttribute CreateTaskForm form) {
        ModelAndView mnv = new ModelAndView("tasks");
        mnv.addObject("createFormTask", new CreateTaskForm());
        TaskDto taskDto = ModelMapper.mapFormToTaskDto(form);
        taskService.addTask(form);
        return mnv;
    }

    @PostMapping("/newtask")
    ModelAndView newTask(@ModelAttribute CreateTaskForm form) {
        ModelAndView mnv = new ModelAndView("");
        taskService.addTask(form);
        mnv.setViewName("redirect:/tasklist");
        return mnv;
    }

    @PostMapping("/deletetask/{id}")
    String removeTask(@PathVariable long idProduct) {
        taskService.deleteTaskById(idProduct);
        return "redicrect:/tasks";
    }


}
