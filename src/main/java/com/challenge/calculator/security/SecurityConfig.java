package com.challenge.calculator.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(requests ->  {
                    requests.requestMatchers("/h2-console/**", "/api/v1/operations/**").permitAll()
                            .anyRequest().authenticated();
                })
                .csrf(csrf -> {
                    csrf.ignoringRequestMatchers("/api/v1/operations/**");
                    csrf.ignoringRequestMatchers("/h2-console/**");
                })
                .headers(headers -> {
                    headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable); // Desabilitar X-Frame-Options para permitir iframes
                })
//                .oauth2ResourceServer(oauth2Configurer -> oauth2Configurer.jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwt -> {
//                    Map<String, Collection<String>> realmAccess = jwt.getClaim("realm_access");
//                    Collection<String> roles = realmAccess.get("roles");
//                    var grantedAuthorities = roles.stream()
//                            .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
//                            .collect(Collectors.toList());
//                    return new JwtAuthenticationToken(jwt, grantedAuthorities);
//                })))
                .build();
    }
}
