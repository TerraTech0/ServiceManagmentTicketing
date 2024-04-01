package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.Category;
import com.example.capstone2.Repository.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoriesRepository;

    public List<Category> getAllCategories(){
        return categoriesRepository.findAll();
    }

    public void addCategory(Category categories){
        categoriesRepository.save(categories);
    }

    public void updateCategory(Integer id, Category categories){
        Category c = categoriesRepository.findCategoriesByCategory_id(id);
        if (c == null){
            throw new ApiException("category not found!");
        }
        c.setName(categories.getName());
        categoriesRepository.save(c);
    }

    public void deleteCategory(Integer id){
        Category categories = categoriesRepository.findCategoriesByCategory_id(id);
        if (categories == null){
            throw new ApiException("category not found!");
        }
        categoriesRepository.delete(categories);
    }


    // i have to add more 15 endpoints and they must be a logic endpoints!
}
