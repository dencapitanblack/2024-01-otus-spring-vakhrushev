package ru.cap.home.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(toH2Console())
                        .disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/**").hasRole("USER")
                        .requestMatchers(toH2Console()).permitAll()
                )
                .userDetailsService(userDetailsService)
                .headers(headers ->
                        headers.frameOptions(Customizer.withDefaults()).disable())
                .formLogin(form ->
                        form
                                .successForwardUrl("/"));


        return http.build();
    }

    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager detailsManager = new JdbcUserDetailsManager(dataSource);
        return detailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }


}