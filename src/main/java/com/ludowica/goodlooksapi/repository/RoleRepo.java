package com.ludowica.goodlooksapi.repository;

import com.ludowica.goodlooksapi.model.Role;
import com.ludowica.goodlooksapi.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(RoleName name);
}
