package com.example.userservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static com.example.userservice.security.Role.ADMIN;
import static com.example.userservice.security.Role.USER;

@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer ->
                        configurer
                .requestMatchers(HttpMethod.GET, "api/v1/users/**")
                                .hasAnyRole(USER.name(),ADMIN.name())
                .requestMatchers(HttpMethod.POST,"api/v1/users/**")
                                .hasRole(ADMIN.name())
                                .anyRequest().authenticated()

        ).httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();

    }

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

            jdbcUserDetailsManager.setUsersByUsernameQuery(
                    "select user_name, password, enabled from users where user_name=?"
            );

            jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                    "select user_name, role from authorities where user_name=?"
            );

        return jdbcUserDetailsManager;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }


}
