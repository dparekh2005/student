package com.interview.students.repo;

import com.interview.students.domain.JwtBlacklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JwtBlacklistRepository extends JpaRepository<JwtBlacklist, Integer> {
     Optional<JwtBlacklist> findJwtBlacklistByToken(String token);
}
