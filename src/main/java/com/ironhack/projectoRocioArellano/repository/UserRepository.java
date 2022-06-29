package com.ironhack.projectoRocioArellano.repository;

import com.ironhack.projectoRocioArellano.model.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
