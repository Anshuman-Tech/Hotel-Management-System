package com.anshuman.apigateway.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {

    private String userId;
    private String accessToken;
    private String refreshToken;
    private long expireAt;
    private Collection<String> Authorities;
}
