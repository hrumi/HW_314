package ru.kata.spring.boot_security.demo.DAO;

import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;

public interface UserDAO {
    List<User> getAllUsers();
    User getUserById(int id);
    void updateUser(int id, User user);
    void deleteById(int id);
    void addUser(User user);
    User getUserByName(String name);
}
