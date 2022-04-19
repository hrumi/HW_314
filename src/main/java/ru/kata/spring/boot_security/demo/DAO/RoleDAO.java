package ru.kata.spring.boot_security.demo.DAO;

import ru.kata.spring.boot_security.demo.model.Role;
import java.util.List;

public interface RoleDAO {
    List<Role> getAllRoles();
    void updateRole(Long id, Role role);
    void deleteById(Long id);
    void addRole(Role role);
    public Role getRoleById(Long id);
}