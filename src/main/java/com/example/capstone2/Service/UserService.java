package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Advice.ControllerAdvice;
import com.example.capstone2.Model.*;
import com.example.capstone2.Repository.CategoryRepository;
import com.example.capstone2.Repository.CommentRepository;
import com.example.capstone2.Repository.TicketRepository;
import com.example.capstone2.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;
    private final CategoryRepository categoryRepository;
    private final CommentRepository commentRepository;
    private final ControllerAdvice controllerAdvice;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(Integer id, User user) {
        User u = userRepository.findUsersByUser_id(id);
        if (u == null) {
            throw new ApiException("user not found!");
        }
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        u.setEmail(user.getEmail());
        u.setRole(user.getRole());
        userRepository.save(u);
    }

    public void deleteUser(Integer id) {
        User user = userRepository.findUsersByUser_id(id);
        if (user == null) {
            throw new ApiException("user not found!");
        }
        userRepository.delete(user);
    }

    //method to let admin assign ticket to agent
    public void assignTicketToAgent(Integer supervisor_id, Integer ticket_id, Integer agent_id) {
        User supervisor = userRepository.findUsersByUser_id(supervisor_id);
        if (supervisor == null || !supervisor.getRole().equals("Supervisor")) {
            throw new ApiException("Only supervisors can assign tickets!");
        }
        Ticket ticket = ticketRepository.findTicketByTicket_id(ticket_id);
        if (ticket == null) {
            throw new ApiException("Ticket not found!");
        }

        User agent = userRepository.findUsersByUser_id(agent_id);
        if (agent == null || !agent.getRole().equals("Agent")) {
            throw new ApiException("Agent not found or user not agent");
        }

        ticket.setUser_id(agent.getUser_id());
        ticketRepository.save(ticket);

    }

    //method to update Ticket Status
    public void updateTicketStatus(Integer ticket_id, String status) {
        Ticket ticket = ticketRepository.findTicketByTicket_id(ticket_id);
        if (ticket == null) {
            throw new ApiException("Ticket not found!");
        }
        if (status.equals("Pending") || status.equals("In Progress") || status.equals("Resolved") ||
                status.equals("Open") || status.equals("On Hold") || status.equals("Assigned") || status.equals("Closed")
                || status.equals("Reopened") || status.equals("Escalated") || status.equals("Cancelled")) {
            ticket.setStatus(status);
            ticket.setUpdate_at(LocalDateTime.now()); // update_at must be changed every time this method is run
            ticketRepository.save(ticket);
        } else {
            throw new ApiException("Status not found!");
        }
    }


    // Method to create a new category by admin
    public void createCategoryByAdmin(Integer admin_id, Category category) {
        User admin = userRepository.findUsersByUser_id(admin_id);
        if (admin == null || !admin.getRole().equals("Admin")) {
            throw new ApiException("Only admins can create a category");
        }
        categoryRepository.save(category);
    }

    // Method to update the category by admin
    public void updateCategoryByAdmin(Integer admin_id, Integer category_id, Category updatedCategory) {
        User admin = userRepository.findUsersByUser_id(admin_id);
        if (admin == null || !admin.getRole().equals("Admin")) {
            throw new ApiException("Only admins can update a category");
        }
        Category category = categoryRepository.findCategoriesByCategory_id(category_id);
        if (category == null) {
            throw new ApiException("Category not found!");
        }
        category.setName(updatedCategory.getName());
        categoryRepository.save(category);
    }

    // Method to delete the category by admin
    public void deleteCategoryByAdmin(Integer admin_id, Integer category_id) {
        User admin = userRepository.findUsersByUser_id(admin_id);
        if (admin == null || !admin.getRole().equals("Admin")) {
            throw new ApiException("Only admins can delete a category");
        }
        categoryRepository.deleteById(category_id);
    }


    //    add comment to ticket by agent
    public void addCommentByAgent(Integer agent_id, Integer ticket_id, String comment) {
        User agent = userRepository.findUsersByUser_id(agent_id);
        if (agent == null || !agent.getRole().equals("Agent")) {
            throw new ApiException("agent not found!");
        }
        Ticket ticket = ticketRepository.findTicketByTicket_id(ticket_id);
        if (ticket == null) {
            throw new ApiException("ticket not found!");
        }
        Comment newComment = new Comment();
        newComment.setUser_id(agent_id);
        newComment.setTicket_id(ticket_id);
        newComment.setComment(comment);
        commentRepository.save(newComment);
    }

    //add comment by customer
    public void addCommentByCustomer(Integer customer_id, Integer ticket_id, String comment) {
        User customer = userRepository.findUsersByUser_id(customer_id);
        if (customer == null || !customer.getRole().equals("Customer")) {
            throw new ApiException("customer not found or user not customer!");
        }
        Ticket ticket = ticketRepository.findTicketByTicket_id(ticket_id);
        if (ticket == null) {
            throw new ApiException("ticket not found!");
        }
        Comment newComment = new Comment();
        newComment.setUser_id(customer_id);
        newComment.setTicket_id(ticket_id);
        newComment.setComment(comment);
        commentRepository.save(newComment);
    }

    public List<Comment> getAllCommentsByTicket(Integer ticket_id) {
        // Find the ticket by its ID
        Ticket ticket = ticketRepository.findTicketByTicket_id(ticket_id);
        if (ticket == null) {
            throw new ApiException("ticket not found!");
        }

        // Retrieve all comments associated with the ticket
        List<Comment> comments = commentRepository.findAllByTicket_id(ticket_id);
        return comments;
    }



    //method to escalate Ticket
    public void escalateTicket(Integer agent_id, Integer ticket_id, Integer supervisor_id) {
        User agent = userRepository.findUsersByUser_id(agent_id);
        if (agent == null || !agent.getRole().equals("Agent")) {
            throw new ApiException("Agent not found or user not agent!");
        }
        Ticket ticket = ticketRepository.findTicketByTicket_id(ticket_id);
        if (ticket == null) {
            throw new ApiException("Ticket not found!");
        }
        User supervisor = userRepository.findUsersByUser_id(supervisor_id);
        if (supervisor == null || !supervisor.getRole().equals("Supervisor")) {
            throw new ApiException("Supervisor not found or user not supervisor!");
        }
        ticket.setStatus("Escalated");
        ticket.setUser_id(agent_id);
        ticketRepository.save(ticket);
    }

    //method to Deescalate ticket by supervisor
    public void deescalateTicket(Integer supervisor_id, Integer ticket_id, Integer agent_id) {
        User supervisor = userRepository.findUsersByUser_id(supervisor_id);
        if (supervisor == null || !supervisor.getRole().equals("Supervisor")) {
            throw new ApiException("Supervisor not found or user not supervisor!");
        }
        Ticket ticket = ticketRepository.findTicketByTicket_id(ticket_id);
        if (ticket == null) {
            throw new ApiException("Ticket not found!");
        }
        User agent = userRepository.findUsersByUser_id(agent_id);
        if (agent == null || !agent.getRole().equals("Agent")) {
            throw new ApiException("Agent not found or user not agent!");
        }
        ticket.setTicket_id(ticket_id);
        ticket.setUser_id(agent_id);
        ticket.setStatus("Deescalated");
        ticketRepository.save(ticket);
    }


    // method to get ticket count by status
    public Map<String, Integer> getTicketCountByStatus() {
        List<Ticket> tickets = ticketRepository.findAll();
        Map<String, Integer> ticketCountByStatus = new HashMap<>();

        for (Ticket ticket : tickets) {
            String status = ticket.getStatus();
            if (ticketCountByStatus.containsKey(status)) {
                ticketCountByStatus.put(status, ticketCountByStatus.get(status) + 1);
            } else {
                ticketCountByStatus.put(status, 1);
            }
        }
        return ticketCountByStatus;
    }

    // method to get ticket count by priority
    public Map<String, Integer> getTicketCountByPriority() {
        List<Ticket> tickets = ticketRepository.findAll();
        Map<String, Integer> ticketCountByPriority = new HashMap<>();

        for (Ticket ticket : tickets) {
            String priority = ticket.getPriority();
            if (ticketCountByPriority.containsKey(priority)) {
                ticketCountByPriority.put(priority, ticketCountByPriority.get(priority) + 1);
            } else {
                ticketCountByPriority.put(priority, 1);
            }
        }

        return ticketCountByPriority;
    }


    //this method need to be fixed!
    //method to calculate the time between time of create a new ticket and time for the last ticket added!
    public void timeForCreateTicket(Ticket ticket, LocalDateTime currentTime){
        LocalDateTime specificTime = ticket.getCreate_at();
        if (currentTime.isBefore(specificTime.plusMinutes(5))) {
            throw new ApiException("sry! you can't create ticket now, please wait 5 min!");
        }
    }



    // method to find all tickets that are closed for specific customer
    public List<Ticket> findTicketsClosedForSpecificCustomer(Integer customer_id) {
        User customer = userRepository.findUsersByUser_id(customer_id);
        if (customer == null || !customer.getRole().equals("Customer")){
            throw new ApiException("Customer not found or user isn't a customer!");
        }
        List<Ticket> tickets = ticketRepository.findTicketsByUser_idAndStatus(customer.getUser_id(), "Closed");
        if (tickets.isEmpty()){
            throw new ApiException("Customer doesn't have any closed tickets!");
        }
        return tickets;
    }



    // method to get all tickets taht are Opent for specific customer
    public List<Ticket> findTicketsOpenForSpecificCustomer(Integer customer_id) {
        User customer = userRepository.findUsersByUser_id(customer_id);
        if (customer == null || !customer.getRole().equals("Customer")){
            throw new ApiException("Customer not found or user isn't a customer!");
        }
        List<Ticket> tickets = ticketRepository.findTicketsByUser_idAndStatus(customer.getUser_id(), "Open");
        if (tickets.isEmpty()){
            throw new ApiException("Customer doesn't have any open tickets!");
        }
        return tickets;
    }



    /*
    method to let supervisor get all agent's tickets and count the status like:
    Agent1:
    Opened = 2
    Closed = 1
    and so on!
     */
    public Map<String, Map<String, Long>> getAgentTicketStatusCounts() {
        // Get all agents
        List<User> agents = userRepository.findByRole("Agent");

        Map<String, Map<String, Long>> agentTicketStatusCounts = new HashMap<>();

        // Iterate over each agent
        for (User agent : agents) {
            // Get all tickets for the current agent
            List<Ticket> agentTickets = ticketRepository.findTicketsByUser_id(agent.getUser_id());

            // Group tickets by status and count them
            Map<String, Long> statusCounts = agentTickets.stream()
                    .collect(Collectors.groupingBy(Ticket::getStatus, Collectors.counting()));

            // Add the counts to the result map
            agentTicketStatusCounts.put(agent.getUsername(), statusCounts);
        }

        return agentTicketStatusCounts;
    }





    //method to get ticket history by ticket id
    public List<Ticket> getTicketHistoryByTicketId(Integer ticket_id) {
        return ticketRepository.findHistoriesByTicketId(ticket_id);
    }


    //method to get ticket that are assigned for agent!
    public List<Ticket> getTicketsAssignedToAgent(Integer agent_id) {
        User agent = userRepository.findUsersByUser_id(agent_id);
        if (agent == null || !agent.getRole().equals("Agent")) {
            throw new ApiException("Agent not found or user not agent!");
        }
        return ticketRepository.findTicketsByUser_id(agent_id);
    }

    /*
    method to get ticket's category by ticket id , need relation with category!
    method to get ticket's department by ticket id, need relation with department!
    */

//    public Category getTicketCategoryByTicketId(Integer ticket_id) {
//        Ticket ticket = ticketRepository.findTicketByTicket_id(ticket_id);
//        if (ticket == null){
//            throw new ApiException("Ticket not found!");
//        }
//        return ticket.getCategory();
//    }
//


//    public Departments getTicketDepartmentByTicketId(Integer ticket_id) {
//        Ticket ticket = ticketRepository.findTicketByTicket_id(ticket_id);
//        if (ticket == null){
//            throw new ApiException("Ticket not found!");
//        }
//        return ticket.getDepartment();
//    }

    //do it late if there's time !






















}
