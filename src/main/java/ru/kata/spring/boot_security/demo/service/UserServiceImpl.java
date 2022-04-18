package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.DAO.UserDAOImpl;
import ru.kata.spring.boot_security.demo.model.User;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserDAOImpl userDAOImpl;

    public UserServiceImpl(UserDAOImpl userDAOImpl) {
        this.userDAOImpl = userDAOImpl;
    }

    @Override
    public List<User> getAllUsers() {
        return userDAOImpl.getAllUsers();
    }

    @Override
    public User getUserById(int id) {
        return userDAOImpl.getUserById(id);
    }

    @Override
    public void updateUser(int id, User user) {
        userDAOImpl.updateUser(id, user);
    }

    @Override
    public void deleteUser(int id) {
        userDAOImpl.deleteById(id);
    }

    @Override
    public void addUser(User user) {
        userDAOImpl.addUser(user);
    }

    @Override
    public User getUserByName(String name) {
        return userDAOImpl.getUserByName(name);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAOImpl.getUserByName(username);
        if (user == null)
            throw new UsernameNotFoundException("User not found");
        return org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), )
    }

}
