package com.company.userservice.repository;

import com.company.userservice.model.entity.Account;
import com.company.userservice.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    boolean existsByUsername(String username);

    boolean existsByRoles(Role role);

    @Query(value = "SELECT a FROM Account a WHERE a.username =:username")
    Account findByUsername(@Param(value = "username") String username);

    List<Account> findAccountsByRoles(Role role);

}