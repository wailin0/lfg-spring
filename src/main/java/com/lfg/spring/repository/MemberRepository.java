package com.lfg.spring.repository;

import com.lfg.spring.model.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT m FROM Member m WHERE m.group.id = :groupId")
    public List<Member> findAllByGroupId(Long groupId);

    @Query("SELECT m FROM Member m WHERE m.user.id = :groupId")
    public List<Member> findAllByUserId(Long userId);

    @Query("SELECT count(m)>0 FROM Member m WHERE m.user.id = :userId AND m.id = :groupId")
    public Boolean isUserInGroup(Long userId, Long groupId);

    @Modifying
    @Query("DELETE FROM Member m WHERE m.user.id = :userId AND m.id = :groupId")
    public void deleteByGroupAndUserId(Long userId, Long groupId);

}
