package com.ll.exam.sbbfinal.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SiteUser,Long> {
    boolean existsByUsername(String username);
}
