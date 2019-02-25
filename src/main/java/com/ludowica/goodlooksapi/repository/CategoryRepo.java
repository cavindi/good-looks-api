package com.ludowica.goodlooksapi.repository;

import com.ludowica.goodlooksapi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {
}
