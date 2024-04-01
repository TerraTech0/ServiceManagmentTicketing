package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.internal.build.AllowNonPortable;

import java.sql.Date;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer comment_id;

    @NotNull(message = "complaint id can't be null!")
    @Column(columnDefinition = "int not null")
    private Integer ticket_id;

    @NotNull(message = "user id can't be null!")
    @Column(columnDefinition = "int not null")
    private Integer user_id;

//    @NotEmpty(message = "comment can't be mepty!")
    @Column(columnDefinition = "text")
    private String comment;

    //it gives me null when i create a comment by agent or customer! need to be fixed!
    private LocalDateTime create_at;
    @PrePersist // when i create a new model this method will run automatically
    public void onCreate(){
        create_at=LocalDateTime.now();
    }
}
