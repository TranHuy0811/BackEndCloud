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
public class UserCreationRequest {
    @NotBlank(message = "BLANK_VALUE")
    @Size(min = 8, message = "INVALID_USERNAME")
    String username;

    @NotBlank(message = "BLANK_VALUE")
    @Size(min = 8, message = "INVALID_PASSWORD")
    String password;

    @NotBlank(message = "BLANK_VALUE")
    @Email(message = "INVALID_EMAIL")
    String email;

    @NotBlank(message = "BLANK_VALUE")
    String fullName;

    @NotBlank(message = "BLANK_VALUE")
    @Pattern(regexp = "^(\\+84|0)[1-9][0-9]{8}$", message = "INVALID_PHONE_NUMBER")
    String phoneNumber;
}
