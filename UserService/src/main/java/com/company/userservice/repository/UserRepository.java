package com.company.userservice.repository;

import com.company.userservice.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByAccount_Username(String username);

    @Query("SELECT u FROM User u LEFT JOIN FETCH Account a WHERE u.id=:userId")
    User getUserById(Long userId);
}
