package com.example.banquesecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import static org.springframework.http.HttpMethod.GET;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
                .authorizeRequests().antMatchers(GET, "/compte").hasRole(AppUserRole.USER.name())
                .and()
                .authorizeRequests().antMatchers("/api/v1/UserManagement").hasRole(AppUserRole.ADMIN.name())
                .and()
                .authorizeRequests().antMatchers("/login/**").permitAll()
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .exceptionHandling().accessDeniedPage("/erreur")
                .and()
                .httpBasic();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*@Override
    @Bean
    protected UserDetailsService userDetailsService() {

        UserDetails user = User.builder()
                .username("user1")
                .password("{noop}password")
                .roles(AppUserRole.USER.name())
                .build();
        UserDetails admin = User.builder().username("admin")
                .password("{noop}password")
                .roles(AppUserRole.ADMIN.name())
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }*/


}
