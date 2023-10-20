package com.berkaycumbur.pribastask.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.berkaycumbur.pribastask.model.User;
import com.berkaycumbur.pribastask.repository.UserRepo;
import com.berkaycumbur.pribastask.service.SequenceGeneratorService;

@RestController
public class UserController {
    @Autowired
    private UserRepo userepo;

    
    @Autowired // Injecting Service
    private SequenceGeneratorService sequenceGeneratorService;

    @PostMapping("/adduser")
    public String saveUser(@RequestBody User user) {
        user.setId(sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME));
        userepo.save(user);
        return "Added Succesfully";
    }

    @GetMapping("/showusers")
    public List<User> getUser() {
        return userepo.findAll(Sort.by(Sort.Order.asc("id"))); // Showing all users ordered by ID
    }

    @PutMapping("/updateuser/{id}")
    public String updateTUser(@PathVariable long id, @RequestBody User updatedUser) {
        User existingUser = userepo.findById((int) id).orElse(null);// Change from id because ID should be constant

        if (existingUser != null) {
            // Update the fields of the existing timeline with the new data
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPassword(updatedUser.getPassword());
            // Update other fields as needed

            userepo.save(existingUser);
            return "Updated Successfully";
        } else {
            return "Timeline not found";
        }
    }

    @DeleteMapping("/deleteuser/{id}")
    public String deleteTimeline(@PathVariable int id) {
        userepo.deleteById(id);
        return "Deleted Successfully";
    }

}
