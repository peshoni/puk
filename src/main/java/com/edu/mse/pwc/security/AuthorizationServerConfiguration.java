package com.edu.mse.pwc.security;

import com.edu.mse.pwc.utils.P;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    private final static String[] GRANT_TYPES = {"password", "get_token", "refresh_token"};
    static final int ACCESS_TOKEN_VALIDITY_SECONDS = 4 * 60 * 60;
    // static final int REFRESH_TOKEN_VALIDITY_SECONDS = 60 * 60 + 20;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        P.syso("Authorize");
        clients.inMemory()
                .withClient("admin")
                .secret(encoder().encode("admin"))
                .autoApprove(true)
                .scopes("read", "write").accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
                //  .refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_SECONDS)
                .authorizedGrantTypes(GRANT_TYPES);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)//.tokenEnhancer(customTokenEnhancer())
                .tokenStore(tokenStore).userDetailsService(userDetailsService);

    }

    @Bean
    public TokenStore tokenStore() {
        TokenStore store = new InMemoryTokenStore();
        return store;
    }

    private PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomTokenConverter customTokenEnhancer() {
        return new CustomTokenConverter();
    }
}
