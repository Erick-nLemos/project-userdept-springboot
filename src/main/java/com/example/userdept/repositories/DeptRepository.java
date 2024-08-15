package com.example.userdept.repositories;

import com.example.userdept.models.entities.DepartmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DeptRepository extends JpaRepository<DepartmentModel, UUID> {

}
