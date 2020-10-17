package com.lfg.spring.repository;

import com.lfg.spring.model.Members;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface MembersRepository extends JpaRepository<Members, Long> {
    List<Members> findByGroups_Id(Long groupId);

    void deleteByGroups_IdAndUsers_Id(Long groupId, Long userId);

    Members findByUsersUsername(String username);
}
