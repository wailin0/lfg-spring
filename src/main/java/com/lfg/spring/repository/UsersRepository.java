package com.lfg.spring.repository;

import com.lfg.spring.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByUsername(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
