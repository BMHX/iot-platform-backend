package top.xym.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import top.xym.framework.security.user.UserDetail;
import top.xym.util.TokenUtils;
import lombok.extern.slf4j.Slf4j; // 新增导入

import java.io.IOException;

@Slf4j // 新增注解
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String token = TokenUtils.getAccessToken(request);
        if (token != null && TokenUtils.validateToken(token)) {
            String username = TokenUtils.getUsernameFromToken(token);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // 从token中构建完整的认证信息
                Long userId = TokenUtils.getUserIdFromToken(token);
                // 新增日志，确认从Token中获取的userId
                log.info("JwtAuthenticationFilter - UserID from token: {}, Username from token: {}", userId, username);
                UserDetail userDetail = new UserDetail(
                    userId,
                    username,
                    null, // Password is not needed here as it's already authenticated
                    TokenUtils.getAuthoritiesFromToken(token)
                );
                // 新增日志，确认构建的UserDetail对象中的ID
                log.info("JwtAuthenticationFilter - Constructed UserDetail with ID: {}, Username: {}", userDetail.getId(), userDetail.getUsername());

                UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                        userDetail,
                        null,
                        userDetail.getAuthorities() // 使用userDetail中的authorities
                    );
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(request, response);
    }
}
