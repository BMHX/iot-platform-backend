package top.xym.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import top.xym.framework.security.user.UserDetail;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TokenUtils {
    private static final long EXPIRATION = 86400L; // Token expiration time in seconds
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor("your-secret-key-which-should-be-at-least-256-bits-long".getBytes());

    public static String generateToken(UserDetail userDetail) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", userDetail.getUsername());
        claims.put("created", new Date());
        claims.put("userId", userDetail.getId());
        claims.put("authorities", userDetail.getAuthorities());

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION * 1000))
                .signWith(SECRET_KEY)
                .compact();
    }

    public static String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", username);
        claims.put("created", new Date());

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION * 1000))
                .signWith(SECRET_KEY)
                .compact();
    }

    public static String getUsernameFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getSubject();
    }

    public static Long getUserIdFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        Long userId = claims.get("userId", Long.class);
        log.info("Extracted userId from token: {}", userId);
        return userId;
    }

    public static boolean validateToken(String token, UserDetails userDetails) {
        try {
            String username = getUsernameFromToken(token);
            boolean usernameMatch = username.equals(userDetails.getUsername());
            boolean notExpired = !isTokenExpired(token);

            if (!usernameMatch) {
                System.out.println("Token validation failed: username does not match");
            }
            if (!notExpired) {
                System.out.println("Token validation failed: token expired");
            }

            return usernameMatch && notExpired;
        } catch (Exception e) {
            System.out.println("Token validation error: " + e.getMessage());
            return false;
        }
    }

    private static boolean isTokenExpired(String token) {
        Date expiration = getClaimsFromToken(token).getExpiration();
        return expiration.before(new Date());
    }

    private static Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public static String getAccessToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }

    public static boolean validateToken(String token) {
        try {
            return !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    public static Collection<? extends GrantedAuthority> getAuthoritiesFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        List<String> authorities = claims.get("authorities", List.class);
        if (authorities == null) {
            return Collections.emptyList();
        }
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
