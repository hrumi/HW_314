//package ru.kata.spring.boot_security.demo.DAO;
//
//import org.springframework.stereotype.Component;
//import ru.kata.spring.boot_security.demo.model.Role;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.util.List;
//
//@Component
//public class RoleDAOImpl implements RoleDAO{
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Override
//    public List<Role> getAllRoles() {
//        return entityManager.createQuery("select u from Role u", Role.class).getResultList();
//    }
//
//    @Override
//    public Role getRoleById(int id) {
//        return entityManager.find(Role.class, id);
//    }
//
//    @Override
//    public void updateRole(int id, Role role) {
//        Role updatingRole = getRoleById(id);
//        updatingRole.setName(role.getName());
//        updatingRole.setUsers(role.getUsers());
//    }
//
//    @Override
//    public void deleteById(int id) {
//        entityManager.remove(getRoleById(id));
//
//    }
//
//    @Override
//    public void addRole(Role role) {
//        entityManager.persist(role);
//    }
//}
