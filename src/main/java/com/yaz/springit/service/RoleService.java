package com.yaz.springit.service;

import com.yaz.springit.domain.Role;
import com.yaz.springit.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    private final RoleRepository roleRepository;

    public Role findByName(String role){
        return roleRepository.findByName(role);
    }
}
