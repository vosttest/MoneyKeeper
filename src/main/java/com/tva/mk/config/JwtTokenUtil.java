package com.tva.mk.config;

import static com.tva.mk.common.Constants.SIGNING_KEY;
import static com.tva.mk.common.Constants.TOKEN_TIME;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.tva.mk.dto.PayloadDto;
import com.tva.mk.model.Users;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {
	// region -- Fields --

	private static final long serialVersionUID = -4160292099516778353L;

	// end

	// region -- Methods --

	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	public String doGenerateToken(Users user, List<SimpleGrantedAuthority> authorities) {
		Claims claims = Jwts.claims().setSubject(user.getUserName());
		claims.put("scopes", authorities);
		claims.put("user", getPayload(user));

		return Jwts.builder().setClaims(claims).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TOKEN_TIME * 1000))
				.signWith(SignatureAlgorithm.HS256, SIGNING_KEY).compact();
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(SIGNING_KEY).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	private PayloadDto getPayload(Users user) {
		PayloadDto res = new PayloadDto();

		res.setId(user.getId());
		res.setFirstName(user.getFirstName());
		res.setLastName(user.getLastName());
		res.setEmail(user.getEmail());
		res.setContactNo(user.getContactNo());
		res.setAccountNo(user.getAccountNo());
		res.setUserName(user.getUserName());
		res.setRemark(user.getRemarks());

		return res;
	}

	// end
}