package com.example.demo.Component;

import com.example.demo.DB.User;
import com.example.demo.Repo.UserRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component("preAuthorizeDeleteUser")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PreAuthorizeDeleteUser {
    final UserRepo userRepo;

    public boolean isOwner(String id, String currentUsername) {
        User user1 = userRepo.findById(id).orElseThrow(() -> new RuntimeException("error"));
        return userRepo.findById(id)
                .map(user -> Objects.equals(user.getUsername(), currentUsername))
                .orElse(false);
    }

}
