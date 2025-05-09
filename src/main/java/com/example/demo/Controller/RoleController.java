package com.example.demo.Controller;

import com.example.demo.DTO.Request.RoleCreationRequest;
import com.example.demo.DTO.Response.ResponseNorm;
import com.example.demo.DTO.Response.RoleResponse;
import com.example.demo.Service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping(path = "/role")
@RequiredArgsConstructor
public class RoleController {
    final RoleService roleService;

    @PostMapping
    public ResponseNorm<RoleResponse> AddRole(@RequestBody RoleCreationRequest request) {
        return ResponseNorm
                .<RoleResponse>builder()
                .result(roleService.AddRole(request))
                .build();
    }

    @GetMapping
    public ResponseNorm<List<RoleResponse>> GetAllRoles() {
        return ResponseNorm
                .<List<RoleResponse>>builder()
                .result(roleService.GetAllRoles())
                .build();
    }

    @DeleteMapping(path = "/{roleName}")
    public ResponseNorm<Void> DeleteRole(@PathVariable String roleName) {
        roleService.DeleteRole(roleName);
        return ResponseNorm
                .<Void>builder()
                .build();
    }
}
