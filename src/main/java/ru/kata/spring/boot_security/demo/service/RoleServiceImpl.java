package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.DAO.RoleDAOImpl;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{

    private final RoleDAOImpl roleDAOImpl;

    public RoleServiceImpl(RoleDAOImpl roleDAOImpl) {
        this.roleDAOImpl = roleDAOImpl;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDAOImpl.getAllRoles();
    }

    @Override
    public Role getRoleById(long id) {
        return roleDAOImpl.getRoleById(id);
    }

    @Override
    public void updateRole(long id, Role role) {

    }

    @Override
    public void deleteRole(long id) {

    }

    @Override
    public void addRole(Role role) {

    }
}
