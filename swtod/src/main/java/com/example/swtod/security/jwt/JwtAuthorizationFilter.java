package com.example.swtod.security.jwt;

import com.example.swtod.domain.user.User;
import com.example.swtod.domain.user.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    public static final String TOKEN_PREFIX = "Bearer ";
    private final UserService userService;
    private final JwtToken jwtToken;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String requestTokenHeader = request.getHeader(AUTHORIZATION);
        String username = null;
        String token = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith(TOKEN_PREFIX)) {
            token = requestTokenHeader.substring(7);
            username = jwtToken.extractUsername(token);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            User user = userService.login(username);

            if (jwtToken.validateToken(token, user)) {
                menageAuthentication(user, request);
            }
        }
        filterChain.doFilter(request, response);
    }

    private void menageAuthentication(User user, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = setUPAToken(user);

        usernamePasswordAuthenticationToken
                .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }

    private UsernamePasswordAuthenticationToken setUPAToken(User user) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        List.of((GrantedAuthority) () -> "USER"));

        if (user.isAdmin()) {
            usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    user,
                    null,
                    List.of(
                            (GrantedAuthority) () -> "USER",
                            (GrantedAuthority) () -> "ADMIN"));
        }
        return usernamePasswordAuthenticationToken;
    }
}
