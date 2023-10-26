package com.wttch.sdh.sdhserver.component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.annotation.Nullable;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 * JWT token 提供器。
 *
 * <p>生成、处理、判断 jwt 并转换为 Spring Security 的 Auth
 */
@Component
public class JwtTokenProvider {
  @Value("${jwt.secret:secret}")
  private String jwtSecret;

  @Value("${jwt.expire-time:3600000}")
  private long jwtExpireTime;

  @Resource private UserDetailsService userDetailsService;

  /** jwt 中 username 的 Key */
  private static final String USERNAME_KEY = "username";

  private Algorithm algorithm;

  @PostConstruct
  protected void init() {
    algorithm = Algorithm.HMAC256(jwtSecret);
  }

  /**
   * 根据用户名创建 jwt token 字符串。
   *
   * @param username 用户名
   * @return jwt token 字符串
   */
  public String createToken(String username) {
    return JWT.create()
        .withClaim(USERNAME_KEY, username)
        .withExpiresAt(Date.from(Instant.now().plusMillis(jwtExpireTime)))
        .sign(algorithm);
  }

  /**
   * 解析 jwt token 字符串。
   *
   * @param jwt jwt token 字符串
   * @return 解析的用户信息，用于 spring security 验证
   */
  public UserDetails parseToken(String jwt) {
    var username = JWT.require(algorithm).build().verify(jwt).getClaim(USERNAME_KEY).asString();
    return userDetailsService.loadUserByUsername(username);
  }

  /**
   * 解析 jwt token，并生成 auth 身份认证信息。
   *
   * @param jwt jwt token 字符串。
   * @return auth 身份认证信息
   */
  public Authentication parseAuth(String jwt) {
    var userDetail = parseToken(jwt);

    return new UsernamePasswordAuthenticationToken(userDetail, "", userDetail.getAuthorities());
  }

  /**
   * 从 http 请求中获取 jwt token。
   *
   * @param req http 请求
   * @return http 请求中包含的 jwt token
   */
  @Nullable
  public String resolveToken(HttpServletRequest req) {
    String bearerToken = req.getHeader("Authorization");
    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }

  /**
   * 验证 jwt 是否过时
   *
   * @param jwt jwt token
   * @return 没过时返回 true, 如果过时则返回 false
   */
  public boolean validateToken(String jwt) {
    try {
      var expiresAt = JWT.require(algorithm).build().verify(jwt).getExpiresAt();
      return !expiresAt.before(new Date());
    } catch (Exception e) {
      return false;
    }
  }
}
