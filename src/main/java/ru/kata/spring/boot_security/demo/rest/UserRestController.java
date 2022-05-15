package ru.kata.spring.boot_security.demo.rest;

import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.rest.exception_handler.UserIncorrectDataException;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private final UserService userService;
    private final RoleService roleService;

    public UserRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getOneUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            throw new UserIncorrectDataException("There is no user with ID: " + id + "in DB.");
        }
        return user;
    }

    @PostMapping("/users")
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @PutMapping("/users")
    public void editUser(@RequestBody User user) {
        userService.updateUser(user);
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if(user == null) {
            throw new UserIncorrectDataException("There is no user with ID: " + id + "in DB.");
        }

        userService.deleteUser(id);
        return "Users with ID = " + id + "was deleted.";
    }

    @GetMapping("/currentUser")
    public User getCurrentUser (Principal principal) {
        return userService.getUserByName(principal.getName());
    }

}
