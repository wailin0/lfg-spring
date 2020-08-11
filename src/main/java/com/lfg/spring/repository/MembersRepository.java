package com.lfg.spring.repository;

import com.lfg.spring.model.Members;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface MembersRepository extends JpaRepository<Members, Long> {
    String findByUserId(Long userId);

    List<Members> findByGroupId(Long groupId);

    void deleteByGroupIdAndUserId(Long groupId, Long userId);
}
