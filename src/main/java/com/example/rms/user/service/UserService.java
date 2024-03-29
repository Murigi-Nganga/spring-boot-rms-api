package com.example.rms.user.service;

import com.example.rms.user.entity.User;
import com.example.rms.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){this.userRepository = userRepository;}

    public Iterable<User> getUsers(){return userRepository.findAll();}

    public Optional<User> getUserById(Long id){return userRepository.findById(id);}

    public Optional<User> getUserByEmail(String email){return userRepository.findByEmail(email);}

    public User addUser(User user){return userRepository.save(user);}

    public Iterable<User> addUsers(List<User> users){return userRepository.saveAll(users);}

    public void updateUser(Long id, User user) {

        if ((userRepository.existsById(id))) {
            new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
        } else {
            new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
        }
    }

    public void deleteUserById(Long id){userRepository.deleteById(id);}
}
