package com.example.demo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
@Component
public class JwtTokenUtil {

  private final SecretKey secretKey; // 替换为你的密钥
  public JwtTokenUtil() {
    // Generate a secure key for HS256
    this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
  }
  // 获取用户名从 token 中
  public String getUsernameFromToken(String token) {
    return getClaimFromToken(token, Claims::getSubject);
  }

  // 获取过期时间从 token 中
  private Date getExpirationDateFromToken(String token) {
    return getClaimFromToken(token, Claims::getExpiration);
  }

  // 从 token 中获取特定的声明
  public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = getAllClaimsFromToken(token);
    return claimsResolver.apply(claims);
  }

  // 获取所有声明
  private Claims getAllClaimsFromToken(String token) {
    return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
  }

  // 检查 token 是否过期
  private Boolean isTokenExpired(String token) {
    final Date expiration = getExpirationDateFromToken(token);
    return expiration.before(new Date());
  }

  // 生成 token
  public String generateToken(String username) {
    Map<String, Object> claims = new HashMap<>();
    return doGenerateToken(claims, username);
  }

  // 创建 JWT 的方法
  private String doGenerateToken(Map<String, Object> claims, String subject) {
    return Jwts.builder()
        .setClaims(claims)
        .setSubject(subject)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000)) // 30分钟过期
        .signWith(SignatureAlgorithm.HS256, secretKey)
        .compact();
  }

  // 验证 token
  public Boolean validateToken(String token, String username) {
    final String extractedUsername = getUsernameFromToken(token);
    return (extractedUsername.equals(username) && !isTokenExpired(token));
  }
}
