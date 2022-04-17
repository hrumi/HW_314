package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.security.Principal;


@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/test")
    public String homePageUserWithOut () {
        return "user1";
    }

    @GetMapping("/admin")
    public String homePageAdmin (Model model) {
        model.addAttribute("usersList", userService.getAllUsers());
        return "index";
    }

    @GetMapping("/user") //для получение view со списком users
    public String homePageUser (Principal principal, Model model) {
        int id = userService.getUserByName(principal.getName()).getId();
        model.addAttribute("user", userService.getUserById(id));
        return "user";
    }

//    @GetMapping("/new") //получение view для создания нового user
//    public String newUser(@ModelAttribute("user") User user) {
//        return "new";
//    }

    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @GetMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/users/admin";
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user";
    }

    @PostMapping("/admin") //ловим ПОСТ запрос из view new
    public String addNewUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/users/admin";
    }

    @PatchMapping ("/{id}")  //отправка данных страницы изменения в БД
    public String EditUser(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.updateUser(id, user);
        return "redirect:/users/admin";
    }
}
