package org.learn.cms.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
class SecurityConfig {
    private final CustomAuthenticationSuccessHandler authenticationSuccessHandler;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain config(HttpSecurity http) throws Exception {

        String[] permitAll = {
                "/",
                "/login",
                "/register",
                "/contact_us",
                "/css/**",
                "/js/**",
                "/images/**"
        };
        return http.authorizeHttpRequests(auth -> auth.requestMatchers(permitAll).permitAll())
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .authenticationProvider(authenticationProvider)
                .formLogin(login -> login.loginPage("/login")
                        .permitAll()
                        .loginProcessingUrl("/authenticate")
                        .failureUrl("/login?error=true")
                        .defaultSuccessUrl("/dashboard", true)
                        .usernameParameter("email")
                        .passwordParameter("password"))
                .logout(logout -> logout.logoutUrl("/perform_logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .permitAll()
                )
                .oauth2Login(oauth -> oauth.loginPage("/authenticate")
                        .successHandler(authenticationSuccessHandler)
                )
                .build();
    }
}
