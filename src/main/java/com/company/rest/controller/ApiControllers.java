package com.company.rest.controller;

import com.company.rest.models.User;
import com.company.rest.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiControllers{

    @Autowired
    private UserRepo userRepo;

    @GetMapping(value = "/")
    public String getPage(){
        return "Welcome!";
    }

    @GetMapping(value = "/users")
    public List<User> getUsers(){
        return userRepo.findAll();
    }

    @PostMapping(value = "/users")
    public String saveUser( @RequestBody  User user){
        userRepo.save(user);
        return "New user created!";
    }

    @PutMapping(value = "/users/{id}")
    public String updateUser( @PathVariable long id, @RequestBody User user){
        User updatedUser = userRepo.findById(id).get();

        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setAge(user.getAge());
        updatedUser.setOccupation(user.getOccupation());

        userRepo.save(updatedUser);
        return "User " + id + " updated!";
    }

    @DeleteMapping(value="/users/{id}")
    public String deleteUser(@PathVariable long id){
        User deleteUser = userRepo.findById(id).get();
        userRepo.delete(deleteUser);
        return "User " + id + " deleted!";
    }

}
