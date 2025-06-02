package com.jorge.cds.intermedio.grupo4.web_app.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jorge.cds.intermedio.grupo4.web_app.services.AuthService;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

/*
 * Esta clase es un filtro de autenticación JWT que se ejecuta una vez por solicitud de cualquier tipo siempre haciendo la autenticación
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    private final AuthService authService;
    private final UserDetailsService userDetailsService;

    // Realiza las inyecciones de dependencias necesarias
    public JwtAuthenticationFilter(AuthService authService, UserDetailsService userDetailsService) {
        this.authService = authService;
        this.userDetailsService = userDetailsService;
    }

    /*
     * El filtro no se ejecuta si la ruta es de login 
     * Durante el login no hay tokem, así que no debe saltar
     * Se ejecuta una vez por solicitud, por lo que no es necesario comprobar el método HTTP
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        final String requestURI = request.getRequestURI();
        return requestURI.equals(WebSecurityConfig.LOGIN_URL_MATCHER);
    }

    /*
     * Se valida el token y se pasa al contexto de seguridad de Spring Security los valores extraidos del token
     */
    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain) throws ServletException, IOException {

        final Optional<String> token = getJwtFromCookie(request);

        // Si NO hay token, sigue la cadena (NO autentica, pero tampoco lanza error)
        if (token.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }

        // Si hay token, validar
        if (!authService.validateToken(token.get())) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            throw new BadCredentialsException("Invalid token");
        }

        String userName = authService.getUserFromToken(token.get());
        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
                null, userDetails.getAuthorities());

        authenticationToken.setDetails(userDetails);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);
    }

    /*
     * Recupera el token del header Set-Cookie. Si no hay token, se devuelve un Optional vacío
     */
    private Optional<String> getJwtFromCookie(HttpServletRequest request) {
        final Cookie[] cookies = request.getCookies();
        if (cookies == null || ArrayUtils.isEmpty(cookies)) {
            return Optional.empty();
        }
        return (Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(AuthConstants.TOKEN_COOKIE_NAME))
                .map(Cookie::getValue)
                .findFirst());
    }

}