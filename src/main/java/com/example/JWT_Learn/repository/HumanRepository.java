package com.example.JWT_Learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JWT_Learn.model.Human;

@Repository
public interface HumanRepository extends JpaRepository<Human, Long> {
    Human findByUsername(String username);
}