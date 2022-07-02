package com.ironhack.projectoRocioArellano.repository;

import com.ironhack.projectoRocioArellano.model.Transferencia;
import com.ironhack.projectoRocioArellano.model.accounts.Account;
import com.ironhack.projectoRocioArellano.model.users.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransferenciaRepository extends JpaRepository<Transferencia,Integer> {


}
