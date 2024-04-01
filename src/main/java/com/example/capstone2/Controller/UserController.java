package com.example.capstone2.Controller;

import com.example.capstone2.Api.ApiResponse;
import com.example.capstone2.Model.Category;
import com.example.capstone2.Model.User;
import com.example.capstone2.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @GetMapping("/get")
    public ResponseEntity getAllUsers(){
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        userService.addUser(user);
        return ResponseEntity.ok().body(new ApiResponse("user added successfully!"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @RequestBody @Valid User user, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        userService.updateUser(id, user);
        return ResponseEntity.ok().body("user updated successfully!");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.ok().body("user deleted successfully!");
    }

    @PutMapping("/assign/{supervisor_id}/{ticket_id}/{agent_id}")
    public ResponseEntity assignTicketToAgent(@PathVariable Integer supervisor_id, @PathVariable Integer ticket_id, @PathVariable Integer agent_id){
        userService.assignTicketToAgent(supervisor_id, ticket_id, agent_id);
        return ResponseEntity.ok().body("ticket assign to agent successfully!");
    }

    @PutMapping("/change-status/{ticket_id}/{status}")
    public ResponseEntity updateTicketStatus(@PathVariable Integer ticket_id, @PathVariable String status){
        userService.updateTicketStatus(ticket_id, status);
        return ResponseEntity.ok().body("status changed successfully!");
    }


    @PostMapping("/create-category-by-admin/{admin_id}")
    public ResponseEntity createCategoryByAdmin(@PathVariable Integer admin_id, @RequestBody @Valid Category category, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        userService.createCategoryByAdmin(admin_id, category);
        return ResponseEntity.ok().body("category created by admin successfully");
    }


    @PutMapping("/update-category-by-admin/{admin_id}/{category_id}")
    public ResponseEntity updateCategoryByAdmin(@PathVariable Integer admin_id, @PathVariable Integer category_id, @RequestBody @Valid Category updateCategory, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        userService.updateCategoryByAdmin(admin_id, category_id, updateCategory);
        return ResponseEntity.ok().body("category updated by admin successfully!");
    }

    @DeleteMapping("/delete-category-by-admin/{admin_id}/{category_id}")
    public ResponseEntity deleteCategoryByAdmin(@PathVariable Integer admin_id, @PathVariable Integer category_id){
        userService.deleteCategoryByAdmin(admin_id, category_id);
        return ResponseEntity.ok().body("category deleted by admin successfully!");
    }

    @PostMapping("/add-comment-by-agent/{agent_id}/{ticket_id}")
    public ResponseEntity addCommentByAgent(@PathVariable Integer agent_id, @PathVariable Integer ticket_id, @RequestBody String comment){
        userService.addCommentByAgent(agent_id, ticket_id, comment);
        return ResponseEntity.ok().body("comment added by agent successfully!");
    }

    @PostMapping("/add-comment-by-customer/{customer_id}/{ticket_id}")
    public ResponseEntity addCommentByCustomer(@PathVariable Integer customer_id, @PathVariable Integer ticket_id, @RequestBody String comment){
        userService.addCommentByCustomer(customer_id, ticket_id, comment);
        return ResponseEntity.ok().body("comment added by customer successfully!");
    }

    @PutMapping("/escalate/{agent_id}/{ticket_id}/{supervisor_id}")
    public ResponseEntity escalateTicket(@PathVariable Integer agent_id, @PathVariable Integer ticket_id, @PathVariable Integer supervisor_id){
        userService.escalateTicket(agent_id, ticket_id, supervisor_id);
        return ResponseEntity.ok().body("ticket escalated to supervisor successfully!");
    }

    @PutMapping("/deescalate/{supervisor_id}/{ticket_id}/{agent_id}")
    public ResponseEntity deescalateTicket(@PathVariable Integer supervisor_id, @PathVariable Integer ticket_id, @PathVariable Integer agent_id){
        userService.deescalateTicket(supervisor_id, ticket_id, agent_id);
        return ResponseEntity.ok().body("ticket deescalated to agent successfully!");
    }

    @GetMapping("/get-ticket-count-by-status")
    public ResponseEntity getTicketCountByStatus(){
        return ResponseEntity.ok().body(userService.getTicketCountByStatus());
    }

    @GetMapping("/get-ticket-count-by-priority")
    public ResponseEntity getTicketCountByPriority(){
        return ResponseEntity.ok().body(userService.getTicketCountByPriority());
    }


    @GetMapping("/find-closed-tickets/{customer_id}")
    public ResponseEntity findTicketsClosedForSpecificCustomer(@PathVariable Integer customer_id){
        return ResponseEntity.ok().body(userService.findTicketsClosedForSpecificCustomer(customer_id));
    }

    @GetMapping("/find-open-tickets/{customer_id}")
    public ResponseEntity findTicketsOpenForSpecificCustomer(@PathVariable Integer customer_id){
        return ResponseEntity.ok().body(userService.findTicketsOpenForSpecificCustomer(customer_id));
    }

    @GetMapping("/get-all-agents-tickets-status-count")
    public ResponseEntity getAgentTicketStatusCounts(){
        return ResponseEntity.ok().body(userService.getAgentTicketStatusCounts());
    }

    @GetMapping("/get-ticket-history/{ticket_id}")
    public ResponseEntity getTicketHistoryByTicketId(@PathVariable Integer ticket_id){
        return ResponseEntity.ok().body(userService.getTicketHistoryByTicketId(ticket_id));
    }


    @GetMapping("/get-tickets-assigned/{agent_id}")
    public ResponseEntity getTicketsAssignedToAgent(@PathVariable Integer agent_id){
        return ResponseEntity.ok().body(userService.getTicketsAssignedToAgent(agent_id));
    }



    @GetMapping("/get-comments/{ticket_id}")
    public ResponseEntity findCommentByTicketId(@PathVariable Integer ticket_id){
        return ResponseEntity.ok().body(userService.getAllCommentsByTicket(ticket_id));
    }















}
