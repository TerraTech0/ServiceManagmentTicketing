package com.example.capstone2.Controller;

import com.example.capstone2.Model.Departments;
import com.example.capstone2.Service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/department")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping("/get")
    public ResponseEntity getAllDepartments(){
        return ResponseEntity.ok().body(departmentService.getAllDepartments());
    }

    @PostMapping("/add")
    public ResponseEntity addDepartment(@RequestBody @Valid Departments departments, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        departmentService.addDepartment(departments);
        return ResponseEntity.ok().body("department added succesfully!");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateDepartment(@PathVariable Integer id, @RequestBody @Valid Departments departments, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        departmentService.updateDepartment(id, departments);
        return ResponseEntity.ok().body("user updated successfully!");
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteDepartment(@PathVariable Integer id){
        departmentService.deleteDepartment(id);
        return ResponseEntity.ok().body("department deleted successfully!");
    }
}
