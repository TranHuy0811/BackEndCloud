package com.example.demo.Service;

import com.example.demo.DB.Role;
import com.example.demo.DTO.Request.RoleCreationRequest;
import com.example.demo.DTO.Response.RoleResponse;
import com.example.demo.Mapper.RoleMapper;
import com.example.demo.Repo.RoleRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
public class RoleService {
    final RoleMapper roleMapper;
    final RoleRepo roleRepo;

    public RoleResponse AddRole(RoleCreationRequest request) {
        Role role = roleMapper.toRole(request);
        return roleMapper.toRoleResponse(roleRepo.save(role));
    }

    public List<RoleResponse> GetAllRoles() {
        return roleMapper.toRoleResponseList(roleRepo.findAll());
    }

    public void DeleteRole(String id) {
        roleRepo.deleteById(id);
    }
}
