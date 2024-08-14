package com.example.userdept.models.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name= "tb_department")
public class DepartmentModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idDept;
    private String name;

    public UUID getId() {
        return idDept;
    }

    public void setId(UUID idDept) {
        this.idDept = idDept;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
