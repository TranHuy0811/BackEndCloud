package com.example.demo.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class RoleCreationRequest {
    @NotBlank(message = "BLANK_VALUE")
    String name;
    @NotBlank(message = "BLANK_VALUE")
    String description;
}
