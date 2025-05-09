package com.example.demo.Mapper;

import com.example.demo.DB.Role;
import com.example.demo.DTO.Request.RoleCreationRequest;
import com.example.demo.DTO.Response.RoleResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role toRole(RoleCreationRequest request);
    RoleResponse toRoleResponse(Role role);
    List<RoleResponse> toRoleResponseList(List<Role> roles);
}
