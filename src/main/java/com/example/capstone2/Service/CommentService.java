package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.Comment;
import com.example.capstone2.Repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public List<Comment> getAllComments(){
        return commentRepository.findAll();
    }

    public void addComment(Comment comment){
        commentRepository.save(comment);
    }

    public void updateComment(Integer id, Comment comment){
        Comment c = commentRepository.findCommentByComment_id(id);
        if (c == null){
            throw new ApiException("comment not found!");
        }
        c.setComment(comment.getComment());
        c.setTicket_id(comment.getTicket_id());
        c.setUser_id(comment.getUser_id());
        commentRepository.save(c);
    }

    public void deleteComment(Integer id){
        Comment comment = commentRepository.findCommentByComment_id(id);
        if (comment == null){
            throw new ApiException("comment not found!");
        }
        commentRepository.delete(comment);
    }


    //I have to write more 15 endpoints and make it logic endpoints!
}
