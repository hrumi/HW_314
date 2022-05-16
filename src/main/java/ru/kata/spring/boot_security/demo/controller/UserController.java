package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import java.security.Principal;


@Controller
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
        return "index3";
}

//    @GetMapping("/user") //для получение view со списком users
//    public String homePageUser (Principal principal, Model model) {
//        Long id = userService.getUserByName(principal.getName()).getId();
//        model.addAttribute("currentUser", userService.getUserById(id));
//        return "user1";
//    }

//    @PostMapping("/admin/delete")
//    public String deleteUser(@RequestParam(value = "id") String id) {
//        userService.deleteUser(Long.parseLong(id));
//        return "redirect:/admin";
//    }

//    @PostMapping("/admin")
//    public String addNewUser(@ModelAttribute("user") User user, @RequestParam(value = "admin_role", defaultValue = "USER") String itsAdmin) {
//        Set<Role> rolesSet= new HashSet<>();
//        rolesSet.add(roleService.getRoleById(1L));
//        if (itsAdmin.equals("ADMIN")) {
//            rolesSet.add(roleService.getRoleById(2L));
//        }
//        userService.addUser(user, rolesSet);
//        return "redirect:/admin";
//    }

//    @PatchMapping ("/admin/edit")  //отправка данных страницы изменения в БД
//    public String EditUser(@ModelAttribute("user") User user, @RequestParam(value = "admin_role", defaultValue = "USER") String itsAdmin, @RequestParam(value = "id") String id)  {

//        Set<Role> rolesSet= new HashSet<>();
//        rolesSet.add(roleService.getRoleById(1L));
//        if (itsAdmin.equals("ADMIN")) {
//            rolesSet.add(roleService.getRoleById(2L));
//        }
//        userService.updateUser(Long.parseLong(id), user, rolesSet);
//        return "redirect:/admin";
//    }
}
