package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class Departments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer department_id;
    @NotEmpty(message = "department name can't be empty!")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String name;
    @NotEmpty(message = "description can't be null!")
    @Column(columnDefinition = "text not null")
    private String description;
}
