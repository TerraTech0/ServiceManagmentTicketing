package com.example.capstone2.Controller;

import com.example.capstone2.Api.ApiResponse;
import com.example.capstone2.Model.Category;
import com.example.capstone2.Model.User;
import com.example.capstone2.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    //type slf4j
    Logger logger = LoggerFactory.getLogger(UserController.class);
    @GetMapping("/get")
    public ResponseEntity getAllUsers(){
        logger.info("insdie get all users! - User Controller");
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user){
        logger.info("insdie add user! - User Controller");
        userService.addUser(user);
        return ResponseEntity.ok().body(new ApiResponse("user added successfully!"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @RequestBody @Valid User user){
        logger.info("insdie update user! - User Controller");
        userService.updateUser(id, user);
        return ResponseEntity.ok().body("user updated successfully!");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        logger.info("inside delete user! - User Controller");
        userService.deleteUser(id);
        return ResponseEntity.ok().body("user deleted successfully!");
    }

    @PutMapping("/assign/{supervisor_id}/{ticket_id}/{agent_id}")
    public ResponseEntity assignTicketToAgent(@PathVariable Integer supervisor_id, @PathVariable Integer ticket_id, @PathVariable Integer agent_id){
        logger.info("inside assign ticket to agent by supervisor! - User Controller");
        userService.assignTicketToAgent(supervisor_id, ticket_id, agent_id);
        return ResponseEntity.ok().body("ticket assign to agent successfully!");
    }

    @PutMapping("/change-status/{ticket_id}/{status}")
    public ResponseEntity updateTicketStatus(@PathVariable Integer ticket_id, @PathVariable String status){
        logger.info("inside change status! - User Controller");
        userService.updateTicketStatus(ticket_id, status);
        return ResponseEntity.ok().body("status changed successfully!");
    }


    @PostMapping("/create-category-by-admin/{admin_id}")
    public ResponseEntity createCategoryByAdmin(@PathVariable Integer admin_id, @RequestBody @Valid Category category){
        logger.info("inside create category by admin! - User Controller");
        userService.createCategoryByAdmin(admin_id, category);
        return ResponseEntity.ok().body("category created by admin successfully");
    }


    @PutMapping("/update-category-by-admin/{admin_id}/{category_id}")
    public ResponseEntity updateCategoryByAdmin(@PathVariable Integer admin_id, @PathVariable Integer category_id, @RequestBody @Valid Category updateCategory){
        logger.info("inside update category by admin! - User Controller");
        userService.updateCategoryByAdmin(admin_id, category_id, updateCategory);
        return ResponseEntity.ok().body("category updated by admin successfully!");
    }

    @DeleteMapping("/delete-category-by-admin/{admin_id}/{category_id}")
    public ResponseEntity deleteCategoryByAdmin(@PathVariable Integer admin_id, @PathVariable Integer category_id){
        logger.info("inside delete category by admin! - User Controller");
        userService.deleteCategoryByAdmin(admin_id, category_id);
        return ResponseEntity.ok().body("category deleted by admin successfully!");
    }

    @PostMapping("/add-comment-by-agent/{agent_id}/{ticket_id}")
    public ResponseEntity addCommentByAgent(@PathVariable Integer agent_id, @PathVariable Integer ticket_id, @RequestBody String comment){
        logger.info("inside add comment by agent! - User Controller");
        userService.addCommentByAgent(agent_id, ticket_id, comment);
        return ResponseEntity.ok().body("comment added by agent successfully!");
    }

    @PostMapping("/add-comment-by-customer/{customer_id}/{ticket_id}")
    public ResponseEntity addCommentByCustomer(@PathVariable Integer customer_id, @PathVariable Integer ticket_id, @RequestBody String comment){
        logger.info("inside add comment by customer! - User Controller");
        userService.addCommentByCustomer(customer_id, ticket_id, comment);
        return ResponseEntity.ok().body("comment added by customer successfully!");
    }

    @PutMapping("/escalate/{agent_id}/{ticket_id}/{supervisor_id}")
    public ResponseEntity escalateTicket(@PathVariable Integer agent_id, @PathVariable Integer ticket_id, @PathVariable Integer supervisor_id){
        logger.info("inside escalate ticket from agent to supervisor! - User Controller");
        userService.escalateTicket(agent_id, ticket_id, supervisor_id);
        return ResponseEntity.ok().body("ticket escalated to supervisor successfully!");
    }

    @PutMapping("/deescalate/{supervisor_id}/{ticket_id}/{agent_id}")
    public ResponseEntity deescalateTicket(@PathVariable Integer supervisor_id, @PathVariable Integer ticket_id, @PathVariable Integer agent_id){
        logger.info("inside deescalate ticket from supervisor to agent! - User Controller");
        userService.deescalateTicket(supervisor_id, ticket_id, agent_id);
        return ResponseEntity.ok().body("ticket deescalated to agent successfully!");
    }

    @GetMapping("/get-ticket-count-by-status")
    public ResponseEntity getTicketCountByStatus(){
        logger.info("inside get ticket count by status! - User Controller");
        return ResponseEntity.ok().body(userService.getTicketCountByStatus());
    }

    @GetMapping("/get-ticket-count-by-priority")
    public ResponseEntity getTicketCountByPriority(){
        logger.info("inside get ticket count by priority! - User Controller");
        return ResponseEntity.ok().body(userService.getTicketCountByPriority());
    }


    @GetMapping("/find-closed-tickets/{customer_id}")
    public ResponseEntity findTicketsClosedForSpecificCustomer(@PathVariable Integer customer_id){
        logger.info("inside find closed tickets through customer id! - User Controller");
        return ResponseEntity.ok().body(userService.findTicketsClosedForSpecificCustomer(customer_id));
    }

    @GetMapping("/find-open-tickets/{customer_id}")
    public ResponseEntity findTicketsOpenForSpecificCustomer(@PathVariable Integer customer_id){
        logger.info("inside find open tickets through customer id - User Controller");
        return ResponseEntity.ok().body(userService.findTicketsOpenForSpecificCustomer(customer_id));
    }

    @GetMapping("/get-all-agents-tickets-status-count")
    public ResponseEntity getAgentTicketStatusCounts(){
        logger.info("inside get all agents ticket status count! - User Controller");
        return ResponseEntity.ok().body(userService.getAgentTicketStatusCounts());
    }

    @GetMapping("/get-ticket-history/{ticket_id}")
    public ResponseEntity getTicketHistoryByTicketId(@PathVariable Integer ticket_id){
        logger.info("inside get ticket history by ticket id! - User Controller");
        return ResponseEntity.ok().body(userService.getTicketHistoryByTicketId(ticket_id));
    }


    @GetMapping("/get-tickets-assigned/{agent_id}")
    public ResponseEntity getTicketsAssignedToAgent(@PathVariable Integer agent_id){
        logger.info("inside get tickets assigned to agent by agent id! - User Controller");
        return ResponseEntity.ok().body(userService.getTicketsAssignedToAgent(agent_id));
    }



    @GetMapping("/get-comments/{ticket_id}")
    public ResponseEntity findCommentByTicketId(@PathVariable Integer ticket_id){
        logger.info("inside get comments by ticket id! - User Controller");
        return ResponseEntity.ok().body(userService.getAllCommentsByTicket(ticket_id));
    }















}
