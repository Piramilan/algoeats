package com.se.milan.repository;

import com.se.milan.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<MenuItem, Integer> {
    List<MenuItem> findByCategory(String category);
}
