package vn.ntqsolution.smart_shop.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import vn.ntqsolution.smart_shop.dto.UserDto;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil {

  public static final long JWT_TOKEN_VALIDITY_TIME = 5 * 60 * 60;

  @Value("${jwt.secret}")
  private String secret;

  public String generateToken(UserDto userDto) {
    Map<String, Object> claims = new HashMap<>();
    return Jwts.builder()
      .setClaims(claims)
      .setSubject(userDto.getUsername())
      .setIssuedAt(new Date(System.currentTimeMillis()))
      .signWith(SignatureAlgorithm.HS512, secret)
      .compact();
  }

  public Boolean validateToken(String token, UserDetails userDetails) {
    String username = getUsernameFromToken(token);
    return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
  }

  public String getUsernameFromToken(String token) {
    return getClaimFromToken(token, Claims::getSubject);
  }

  private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = getAllClaimsFromToken(token);
    return claimsResolver.apply(claims);
  }

  private Claims getAllClaimsFromToken(String token) {
    return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
  }

  private boolean isTokenExpired(String token) {
    Date expiredDate = getExpirationDateFromToken(token);
    return expiredDate.before(new Date());
  }

  public Date getExpirationDateFromToken(String token) {
    return getClaimFromToken(token, Claims::getExpiration);
  }

}
