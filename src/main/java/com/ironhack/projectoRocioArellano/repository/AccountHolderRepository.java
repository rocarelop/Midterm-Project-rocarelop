package com.ironhack.projectoRocioArellano.repository;

import com.ironhack.projectoRocioArellano.model.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountHolderRepository extends JpaRepository<AccountHolder, Integer> {
}
