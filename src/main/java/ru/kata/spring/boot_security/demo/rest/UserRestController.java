package ru.kata.spring.boot_security.demo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.rest.exception_handler.UserIncorrectData;
import ru.kata.spring.boot_security.demo.rest.exception_handler.UserIncorrectDataException;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public User addUser(@RequestBody User user, @RequestParam(value = "itsAdmin", defaultValue = "USER") String itsAdmin) {

        Set<Role> rolesSet = new HashSet<>();
        rolesSet.add(roleService.getRoleById(1L));
        if (itsAdmin.equals("ADMIN")) {
            rolesSet.add(roleService.getRoleById(2L));
        }
        userService.addUser(user, rolesSet);

        return user;
    }

    @PutMapping("/users")
    public User editUser(@RequestBody User user, @RequestParam(value = "itsAdmin", defaultValue = "USER") String itsAdmin) {

        Set<Role> rolesSet = new HashSet<>();
        rolesSet.add(roleService.getRoleById(1L));
        if (itsAdmin.equals("ADMIN")) {
            rolesSet.add(roleService.getRoleById(2L));
        }
        userService.updateUser(user.getId(), user, rolesSet);
        return user;
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

}
