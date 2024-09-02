package farias.paulino.kauan.HelpDesk.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@SuppressWarnings("deprecation")
@Component
public class JWTUtil {
	@Value("${jwt.expiration}")
	private Long expiration;
	@Value("${jwt.secret}")
	private String secret;

	private SecretKey getSigningKey() {
        // Use a secure method to generate a signing key
        return Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }
	public String generateToken(String email) {
        SecretKey key = getSigningKey();

        return Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key)
                .compact();
    }
}
