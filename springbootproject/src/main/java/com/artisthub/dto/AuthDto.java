package com.artisthub.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;

public class AuthDto {

    @Data
    public static class LoginRequest {
        @NotBlank
        private String email;

        @NotBlank
        private String password;
    }

    @Data
    public static class RegisterRequest {
        @NotBlank
        private String name;

        @NotBlank
        @Email
        private String email;

        @NotBlank
        private String password;
        
        private String phone;
        
        private String role; // admin, artist, customer
        
        // Artist specific
        private String category;
        private Integer experienceYears;
        private Double pricePerEvent;
        private String description;
        private String profilePicUrl;
        
        // Customer specific
        private String address;
    }

    @Data
    public static class JwtResponse {
        private String token;
        private String type = "Bearer";
        private Long id;
        private String email;
        private String role;

        public JwtResponse(String accessToken, Long id, String email, String role) {
            this.token = accessToken;
            this.id = id;
            this.email = email;
            this.role = role;
        }
    }
}
