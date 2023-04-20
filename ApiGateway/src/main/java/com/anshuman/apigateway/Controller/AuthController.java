package com.anshuman.apigateway.Controller;

import com.anshuman.apigateway.DTO.AuthResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private Logger logger = LoggerFactory.getLogger(AuthController.class);


    //Note - Try login in Incognito window, as in normal window a user is already logged in.
    @GetMapping("/login")
    public ResponseEntity<AuthResponse> login(@RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient client,
                                              @AuthenticationPrincipal OidcUser user,
                                              Model model){
        /*
        All the details for AuthResponse object is obtained from the Parameters passed to the functions.
         */

        logger.info("User email id: {}", user.getEmail());

        AuthResponse authResponse = AuthResponse.builder()
                .userId(user.getEmail())
                .accessToken(client.getAccessToken().getTokenValue())
                .refreshToken(client.getRefreshToken().getTokenValue())
                .expireAt(client.getAccessToken().getExpiresAt().getEpochSecond())
                .Authorities(user.getAuthorities().stream().map(grantedAuthority -> grantedAuthority.getAuthority()).collect(Collectors.toList())) //Convert Collection of GrantedAuthority to Collection of String
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(authResponse);
    }
}
