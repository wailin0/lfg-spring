package com.lfg.spring.repository;

import com.lfg.spring.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {


}
