package com.example.userdept.controllers;


import com.example.userdept.dtos.UserRecordDto;
import com.example.userdept.models.entities.UserModel;
import com.example.userdept.repositories.UserRepository;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @GetMapping("/users/{idUser}")
    public ResponseEntity<Object> getOne(@PathVariable("idUser") UUID idUser){
        Optional<UserModel> userOpt = userRepository.findById(idUser);
        if(userOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not Found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(userOpt.get());
    }

    @PutMapping("/users/{idUser}")
    public ResponseEntity<Object> updateUser(@PathVariable UUID idUser, @RequestBody @Valid UserRecordDto userRecordDto) {
        Optional<UserModel> userOpt = userRepository.findById(idUser);
        if (userOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not Found.");
        }
        var userModel = userOpt.get();
        BeanUtils.copyProperties(userRecordDto, userModel);
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.save(userModel));
    }

    @DeleteMapping("/users/{idUser}")
    public ResponseEntity<Object> deleteUser(@PathVariable UUID idUser){
        Optional<UserModel> userOpt = userRepository.findById(idUser);
        if(userOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not FOund.");
        }
        userRepository.delete(userOpt.get());
        return ResponseEntity.status(HttpStatus.OK).body("User Deleted Successfully");
    }



}
