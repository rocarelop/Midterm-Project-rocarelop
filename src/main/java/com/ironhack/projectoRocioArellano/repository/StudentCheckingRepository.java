package com.ironhack.projectoRocioArellano.repository;

import com.ironhack.projectoRocioArellano.model.accounts.StudentChecking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCheckingRepository extends JpaRepository<StudentChecking, Integer> {
}
