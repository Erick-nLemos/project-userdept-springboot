package com.example.userdept.dtos;

import com.example.userdept.models.entities.DepartmentModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRecordDto(@NotBlank String name, @NotBlank String email, DepartmentModel department) {

}
