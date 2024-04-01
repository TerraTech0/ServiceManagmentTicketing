package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.Departments;
import com.example.capstone2.Repository.DepartmentsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DepartmentService {
    private final DepartmentsRepository departmentsRepository;

    public List<Departments> getAllDepartments(){
        return departmentsRepository.findAll();
    }

    public void addDepartment(Departments departments){
        departmentsRepository.save(departments);
    }

    public void updateDepartment(Integer id, Departments departments){
        Departments d = departmentsRepository.findDepartmentsByDepartment_id(id);
        if (d == null){
            throw new ApiException("department not found!");
        }
        d.setName(departments.getName());
        d.setDescription(departments.getDescription());
        departmentsRepository.save(d);
    }

    public void deleteDepartment(Integer id){
        Departments departments = departmentsRepository.findDepartmentsByDepartment_id(id);
        if (departments == null){
            throw new ApiException("department not found!");
        }
        departmentsRepository.delete(departments);
    }
}
