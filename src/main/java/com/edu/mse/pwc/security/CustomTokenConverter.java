package com.edu.mse.pwc.security;

import com.edu.mse.pwc.persistence.entities.UserEntity;
import com.edu.mse.pwc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CustomTokenConverter extends JwtAccessTokenConverter {
    @Autowired
    private UserService userService;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        final Map<String, Object> additionalInfo = new HashMap<>();
        UserEntity user = userService.getUserByUsername(authentication.getName());
        additionalInfo.put("id", user.getId());
//        additionalInfo.put("role", user.getRole());
//        additionalInfo.put("firstName", user.getFirstName());
//        additionalInfo.put("lastName", user.getLastName());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return super.enhance(accessToken, authentication);
    }

    public Map<String, Object> decodeToken(String token) {
        Map<String, Object> map = super.decode(token);
        return map;
    }
}
