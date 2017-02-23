package com.kamatama41.sfcbot

import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

/**
 * Needs to the following properties
 * - security.user.name
 * - security.user.password
 */
@Configuration
@EnableWebSecurity
class SecurityConfig(val properties: SecurityProperties) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
                .antMatchers(
                        "/",
                        "/favicon.ico",
                        "/main.css",
                        "/built/bundle.js",
                        "/api/**"
                ).permitAll()
                .anyRequest().authenticated()
                .and().httpBasic()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.inMemoryAuthentication()
                .withUser(properties.user.name).password(properties.user.password).roles("USER")
    }
}
