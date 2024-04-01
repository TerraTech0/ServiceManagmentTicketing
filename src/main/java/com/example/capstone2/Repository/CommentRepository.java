package com.example.capstone2.Repository;

import com.example.capstone2.Model.Comment;
import com.example.capstone2.Model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Query("select c from Comment c where c.ticket_id=?1")
    Comment findCommentByComment_id(Integer id);

    @Query("select c from Comment c where c.ticket_id=?1")
    List<Comment> findAllByTicket_id(Integer ticket_id);
}
