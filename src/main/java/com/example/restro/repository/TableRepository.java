package com.example.restro.repository;

import com.example.restro.entity.TableDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends JpaRepository<TableDetails, Integer> {
}
