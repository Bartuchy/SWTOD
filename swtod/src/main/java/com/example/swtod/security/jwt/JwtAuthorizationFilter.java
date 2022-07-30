package com.example.swtod.security.jwt;

import com.example.swtod.entity.User;
import com.example.swtod.security.UserDetailsServiceImpl;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    public static final String TOKEN_PREFIX = "Bearer ";

    private final UserDetailsServiceImpl userDetailsService;
    private final JwtToken jwtToken;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String requestTokenHeader = request.getHeader(AUTHORIZATION);
        String email = null;
        String token = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith(TOKEN_PREFIX)) {
            token = requestTokenHeader.substring(7);
            email = jwtToken.extractUsername(token);

        }

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            User user = (User) this.userDetailsService.loadUserByUsername(email);

            if (jwtToken.validateToken(token, user)) {
                menageAuthentication(user, request);
            }
        }
        filterChain.doFilter(request, response);
    }

    private void menageAuthentication(User user, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(user, null, null);
        usernamePasswordAuthenticationToken
                .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }
}
