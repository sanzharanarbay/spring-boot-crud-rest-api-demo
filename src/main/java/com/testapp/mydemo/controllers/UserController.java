package com.testapp.mydemo.controllers;

import com.testapp.mydemo.entities.User;
import com.testapp.mydemo.exceptions.ResourceNotFoundException;
import com.testapp.mydemo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // get all users
    @GetMapping("/all")
    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    // get user by id
    @GetMapping("/{id}")
    public User getUserById(@PathVariable(value = "id") long userId){
        return this.userRepository.findById(userId).
                orElseThrow(() -> new ResourceNotFoundException("User Not Found!!!"));
    }

    // create user
    @PostMapping("/create")
    public User createUser(@RequestBody User user){
        return this.userRepository.save(user);
    }

    // update User
    @PutMapping("/update/{id}")
    public User updateUser(@RequestBody User request, @PathVariable(value = "id") long userId){
        User exist = this.userRepository.findById(userId).
                orElseThrow(() -> new ResourceNotFoundException("User Not Found!!!"));
        exist.setFirstname(request.getFirstname());
        exist.setLastname(request.getLastname());
        exist.setEmail(request.getEmail());
       return  this.userRepository.save(exist);
    }

    // delete User
    @DeleteMapping("/delete/{id}")
    public Map<String, String> deleteUser(@PathVariable(value = "id") long userId){
        User exist = this.userRepository.findById(userId).
                orElseThrow(() -> new ResourceNotFoundException("User Not Found!!!"));
        this.userRepository.delete(exist);
        HashMap<String, String> map = new HashMap<>();
        map.put("status", "ok");
        map.put("message", "User Deleted successfully!!!");
        return map;
    }


}
