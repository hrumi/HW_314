package ru.kata.spring.boot_security.demo.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {
    List<User> getAllUsers();
    User getUserById(Long id);
    void updateUser(Long id, User user, Set<Role> roleSet);
    void updateUser(Long id, User user);
    void deleteUser(Long id);
    void addUser(User user, Set<Role> roleSet);
    void addUser(User user);
    User getUserByName(String name);
    User getUserByEmail(String email);
}
