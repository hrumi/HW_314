package ru.kata.spring.boot_security.demo.DAO;

import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;

public interface UserDAO {
    List<User> getAllUsers();
    User getUserById(Long id);
    void updateUser(Long id, User user);
    void deleteById(Long id);
    void addUser(User user);
    User getUserByName(String name);
    User getUserByEmail(String email);
}
