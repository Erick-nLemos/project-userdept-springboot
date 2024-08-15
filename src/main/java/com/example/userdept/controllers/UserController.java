package com.example.userdept.controllers;


import com.example.userdept.dtos.UserRecordDto;
import com.example.userdept.models.entities.UserModel;
import com.example.userdept.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/users")
    public ResponseEntity<UserModel> saveUser(@RequestBody @Valid UserRecordDto userRecordDto){
        var userModel = new UserModel();
        BeanUtils.copyProperties(userRecordDto, userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(userModel));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserModel>> getAll(){
        List<UserModel> usersList = userRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(usersList);
    }



}
