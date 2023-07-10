package com.mecavia.site.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.mecavia.site.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
	
	private static final String SECRET_KEY = "2F413F442A472D4B6150645367566B59703373367639792442264529482B4D62";
	
	public String exactUsername(String jwt) throws Exception {
		return extractClaim(jwt, Claims::getSubject);
	}
	public <T> T extractClaim(String jwt,Function<Claims, T> claimsResolver) throws Exception {
		final Claims claims = extracatAllClaims(jwt);
		return claimsResolver.apply(claims);
	}
	
	public String genarateToken(UserDetails userDetails) {
		 try {
			 User user = (User) userDetails; 
			 Map<String, Object> claims = new HashMap<>();
			 claims.put("id", user.getId()); 
			 claims.put("roleid", user.getRoleid()); 
			 claims.put("lastname", user.getLastname()); 
			 claims.put("firstname", user.getFirstname()); 
			 claims.put("businessRole", user.getBusinessRole()); 
			 return genarateToken(claims,userDetails);
		} catch (Exception e) {
			return e.getLocalizedMessage();
		} 
		
	} 
	
	public String genarateToken(
			Map<String,Object> extractClaims,
			UserDetails userDetails
			) {
		return Jwts
				.builder()
				.setClaims(extractClaims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
				.signWith(getSignInKey(),SignatureAlgorithm.HS256)
				.compact();
	}
	public boolean isTokenValid(String jwt,UserDetails userDetails) throws Exception {
		final String username = exactUsername(jwt);
		return (username.equals(userDetails.getUsername())) && !isTokenExpird(jwt);
	}
	
	private boolean isTokenExpird(String jwt) throws Exception {
		return exactExpiration(jwt).before(new Date());
	}
	private Date exactExpiration(String jwt) throws Exception {
		return extractClaim(jwt, Claims::getExpiration);
	}
	private Claims extracatAllClaims(String jwt) throws Exception {
		return Jwts
				.parserBuilder()
				.setSigningKey(getSignInKey())
				.build()
				.parseClaimsJws(jwt)
				.getBody();
		
		
		
	}
	private Key getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY); 
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
