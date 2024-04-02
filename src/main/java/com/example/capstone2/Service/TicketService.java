package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.Ticket;
import com.example.capstone2.Model.User;
import com.example.capstone2.Repository.TicketRepository;
import com.example.capstone2.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public List<Ticket> getAllTickets(){
        return ticketRepository.findAll();
    }

    public void addTicket(Ticket newTicket){
        // get user by ticket.user_id
        Integer user_id = newTicket.getUser_id();
        User user = userRepository.findUsersByUser_id(user_id);
        if (user == null){
            throw new ApiException("user not found!");
        }
        if (!user.getRole().equals("Customer")){
            throw new ApiException("Only customer can create ticket!");
        }

        List<Ticket> ticketList = ticketRepository.findLastTicketByUserId(newTicket.getUser_id());
        Ticket ticket = null;
        if (!ticketList.isEmpty()) {
            ticket = ticketList.get(ticketList.size()-1);
        }

        // check the time limit
        if(ticket != null) {
            userService.timeForCreateTicket(ticket, LocalDateTime.now());
        }
        ticketRepository.save(newTicket);
    }


    public void updateTicket(Integer id, Ticket ticket){
        Ticket c = ticketRepository.findTicketByTicket_id(id);
        if (c == null){
            throw new ApiException("user not found!");
        }
        c.setUser_id(ticket.getUser_id());
        c.setTitle(ticket.getTitle());
        c.setDescription(ticket.getDescription());
        c.setPriority(ticket.getPriority());
        c.setStatus(ticket.getStatus());
        c.setUpdate_at(ticket.getUpdate_at());
        ticketRepository.save(c);
    }

    public void deleteTicket(Integer id){
        Ticket ticket = ticketRepository.findTicketByTicket_id(id);
        if (ticket == null){
            throw new ApiException("Ticket not found!");
        }
        ticketRepository.delete(ticket);
    }

    // Method to escalate ticket priority
    public void escalateTicketPriority(Integer ticket_id) {
        Ticket ticket = ticketRepository.findTicketByTicket_id(ticket_id);
        if (ticket == null) {
            throw new ApiException("Ticket not found!");
        }
        if (ticket.getPriority().equals("Low")) {
            ticket.setPriority("Medium");
        } else if (ticket.getPriority().equals("Medium")) {
            ticket.setPriority("High");
        } else if (ticket.getPriority().equals("High")) {
            ticket.setPriority("Critical");
        }
        ticketRepository.save(ticket);
    }

    //deescalate ticket
    public void deescalateTicketPriority(Integer ticket_id){
        Ticket ticket = ticketRepository.findTicketByTicket_id(ticket_id);
        if (ticket == null){
            throw new ApiException("Ticket not found!");
        }
        if (ticket.getPriority().equals("Critical")){
            ticket.setPriority("High");
        } else if (ticket.getPriority().equals("High")){
            ticket.setPriority("Medium");
        } else if (ticket.getPriority().equals("Medium")){
            ticket.setPriority("Low");
        }
        ticketRepository.save(ticket);
    }


}
