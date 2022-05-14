package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.DAO.UserDAOImpl;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDAOImpl userDAOImpl;

    public UserServiceImpl(UserDAOImpl userDAOImpl) {
        this.userDAOImpl = userDAOImpl;
    }

    public BCryptPasswordEncoder myPasswordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAOImpl.getAllUsers();
    }

    @Override
    public User getUserById(Long id) {
        return userDAOImpl.getUserById(id);
    }

    @Override
    public void updateUser(Long id, User user, Set<Role> roleSet) {
        user.setPassword(myPasswordEncoder().encode(user.getPassword()));
        user.setRoles(roleSet);
        userDAOImpl.updateUser(id, user);
    }

    @Override
    public void updateUser(User user) {
        user.setPassword(myPasswordEncoder().encode(user.getPassword()));
        userDAOImpl.updateUser(user.getId(), user);
    }

    @Override
    public void deleteUser(Long id) {
        userDAOImpl.deleteById(id);
    }

    @Override
    public void addUser(User user, Set<Role> roleSet) {
        user.setPassword(myPasswordEncoder().encode(user.getPassword()));
        user.setRoles(roleSet);
        userDAOImpl.addUser(user);
    }

    @Override
    public void addUser(User user) {
        user.setPassword(myPasswordEncoder().encode(user.getPassword()));
        userDAOImpl.addUser(user);
    }

    @Override
    public User getUserByName(String name) {
        return userDAOImpl.getUserByName(name);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDAOImpl.getUserByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDAOImpl.getUserByEmail(email);
        if (user == null)
            throw new UsernameNotFoundException("User not found");
        return user;
    }
}
