package com.example.pcserviceapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder(8);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(getPasswordEncoder())
                .usersByUsernameQuery("select username, password, active from user where username =?")
                .authoritiesByUsernameQuery("select u.username, u.role from user u where u.username=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Отключение CSRF защиты и настройка политики CORS, если это необходимо
        http.csrf().disable().cors().disable();

        // Настройка правил доступа
        http.authorizeRequests()
                .antMatchers("/api/public/**").permitAll() // Разрешить доступ к открытым API
                .antMatchers("/api/private/**").authenticated() // Требовать аутентификацию для защищенных API
                .anyRequest().authenticated(); // Остальные запросы также требуют аутентификации

        // Настройка фильтров для аутентификации и авторизации

        // Отключение страницы входа и выхода
        http.formLogin().disable();
        http.logout().disable();
    }
}