package com.example.capstone2.Controller;

import com.example.capstone2.Model.Ticket;
import com.example.capstone2.Service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ticket")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;


    @GetMapping("/get")
    public ResponseEntity getAllTickets(){
        return ResponseEntity.ok().body(ticketService.getAllTickets());
    }


    @PostMapping("/add")
    public ResponseEntity addTicket(@RequestBody @Valid Ticket ticket, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        ticketService.addTicket(ticket);
        return ResponseEntity.ok().body("ticket added successfully!");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateTicket(@PathVariable Integer id, @RequestBody @Valid Ticket ticket, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        ticketService.updateTicket(id, ticket);
        return ResponseEntity.ok().body("ticket updated successfully!");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTicket(@PathVariable Integer id){
        ticketService.deleteTicket(id);
        return ResponseEntity.ok().body("ticket deleted successfully!");
    }

    @PutMapping("/escalate-ticket/{ticket_id}")
    public ResponseEntity escalateTicketPriority(@PathVariable Integer ticket_id){
        ticketService.escalateTicketPriority(ticket_id);
        return ResponseEntity.ok().body("ticket escalated successfully!");
    }

    @PutMapping("/deescalate-ticket/{ticket_id}")
    public ResponseEntity deescalateTicketPriority(@PathVariable Integer ticket_id){
        ticketService.deescalateTicketPriority(ticket_id);
        return ResponseEntity.ok().body("ticket deescalated successfully!");
    }
}
