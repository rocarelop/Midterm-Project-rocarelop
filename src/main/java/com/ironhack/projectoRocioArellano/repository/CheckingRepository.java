package com.ironhack.projectoRocioArellano.repository;

import com.ironhack.projectoRocioArellano.model.accounts.Checking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckingRepository extends JpaRepository<Checking, Integer> {
}
