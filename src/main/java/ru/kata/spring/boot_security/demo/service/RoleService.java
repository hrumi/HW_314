package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;
import java.util.List;

public interface RoleService {

    List<Role> getAllRoles();
    Role getRoleById(int id);
    void updateRole(int id, Role role);
    void deleteRole(int id);
    void addRole(Role role);

}