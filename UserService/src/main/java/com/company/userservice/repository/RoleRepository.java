package com.company.userservice.repository;

import com.company.userservice.model.entity.Role;
import com.company.userservice.model.enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value = "SELECT r FROM Role r WHERE r.roleName =:roleName")
    Role findByRoleName(@Param(value = "roleName") Roles eRole);

}