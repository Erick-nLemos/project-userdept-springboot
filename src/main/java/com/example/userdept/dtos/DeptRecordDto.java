package com.example.userdept.dtos;


import jakarta.validation.constraints.NotBlank;

public record DeptRecordDto(@NotBlank String name) {

}
