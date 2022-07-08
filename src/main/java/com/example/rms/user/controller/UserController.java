package com.example.rms.user.controller;

import com.example.rms.user.service.UserService;
import com.example.rms.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/all")
    Iterable<User> getUsers() {return userService.getUsers();}

    @GetMapping("/user/{id}")
    Optional<User> getUser(@PathVariable Long id) {return userService.getUserById(id);}

    @GetMapping("/auth/login/{email}")
    Optional<User> getUserByEmail(@PathVariable String email){return userService.getUserByEmail(email);}

    @PostMapping("/auth/register")
    User addUser(@RequestBody User user) {return userService.addUser(user);}

    @PostMapping("/addUsers")
    Iterable<User> addUsers(@RequestBody List<User> users) {return userService.addUsers(users);}

    @PutMapping("/user/{id}")
    ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {return userService.updateUser(id, user);}

    @DeleteMapping("/user/{id}")
    void deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

}
