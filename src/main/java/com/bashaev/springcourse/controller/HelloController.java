package com.bashaev.springcourse.controller;

import com.bashaev.springcourse.entity.User;
import com.bashaev.springcourse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: Рустам
 */

@Controller
public class HelloController {

    private final UserService userService;

    @Autowired
    public HelloController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/")
    public String viewHomePage(Model model) {
        List<User> userList = userService.getAllUsers();
        model.addAttribute("userList", userList);
        return "index";
    }


    @GetMapping("/new")
    public String newPerson(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "new_user";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/";
    }


    @GetMapping("/edit/{id}")
    public String editUser(Model model, @PathVariable(name = "id") Long id) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "edit_user";
    }

    @PostMapping(value = "/edit")
    public String edit(@ModelAttribute("user") User user) {
        userService.editUser(user);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }

}
