package com.PB1b.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PB1b.dto.RegisteredBiller;

public interface RegisteredBillersRepo extends JpaRepository<RegisteredBiller, Integer>{

}
