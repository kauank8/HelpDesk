package farias.paulino.kauan.HelpDesk.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
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
		//return Keys.secretKeyFor(SignatureAlgorithm.HS512);
		return Keys.hmacShaKeyFor(secret.getBytes());
	}

	public String generateToken(String email) {
		SecretKey key = getSigningKey();

		return Jwts.builder().setSubject(email).setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(key).compact();
	}

	public boolean tokenValido(String token) {
		Claims claims = getClaims(token);
		if (claims != null) {
			String username = claims.getSubject();
			Date expirationDate = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());
			if (username != null && expirationDate != null && now.before(expirationDate)) {
				return true;
			}
		}
		return false;
	}

	private Claims getClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
		} catch (Exception e) {
			return null;
		}
	}

	public String getUsername(String token) {
		Claims claims = getClaims(token);
		if(claims != null) {
			return claims.getSubject();
		}
		return null;
	}
}
