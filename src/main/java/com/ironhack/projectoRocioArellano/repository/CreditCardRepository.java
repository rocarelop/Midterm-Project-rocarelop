package com.ironhack.projectoRocioArellano.repository;

import com.ironhack.projectoRocioArellano.model.accounts.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Integer> {
}
