package com.example.demo.DTO.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class UserUpdateRequest {
    @Size(min = 8, message = "INVALID_USERNAME")
    String username;

    @Size(min = 8, message = "INVALID_PASSWORD")
    String password;

    String fullName;

    @Pattern(regexp = "^(\\+84|0)[1-9][0-9]{8}$", message = "INVALID_PHONE_NUMBER")
    String phoneNumber;
}
