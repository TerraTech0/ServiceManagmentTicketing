package com.example.capstone2.Model;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ticket_id;

    @NotNull(message = "user id can't be null!")
    @Column(columnDefinition = "int not null")
    private Integer user_id;

    @NotEmpty(message = "title can't be empty!")
    @Column(columnDefinition = "varchar(100) not null")
    private String title;

    @NotEmpty(message = "description can't be empty!")
    @Column(columnDefinition = "text not null") //changed the varchar int text type
    private String description;

    @NotEmpty(message = "status can't be null!")
    @Pattern(regexp = "^(Pending|Open|On Hold|Assigned|In Progress|Closed|Escalated|Deescalated|Reopened|Accepted|Rejected)$")
    @Column(columnDefinition = "varchar(15) not null CHECK (status IN ('Pending','Open', 'On Hold', 'Assigned', 'In Progress', 'Closed', 'Escalated', 'Deescalated', 'Reopened', 'Accepted', 'Rejected'))")

    private String status;


    @NotEmpty(message = "priority can't be empty!")
    @Pattern(regexp = "^(Critical|High|Medium|Low)$")
    @Column(columnDefinition = "varchar(20) not null check(priority='Critical' or priority='High' or priority='Medium' or priority='Low')")
    private String priority;

    private LocalDateTime create_at;

    private LocalDateTime update_at;

    @PrePersist // when i create a new model this method will run automatically
    public void onCreate(){
        create_at=LocalDateTime.now();
    }

}



