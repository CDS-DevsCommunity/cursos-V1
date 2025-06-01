package com.jorge.cds.intermedio.grupo4.web_app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.jorge.cds.intermedio.grupo4.web_app.services.AuthService;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.config.Customizer;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpMethod;

import jakarta.servlet.Filter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity(debug = true)
@EnableMethodSecurity
public class WebSecurityConfig {
    
    private final AuthService authService;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public WebSecurityConfig(AuthService authService, UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.authService = authService;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    public final static String LOGIN_URL_MATCHER = ApiConfig.API_BASE_PATH + "/auth/login";
    public final static String LOG_OUT_URL_MATCHER = ApiConfig.API_BASE_PATH + "/auth/logout";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        final Filter jwtFilter = jwtAuthenticationFilter();
        http
            .formLogin(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers(HttpMethod.POST, LOGIN_URL_MATCHER).permitAll()
                .requestMatchers("/api/users/**").permitAll()   // libre acceso a los usuarios
                .requestMatchers(ApiConfig.API_BASE_PATH + "/auth/create-user").permitAll()
                //.requestMatchers("/api/users/**").authenticated()         // acceso autenticado a los usuarios
                .anyRequest().denyAll()
            )
            .logout(logout -> {
                logout
                    .logoutRequestMatcher(new AntPathRequestMatcher(LOG_OUT_URL_MATCHER, HttpMethod.POST.name()))
                    .logoutSuccessHandler((request, response, authentication) -> {
                        response.setStatus(HttpStatus.NO_CONTENT.value());
                        final Cookie cookie = new Cookie(AuthConstants.TOKEN_COOKIE_NAME, null);
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    })
                ;
            })
            .addFilterBefore(jwtFilter, LogoutFilter.class)
            .csrf((csrf) -> {
                    try {
                        csrf.disable()
                            .sessionManagement((sessionManagement) -> sessionManagement
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                            ).oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()));
                    } catch (Exception e) {
                        throw new AuthenticationException("Spring Security Config Issue",e) {
                        };
                    }
                }
            )
            .authenticationManager(authenticationManager())
            .exceptionHandling(handler -> handler
                .authenticationEntryPoint((request, response, authException) -> {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                })
            )
        ;

        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        ProviderManager providerManager = new ProviderManager(authenticationProvider);
        providerManager.setEraseCredentialsAfterAuthentication(true);

        return providerManager;
    }

    @Bean
    public MethodSecurityExpressionHandler methodSecurityExpressionHandler() {
        /*DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setRoleHierarchy(roleHierarchy());
        return expressionHandler;*/
        return new DefaultMethodSecurityExpressionHandler();
    }

    /*
     * lo comentado sire para definir jerarquías de roles, osea, que un rol
     * puede heredar permisos de otro rol, por ejemplo, el rol BACK_OFFICE_ADMIN
     */

    /*@Bean
    public RoleHierarchy roleHierarchy() {
        return RoleHierarchyImpl.withDefaultRolePrefix()
            .role(Role.BACK_OFFICE_ADMIN.name())
            .implies(Role.SALES_MANAGER.name())
            .implies(Role.END_USER.name())
            .build();
    }*/


    private JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(authService, userDetailsService);
    }

}
