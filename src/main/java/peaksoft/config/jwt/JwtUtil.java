package peaksoft.config.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import peaksoft.model.Auth;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtil {
    private final JwtConfig jwtConfig;

    public String generateToken(Authentication authentication) {
        Auth auth = (Auth) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(auth.getEmail())
                .signWith(SignatureAlgorithm.HS256, jwtConfig.getSecretKey())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtConfig.getExpiration() * 8600000))
                .compact();

    }

    public String getEmailFromToken(String token) {

        return Jwts.parser()
                .setSigningKey(jwtConfig.getSecretKey())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean verifierToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtConfig.getSecretKey())
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
