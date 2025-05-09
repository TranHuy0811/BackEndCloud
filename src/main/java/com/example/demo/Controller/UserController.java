package com.example.demo.Controller;

import com.example.demo.Service.UserService;
import com.example.demo.DTO.Request.UserCreationRequest;
import com.example.demo.DTO.Request.UserUpdateRequest;
import com.example.demo.DTO.Response.ResponseNorm;
import com.example.demo.DTO.Response.UserResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public ResponseNorm<List<UserResponse>> GetAllUsers() {
        return ResponseNorm
                .<List<UserResponse>>builder()
                .result(userService.GetAllUsers())
                .build();
    }

    @GetMapping(path = "/{userId}")
    public ResponseNorm<UserResponse> GetSpecificUser(@PathVariable("userId") String userId) {
        return ResponseNorm
                .<UserResponse>builder()
                .result(userService.GetSpecificUsers(userId))
                .build();
    }

    @PostMapping
    public ResponseNorm<UserResponse> AddUser(@RequestBody @Valid UserCreationRequest request) {
        return ResponseNorm
                .<UserResponse>builder()
                .result(userService.AddUser(request))
                .build();
    }

    @PutMapping(path = "/{userId}")
    public ResponseNorm<UserResponse> UpdateUser(@RequestBody @Valid UserUpdateRequest request, @PathVariable("userId") String userId) {
        return ResponseNorm
                .<UserResponse>builder()
                .result(userService.UpdateUser(request, userId))
                .build();
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseNorm<Void> DeleteUser(@PathVariable("userId") String userId) {
        userService.DeleteUser(userId);
        return ResponseNorm
                .<Void>builder()
                .build();
    }

}
