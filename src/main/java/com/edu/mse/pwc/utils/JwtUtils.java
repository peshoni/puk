package com.edu.mse.pwc.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;
import java.util.List;

public class JwtUtils {

    private static JsonMapper jsonMapper = JsonMapper.builder()
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .addModule(new JavaTimeModule())
            .build();

    private JwtUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static JwtClaims getClaims(String accessToken) {

        if (accessToken == null) {
            throw new RuntimeException("Null access token");
            //throw JwtException.invalidAccessToken("Null access token");
        }

        String[] tokens = accessToken.split("\\.");

        if (tokens.length != 3) {
            throw new RuntimeException("JWT must have 3 tokens");
            // throw JwtException.invalidAccessToken("JWT must have 3 tokens");
        }

        try {
            byte[] claimsBytesDecoded = Base64.getDecoder().decode(tokens[1].getBytes(StandardCharsets.UTF_8));
            return jsonMapper.readValue(claimsBytesDecoded, JwtClaimsImpl.class);
        } catch (IllegalArgumentException | IOException e) {
            throw new RuntimeException("Failed to parse access_token claims", e);
            // throw JwtException.invalidClaims("Failed to parse access_token claims", e);
        }
    }

    public static interface JwtClaims {
        public String getIssuer();

        public Instant getExpirationTime();

        public Instant getIssuedAt();

        public String getSubject();

        public List<String> getAudience();

        public List<String> getAuthorities();

        public List<String> getScopes();

        public String getClientId();

        public String getGrantType();
        
        public String getFirstName();
    }

    /*
     * https://tools.ietf.org/html/rfc7519#section-4.1
     */
    @Getter
    public static class JwtClaimsImpl implements JwtClaims {

        @JsonProperty("iss")
        private String issuer;

        @JsonProperty("exp")
        private Instant expirationTime;

        @JsonProperty("iat")
        private Instant issuedAt;

        @JsonProperty("sub")
        private String subject;

        @JsonProperty("aud")
        private List<String> audience;

        @JsonProperty("authorities")
        private List<String> authorities;

        @JsonProperty("scope")
        private List<String> scopes;

        @JsonProperty("client_id")
        private String clientId;

        @JsonProperty("grant_type")
        private String grantType;

        @JsonProperty("firstName")
        private String firstName;
    }
}
