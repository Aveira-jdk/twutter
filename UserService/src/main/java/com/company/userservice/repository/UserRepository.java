package com.company.userservice.repository;

import com.company.userservice.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByAccount_Username(String username);

    User getUserById(Long userId);
}
