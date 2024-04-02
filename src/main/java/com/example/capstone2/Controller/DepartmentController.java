package com.example.capstone2.Controller;

import com.example.capstone2.Model.Departments;
import com.example.capstone2.Service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/department")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    Logger logger = LoggerFactory.getLogger(DepartmentController.class);
    @GetMapping("/get")
    public ResponseEntity getAllDepartments(){
        logger.info("insdie get all departments!");
        return ResponseEntity.ok().body(departmentService.getAllDepartments());
    }

    @PostMapping("/add")
    public ResponseEntity addDepartment(@RequestBody @Valid Departments departments){
        logger.info("inside add department!");
        departmentService.addDepartment(departments);
        return ResponseEntity.ok().body("department added succesfully!");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateDepartment(@PathVariable Integer id, @RequestBody @Valid Departments departments){
        logger.info("inside update department!");
        departmentService.updateDepartment(id, departments);
        return ResponseEntity.ok().body("user updated successfully!");
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteDepartment(@PathVariable Integer id){
        logger.info("inside delete department!");
        departmentService.deleteDepartment(id);
        return ResponseEntity.ok().body("department deleted successfully!");
    }
}
