package ru.kata.spring.boot_security.demo.DAO;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
//import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;


@Component
public class UserDAOImpl implements UserDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() { //зачем-то подчеркивает User
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void updateUser(int id, User user) {
        User updateUser = getUserById(id);
        updateUser.setName(user.getName());
        updateUser.setAge(user.getAge());
    }

    @Override
    public void deleteById(int id) {
        entityManager.remove(getUserById(id));
    }

    @Override
    public void addUser(User user) {
        //user.setRoles(Set.of(new Role(1L,"ROLE_USER")));
        //user.setPassword(user.getPassword());
        entityManager.persist(user);
    }

    @Override
    public User getUserByName(String name) {
        return entityManager.find(User.class, name);
    }

//    public UserDetails loadUserByUsername(String name) {
//        User user = entityManager.find(User.class, name);
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found");
//        }
//        return user;
//    }

}


