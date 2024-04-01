package com.example.capstone2.Repository;

import com.example.capstone2.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("select c from Category c where c.category_id=?1")
    Category findCategoriesByCategory_id(Integer id);
}
