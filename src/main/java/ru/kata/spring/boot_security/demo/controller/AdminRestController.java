package ru.kata.spring.boot_security.demo.controller;

import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.Service.RoleService;
import ru.kata.spring.boot_security.demo.Service.UserService;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;


@RestController
@RequestMapping("/admin")
public class AdminRestController {


    private final RoleService roleService;
    private final UserService userService;

    public AdminRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @PostMapping(value = "/users")
    public List<User> allUsers() {
        return userService.getAll();
    }

    @PostMapping(value = "/add")
    public void create(@RequestParam String firstName, String password, String lastName, String email,
                       byte age, @RequestParam(name = "roleId") long[] roleId) {

        User user = new User(firstName, lastName, age, email, password);
        roleService.getSetOfRoles(user, roleId);
        userService.addUser(user);
    }

    @PostMapping(value = "/roles")
    public List<Role> getRoles() {
        return roleService.getAll();
    }

    @PostMapping("/edit")
    public void edit(@RequestParam long id, String firstName, String password, String lastName, String email,
                     byte age, @RequestParam(name = "roleId") long[] roleId) {
        User user = new User(id, firstName, lastName, age, email, password);
        roleService.getSetOfRoles(user, roleId);
        userService.addUser(user);
    }

    @PostMapping("/delete")
    public void delete(@RequestParam long id) {
        userService.remove(userService.getOne(id));
    }
}



