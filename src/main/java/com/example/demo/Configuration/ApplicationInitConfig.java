package com.example.demo.Configuration;


import com.example.demo.DB.Role;
import com.example.demo.DB.User;
import com.example.demo.Repo.RoleRepo;
import com.example.demo.Repo.UserRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;

@Configuration
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApplicationInitConfig {
    final PasswordEncoder passwordEncoder;
    final UserRepo userRepo;
    final RoleRepo roleRepo;

    @Bean
    ApplicationRunner applicationRunner(UserRepo userRepo) {
        return args -> {

            // Create init role
            List<String> initRoles = List.of("USER", "ADMIN");
            initRoles.forEach(roleName -> {
                if(!roleRepo.existsById(roleName)) {
                    Role role = Role
                            .builder()
                            .name(roleName)
                            .description(roleName + " role")
                            .build();
                    roleRepo.save(role);
                }
            });

            // Create admin user
            if(!userRepo.existsByEmail("admin")) {
                List<Role> roles = roleRepo.findAllById(List.of("ADMIN"));
                User user = User
                        .builder()
                        .email("admin")
                        .password(passwordEncoder.encode("admin"))
                        .roles(new HashSet<>(roles))
                        .build();

                userRepo.save(user);
            }
        };
    }
}
