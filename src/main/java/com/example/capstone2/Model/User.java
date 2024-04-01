package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;

    @NotEmpty(message = "username can't be empty!")
    @Size(min = 3, message = "username should contains on 3 or more characters!")
    @Column(columnDefinition = "varchar(15) not null unique")
    private String username;

    @NotEmpty(message = "password should not be empty!")
    @Column(columnDefinition = "varchar(255) not null")
    private String password;

    @Email
    @NotEmpty(message = "email can't be empty!")
    @Column(columnDefinition = "varchar(50) not null unique")
    private String email;

    @NotEmpty(message = "role can't be empty!")
    @Pattern(regexp = "^(Admin|Customer|Agent|Supervisor)$")
    @Column(columnDefinition = "varchar(10) not null check(role='admin' or role='customer' or role='agent' or role='supervisor')")
    private String role;
}
