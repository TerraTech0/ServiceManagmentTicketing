package com.example.capstone2.Controller;

import com.example.capstone2.Model.Ticket;
import com.example.capstone2.Service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ticket")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;


    Logger logger = LoggerFactory.getLogger(TicketController.class);
    @GetMapping("/get")
    public ResponseEntity getAllTickets(){
        logger.info("insdie get all tickets! - Ticket Controller");
        return ResponseEntity.ok().body(ticketService.getAllTickets());
    }


    @PostMapping("/add")
    public ResponseEntity addTicket(@RequestBody @Valid Ticket ticket){
        logger.info("inside add ticket! - Ticket Controller");
        ticketService.addTicket(ticket);
        return ResponseEntity.ok().body("ticket added successfully!");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateTicket(@PathVariable Integer id, @RequestBody @Valid Ticket ticket){
        logger.info("inside update ticket! - Ticket Controller");
        ticketService.updateTicket(id, ticket);
        return ResponseEntity.ok().body("ticket updated successfully!");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTicket(@PathVariable Integer id){
        logger.info("inside delete ticket! - Ticket Controller");
        ticketService.deleteTicket(id);
        return ResponseEntity.ok().body("ticket deleted successfully!");
    }

    @PutMapping("/escalate-ticket/{ticket_id}")
    public ResponseEntity escalateTicketPriority(@PathVariable Integer ticket_id){
        logger.info("inside escalate ticket via ticket id! - Ticket Controller");
        ticketService.escalateTicketPriority(ticket_id);
        return ResponseEntity.ok().body("ticket escalated successfully!");
    }

    @PutMapping("/deescalate-ticket/{ticket_id}")
    public ResponseEntity deescalateTicketPriority(@PathVariable Integer ticket_id){
        logger.info("inside deescalate ticket through ticket id! - Ticket Controller");
        ticketService.deescalateTicketPriority(ticket_id);
        return ResponseEntity.ok().body("ticket deescalated successfully!");
    }
}
