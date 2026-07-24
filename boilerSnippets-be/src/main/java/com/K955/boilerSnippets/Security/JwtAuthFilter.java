package com.K955.boilerSnippets.Security;

import com.K955.boilerSnippets.Entity.User;
import com.K955.boilerSnippets.ExceptionHandling.Exceptions.UserNotFoundException;
import com.K955.boilerSnippets.Repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("Incoming Request: {}", request.getRequestURI());

        final String header = request.getHeader("Authorization");

        if(header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);

        if(!jwtUtil.isTokenValid(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        Long githubId = jwtUtil.extractGithubId(token);

        User user = userRepository.findByGithubId(githubId)
                .orElseThrow(() -> new UserNotFoundException(githubId.toString()));

        if(user != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    user, null, null
            );

            authenticationToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );
        }

        filterChain.doFilter(request, response);
    }

}
