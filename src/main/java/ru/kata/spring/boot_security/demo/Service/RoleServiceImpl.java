package ru.kata.spring.boot_security.demo.Service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public Role getOne(long id) {
        return roleRepository.getById(id);
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public void getSetOfRoles(User user, long[] roleId) {
        Set<Role> role = new HashSet<>();
        for (Long id : roleId) {
            role.add(getOne(id));
        }
        user.setRoles(role);
    }

}