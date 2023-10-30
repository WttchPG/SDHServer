package com.wttch.sdh.sdhserver.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wttch.sdh.sdhserver.entity.vo.APIResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

/**
 * JWT 过滤器。
 *
 * <p>获取 token 并解析，如果是正确的 token 直接颁发 auth 身份认证信息。
 */
@AllArgsConstructor
public class JwtTokenFilter extends GenericFilterBean {
  private JwtTokenProvider tokenProvider;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    if (tokenProvider.isLogin(request)) {
      chain.doFilter(request, response);
      return;
    }

    String token = tokenProvider.resolveToken((HttpServletRequest) request);
    if (Objects.nonNull(token) && tokenProvider.validateToken(token)) {
      // 验证 token
      Authentication auth = tokenProvider.parseAuth(token);
      // 注入 身份认证
      SecurityContextHolder.getContext().setAuthentication(auth);
      chain.doFilter(request, response);
    } else {
      // 直接抛出异常会报 500 错误
      var resp = (HttpServletResponse) response;
      APIResponse<String> re = APIResponse.error(401, "jwt 无效!");
      resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
      resp.setStatus(HttpServletResponse.SC_OK);
      OutputStream responseStream = resp.getOutputStream();
      ObjectMapper mapper = new ObjectMapper();
      mapper.writeValue(responseStream, re);
      responseStream.flush();
    }
  }
}
