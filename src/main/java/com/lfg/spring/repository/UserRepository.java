package com.lfg.spring.repository;

import com.lfg.spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByUsername(String username);

    @Modifying
    @Query("UPDATE User u SET u.online = :isOnline WHERE u.userId = :userId")
    public void setOnline(Long userId, boolean isOnline);

}
