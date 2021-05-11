package com.reading.readingisgood.config.security;

import com.reading.readingisgood.config.Properties.Properties;
import io.jsonwebtoken.*;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class JwtTokenProvider {
    private static final Logger log = LoggerFactory.getLogger(JwtTokenProvider.class);
    private final Properties jwtProperties;

    public String generateToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal)authentication.getPrincipal();
        return getAccessToken(userPrincipal.getId(), populateClaims(userPrincipal));
    }

    public String getAccessToken(String id, Claims claims) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + this.jwtProperties.getJwtExpirationInMs().longValue());
        String token = Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(expiryDate).signWith(SignatureAlgorithm.HS512, this.jwtProperties.getJwtSecret()).compact();
        return token;
    }


    public Claims populateClaims(UserPrincipal userPrincipal) {
        Claims claims = Jwts.claims().setSubject((userPrincipal.getMobileNumber().toString()));
        claims.put("scopes", userPrincipal.getAuthorities().stream().map(Object::toString).collect(Collectors.toList()));
        return claims;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtProperties.getJwtSecret()).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            log.info("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.info("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.info("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.info("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.info("JWT claims string is empty.");
        }
        return false;
    }

    public Long extractMobileNoFromToken(String token) {
        return Long.valueOf(Long.parseLong(getClaimsByToken(token).getSubject()));
    }

    String extractTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        return extractTokenFromAuthorizationHeader(bearerToken);
    }

    public String extractTokenFromAuthorizationHeader(String authorizationHeaderValue) {
        if (StringUtils.isEmpty(authorizationHeaderValue))
            return "";
        if (!authorizationHeaderValue.startsWith("Bearer "))
            return "";
        return authorizationHeaderValue.split(" ")[1];
    }

    private Claims getClaimsByToken(String token) {
        return (Claims)Jwts.parser()
                .setSigningKey(this.jwtProperties.getJwtSecret())
                .parseClaimsJws(token)
                .getBody();
    }

}