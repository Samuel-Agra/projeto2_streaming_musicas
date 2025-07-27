package com.example.projeto2_streaming_musicas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login", "/singers").permitAll()
                        .requestMatchers("/singer/edit/**,", "/singers/add", "/singers/delete/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin( login -> login
                        .loginPage("/login")
                        //.failureUrl("/login")
                        .defaultSuccessUrl("/singers", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl( "/logout" )
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                );

        return http.build();
    }
}
