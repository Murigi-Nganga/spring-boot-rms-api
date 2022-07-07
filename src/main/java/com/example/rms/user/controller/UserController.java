package com.example.rms.user.controller;

import com.example.rms.user.service.UserService;
import com.example.rms.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    Iterable<User> getUsers() {return userService.getUsers();}

    @GetMapping("/{id}")
    Optional<User> getUser(@PathVariable Long id) {return userService.getUserById(id);}

    @PostMapping("/email")
    Optional<User> getUserByEmail(@RequestBody String email){return userService.getUserByEmail(email);}

    @PostMapping("/add")
    User addUser(@RequestBody User user) {return userService.addUser(user);}

    @PostMapping("/add-many")
    Iterable<User> addUsers(@RequestBody List<User> users) {return userService.addUsers(users);}

    @PutMapping("/{id}")
    ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {return userService.updateUser(id, user);}

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

}
