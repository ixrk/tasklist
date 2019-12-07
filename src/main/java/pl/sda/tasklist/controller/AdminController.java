package pl.sda.tasklist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.tasklist.dao.UserRepository;
import pl.sda.tasklist.service.UserService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    @GetMapping("/user-list")
    ModelAndView getAllUsers(){
        ModelAndView mnv = new ModelAndView("user-list");
        mnv.addObject("userList", userService.getAllUsernames());
        return mnv;
    }
}
