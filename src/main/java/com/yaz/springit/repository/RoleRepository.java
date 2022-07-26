package com.yaz.springit.repository;

import com.yaz.springit.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role,Long> {
}
