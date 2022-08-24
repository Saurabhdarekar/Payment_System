package com.PB1b.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PB1b.dto.Bills;

public interface BillsRepo extends JpaRepository<Bills, Integer> {

}
