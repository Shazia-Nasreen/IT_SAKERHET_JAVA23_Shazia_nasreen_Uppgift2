package com.example.timecapsule.repository;

import com.example.timecapsule.entity.Capsule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CapsuleRepository extends JpaRepository<Capsule, Long> {
    List<Capsule> findByUserId(Long userId);
}
