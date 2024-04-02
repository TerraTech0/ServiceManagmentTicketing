package com.example.capstone2.Controller;

import com.example.capstone2.Model.Category;
import com.example.capstone2.Service.CategoryService;
import com.example.capstone2.Service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor

public class CategoryController {

    private final CategoryService categoryService;

    Logger logger = LoggerFactory.getLogger(CategoryController.class);
    @GetMapping("/get")
    public ResponseEntity getAllCategories(){
        logger.info("inside get all categories!");
        return ResponseEntity.ok().body(categoryService.getAllCategories());
    }

    @PostMapping("/add")
    public ResponseEntity addCategory(@RequestBody @Valid Category category){
        logger.info("inside add category!");
        categoryService.addCategory(category);
        return ResponseEntity.ok().body("categroy added successfully!");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@PathVariable Integer id, @RequestBody @Valid Category category){
        logger.info("inside update category!");
        categoryService.updateCategory(id, category);
        return ResponseEntity.ok().body("category updated successfully!");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable Integer id){
        logger.info("inside delete category!");
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().body("category deleted successfully!");
    }

}
