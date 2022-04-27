package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import javax.transaction.Transactional;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;


@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String homePageAdmin (Principal principal, Model model) {
        model.addAttribute("usersList", userService.getAllUsers());

        Long id = userService.getUserByName(principal.getName()).getId();
        model.addAttribute("currentUser", userService.getUserById(id));
        return "index";
    }

    @GetMapping("/user") //для получение view со списком users
    public String homePageUser (Principal principal, Model model) {
        Long id = userService.getUserByName(principal.getName()).getId();
        model.addAttribute("user", userService.getUserById(id));
        return "user";
    }

    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @GetMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users/admin";
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user";
    }

    @PostMapping("/admin")
    public String addNewUser(@ModelAttribute("user") User user, @RequestParam(value = "admin_role", defaultValue = "false") boolean itsAdmin) {

        Set<Role> rolesSet= new HashSet<>();
        rolesSet.add(roleService.getRoleById(1L));
        if (itsAdmin) {
            rolesSet.add(roleService.getRoleById(2L));
        }
        userService.addUser(user, rolesSet);
        return "redirect:/users/admin";
    }

    @PatchMapping ("/{id}")  //отправка данных страницы изменения в БД
    public String EditUser(@ModelAttribute("user") User user, @PathVariable("id") Long id, @RequestParam(value = "admin_role", defaultValue = "false") boolean itsAdmin) {

        Set<Role> rolesSet= new HashSet<>();
        rolesSet.add(roleService.getRoleById(1L));
        if (itsAdmin) {
            rolesSet.add(roleService.getRoleById(2L));
        }

        userService.updateUser(id, user, rolesSet);
        return "redirect:/users/admin";
    }
}
