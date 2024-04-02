package com.example.capstone2.Controller;

import com.example.capstone2.Model.Comment;
import com.example.capstone2.Model.User;
import com.example.capstone2.Service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    Logger logger = LoggerFactory.getLogger(CommentController.class);

    @GetMapping("/get")
    public ResponseEntity getAllComments(){
        logger.info("insdie get all comments!");
        return ResponseEntity.ok().body(commentService.getAllComments());
    }

    @PostMapping("/add")
    public ResponseEntity addComment(@RequestBody @Valid Comment comment){
        logger.info("inside add comment!");
        commentService.addComment(comment);
        return ResponseEntity.ok().body("comment added successfully!");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateComment(@PathVariable Integer id, @RequestBody @Valid Comment comment){
        logger.info("inside update comment!");
        commentService.updateComment(id, comment);
        return ResponseEntity.ok().body("comment updated successfully!");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteComment(@PathVariable Integer id){
        logger.info("inside delete comment!");
        commentService.deleteComment(id);
        return ResponseEntity.ok().body("comment deleted successfully!");
    }
}
