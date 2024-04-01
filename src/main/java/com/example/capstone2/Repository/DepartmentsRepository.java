package com.example.capstone2.Repository;

import com.example.capstone2.Model.Departments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentsRepository extends JpaRepository<Departments, Integer> {

    @Query("select d from Departments d where d.department_id=?1")
    Departments findDepartmentsByDepartment_id(Integer id);
}
