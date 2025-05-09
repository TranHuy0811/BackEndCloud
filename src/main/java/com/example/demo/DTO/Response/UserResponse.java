package com.example.demo.DTO.Response;

import com.example.demo.DB.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class UserResponse {
    String id;
    String username;
    String email;
    List<RoleResponse> roles;
}
