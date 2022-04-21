package ru.kata.spring.boot_security.demo.DAO;

import ru.kata.spring.boot_security.demo.model.Role;
import java.util.List;

public interface RoleDAO {
    List<Role> getAllRoles();
    void updateRole(long id, Role role);
    void deleteById(long id);
    void addRole(Role role);
    public Role getRoleById(long id);
}