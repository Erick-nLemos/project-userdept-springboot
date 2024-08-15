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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @GetMapping("/departments")
    public ResponseEntity<List<DepartmentModel>> getAll(){
        List<DepartmentModel> deptList = deptRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(deptList);
    }

    @GetMapping("/departments/{idDept}")
    public ResponseEntity<Object> getOne(@PathVariable("idDept") UUID idDept){
        Optional<DepartmentModel> deptOpt = deptRepository.findById(idDept);
        if (deptOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Department not Found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(deptOpt.get());
    }

    @PutMapping("/departments/{idDept}")
    public ResponseEntity<Object> updateDept(@PathVariable UUID idDept, @RequestBody @Valid DeptRecordDto deptRecordDto){
        Optional<DepartmentModel> deptOpt = deptRepository.findById(idDept);
        if (deptOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Department not Found");
        }
        var deptModel = deptOpt.get();
        BeanUtils.copyProperties(deptRecordDto, deptModel);
        return ResponseEntity.status(HttpStatus.OK).body(deptRepository.save(deptModel));
    }
}
