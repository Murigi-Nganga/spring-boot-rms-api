package com.example.rms.user.controller;

import com.example.rms.user.entity.User;
import com.example.rms.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    Iterable<User> getUsers() {return userService.getUsers();}

    @GetMapping("/id/{id}")
    Optional<User> getUserById(@PathVariable Long id) {return userService.getUserById(id);}

    @PostMapping("auth/login")
    ResponseEntity<?> getUserByEmail(@RequestBody Map<String, String> loginDetails){

        String email = loginDetails.get("email");
        String password = loginDetails.get("password");

        Optional<User> _user = userService.getUserByEmail(email);

        Map<String, Object> loginResponseMap = new HashMap<>();

        if(_user.isPresent() && Objects.equals(_user.get().getPassword(), password)) {
            updateUser(_user.get().getUserId(), _user.get());

            loginResponseMap.put("userId" , _user.get().getUserId());
            loginResponseMap.put("firstName" , _user.get().getFirstName());
            loginResponseMap.put("lastName" , _user.get().getLastName());
            loginResponseMap.put("email" , _user.get().getEmail());
            loginResponseMap.put("phoneNumber" , _user.get().getPhoneNumber());
            loginResponseMap.put("token" ,UUID.randomUUID());
            return ResponseEntity.ok().body(loginResponseMap);
        } else {
            loginResponseMap.put("timestamp", Timestamp.from(Instant.now()));
            loginResponseMap.put("status" , 500);
            loginResponseMap.put("error" , "Internal Server Error");
            loginResponseMap.put("path" , "api/user/auth/login");
            return ResponseEntity.internalServerError().body(loginResponseMap);
        }

    }

    @PostMapping("/auth/register")
    Map<String, Object> addUser(@RequestBody User user) {
        User _user = userService.addUser(user);

        Map<String, Object> registerResponseMap = new HashMap<>();

        registerResponseMap.put("userId" , _user.getUserId());
        registerResponseMap.put("firstName" , _user.getFirstName());
        registerResponseMap.put("lastName" , _user.getLastName());
        registerResponseMap.put("email" , _user.getEmail());
        registerResponseMap.put("phoneNumber" , _user.getPhoneNumber());
        registerResponseMap.put("token" ,UUID.randomUUID());

        return registerResponseMap;
    }

    @PostMapping("/addUsers")
    Iterable<User> addUsers(@RequestBody List<User> users) {return userService.addUsers(users);}

    @PutMapping("/{id}")
    void updateUser(@PathVariable Long id, @RequestBody User user) {
        userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

}