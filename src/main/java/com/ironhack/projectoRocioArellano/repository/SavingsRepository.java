package com.ironhack.projectoRocioArellano.repository;

import com.ironhack.projectoRocioArellano.model.accounts.Savings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingsRepository extends JpaRepository<Savings, Integer> {
}
