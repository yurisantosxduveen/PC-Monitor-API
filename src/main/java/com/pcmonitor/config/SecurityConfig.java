package com.pcmonitor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.UserDetails;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();                  // -> O BCrypt serve para proteger as senhas.
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {

        UserDetails admin = User.builder()
                .username("Duveen")
                .password(passwordEncoder.encode("YuriDev"))
                .roles("ADMIN")
                .build();

        UserDetails user = User.builder()
                .username("visitante")
                .password(passwordEncoder.encode("visitante123"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);

    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity HTTP) throws Exception {

    HTTP

        .csrf(csrf -> csrf.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authorizeHttpRequests(auth -> auth
                                .requestMatchers("/pc/desligar/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )

                .httpBasic(Customizer.withDefaults());

            return HTTP.build();

    }
}
