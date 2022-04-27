package ru.kata.spring.boot_security.demo.DAO;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class UserDAOImpl implements UserDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void updateUser(Long id, User user) {
        User updateUser = getUserById(id);
        updateUser.setName(user.getName());
        updateUser.setAge(user.getAge());
        updateUser.setRoles(user.getRoles());
        updateUser.setPassword(user.getPassword());
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(getUserById(id));
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getUserByName(String name) {
        return entityManager.createQuery("select u from User u WHERE u.name = :name", User.class)
                .setParameter("name", name)
                .getResultList()
                .stream().findFirst().orElse(null);
    }

    @Override
    public User getUserByEmail(String email) {
        return entityManager.createQuery("select u from User u WHERE u.email = :email", User.class)
                .setParameter("email", email)
                .getResultList()
                .stream().findFirst().orElse(null);
    }
}


