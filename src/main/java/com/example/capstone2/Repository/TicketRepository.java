package com.example.capstone2.Repository;

import com.example.capstone2.Model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    @Query("select c from Ticket c where c.ticket_id=?1")
    Ticket findTicketByTicket_id(Integer id);


    //trying this code with limit in last of JPQL query
    @Query("SELECT t FROM Ticket t WHERE t.user_id = :userId ORDER BY t.create_at DESC limit 1")
    List<Ticket> findLastTicketByUserId(@Param("userId") Integer userId);

    @Query("select t from Ticket t where t.user_id=?1 order by t.create_at desc limit 1")
    Ticket findTicketsByUser_idOrderByCreate_atDesc(Integer user_id);



//    @Query("SELECT t FROM Ticket t WHERE t.user_id = :userId ORDER BY t.create_at DESC")
//    Optional<Ticket> findLastTicketByUserId(@Param("userId") Integer userId);

    @Query("select t from Ticket t where t.user_id=?1 and t.status=?2")
    List<Ticket> findTicketsByUser_idAndStatus(Integer user_id, String  status);

    @Query("SELECT t FROM Ticket t WHERE t.ticket_id = :ticketId")
    List<Ticket> findHistoriesByTicketId(@Param("ticketId") Integer ticketId);

    @Query("select t from Ticket t where t.user_id=?1")
    List<Ticket> findTicketsByUser_id(Integer agent_id);
}
