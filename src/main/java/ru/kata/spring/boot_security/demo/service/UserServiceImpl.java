package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.DAO.UserDAOImpl;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDAOImpl userDAOImpl;

    public UserServiceImpl(UserDAOImpl userDAOImpl) {
        this.userDAOImpl = userDAOImpl;
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
    public void updateUser(Long id, User user) {
        userDAOImpl.updateUser(id, user);
    }

    @Override
    public void deleteUser(Long id) {
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
        return user;
    }

//    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
//        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
//    }

}
