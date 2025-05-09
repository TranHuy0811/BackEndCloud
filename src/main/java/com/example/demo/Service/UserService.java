package com.example.demo.Service;

import com.example.demo.DB.Role;
import com.example.demo.DB.User;
import com.example.demo.Exception.AppException;
import com.example.demo.Exception.ErrorCode;
import com.example.demo.Mapper.UserMapper;
import com.example.demo.Repo.RoleRepo;
import com.example.demo.Repo.UserRepo;
import com.example.demo.DTO.Request.UserCreationRequest;
import com.example.demo.DTO.Request.UserUpdateRequest;
import com.example.demo.DTO.Response.UserResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserService {
    final UserRepo userRepo;
    final UserMapper userMapper;
    final RoleRepo roleRepo;
    final PasswordEncoder passwordEncoder;

    public UserResponse AddUser(UserCreationRequest request) {
        if(userRepo.existsByEmail(request.getEmail())) throw new AppException(ErrorCode.USER_EXISTED);
        User user = userMapper.toUser(request);

        user.setPassword(passwordEncoder.encode(request.getPassword()));
        List<Role> roles = roleRepo.findAllById(List.of("USER"));
        user.setRoles(new HashSet<>(roles));

        return userMapper.toUserResponse(userRepo.save(user));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> GetAllUsers() {
        return userMapper.toUserResponseList(userRepo.findAll());
    }

    @PostAuthorize("returnObject.username == authentication.name")
    public UserResponse GetSpecificUsers(String id) {
        return userMapper.toUserResponse(userRepo.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED))
        );
    }

    @PreAuthorize("@preAuthorizeDeleteUser.isOwner(#id, authentication.name) or hasRole('ADMIN')")
    public UserResponse UpdateUser(UserUpdateRequest request, String id) {
        User user = userRepo.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        userMapper.updateUser(request, user);
        return userMapper.toUserResponse(userRepo.save(user));
    }

    @PreAuthorize("@preAuthorizeDeleteUser.isOwner(#id, authentication.name) or hasRole('ADMIN')")
    public void DeleteUser(String id) {
        userRepo.deleteById(id);
    }
}
