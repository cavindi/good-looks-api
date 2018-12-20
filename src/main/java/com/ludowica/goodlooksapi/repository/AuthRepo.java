package com.ludowica.goodlooksapi.repository;

import com.ludowica.goodlooksapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepo extends JpaRepository<User, Integer> {
    User findByUsernameAndPassword(String username, String password);
}
