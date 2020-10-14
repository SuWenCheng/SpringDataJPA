package com.alwin.jpa.example.controller;

import com.alwin.jpa.example.bean.User;
import com.alwin.jpa.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/user/")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User addUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping(path = "all")
    @ResponseBody
    public Page<User> getAllUsers(Pageable request) {
        return userRepository.findAll(request);
    }

    @GetMapping(path = "page")
    @ResponseBody
    public Page<User> getAllUserByPage() {
        return userRepository.findAll(
                PageRequest.of(1,  20, Sort.by(new Sort.Order(Sort.Direction.ASC, "name"))));
    }

    @GetMapping(path = "sort")
    @ResponseBody
    public List<User> getAllUsersWithSort() {
        return userRepository.findAll(Sort.by(new Sort.Order(Sort.Direction.ASC,"name")));
    }
}
