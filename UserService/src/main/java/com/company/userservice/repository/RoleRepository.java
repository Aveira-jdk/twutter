package com.company.userservice.repository;

import com.company.userservice.model.entity.Role;
import com.company.userservice.model.enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value = "SELECT r FROM Role r WHERE r.roleName =:roleName")
    Role findByRoleName(@Param(value = "roleName") Roles eRole);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM accounts_roles WHERE account_id =:accountId", nativeQuery = true)
    void deleteAccount(Long accountId);
}