package com.example.capstone2.Repository;

import com.example.capstone2.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.user_id=?1")
    User findUsersByUser_id(Integer id);

    List<User> findByRole(String role);


}
