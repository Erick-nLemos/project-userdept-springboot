package com.example.userdept.controllers;

import com.example.userdept.dtos.DeptRecordDto;
import com.example.userdept.models.entities.DepartmentModel;
import com.example.userdept.repositories.DeptRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeptController {

    @Autowired
    DeptRepository deptRepository;

    @PostMapping("/departments")
    public ResponseEntity<DepartmentModel> saveDept(@RequestBody @Valid DeptRecordDto deptRecordDto){
        var deptModel = new DepartmentModel();
        BeanUtils.copyProperties(deptRecordDto, deptModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(deptRepository.save(deptModel));
    }

}
