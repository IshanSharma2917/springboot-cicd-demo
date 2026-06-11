package com.task.TaskScheduler.Security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.task.TaskScheduler.Repositiory.UserRepo;
import com.task.TaskScheduler.entity.User;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepo userRepo;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if(authHeader != null && authHeader.startsWith("Bearer "))
        {
            String token = authHeader.substring(7);

            String username = jwtUtil.extractUsername(token);

            if(username != null &&
               SecurityContextHolder.getContext().getAuthentication() == null)
            {
                User user = userRepo.findByName(username).orElse(null);

                if(user != null &&
                   jwtUtil.validateToken(token, user.getName()))
                {
                	UsernamePasswordAuthenticationToken authentication =
                			new UsernamePasswordAuthenticationToken(
                			        username,
                			        null,
                			        null
                			);

                    authentication.setDetails(
                            new WebAuthenticationDetailsSource()
                                    .buildDetails(request)
                    );

                    SecurityContextHolder.getContext()
                            .setAuthentication(authentication);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
