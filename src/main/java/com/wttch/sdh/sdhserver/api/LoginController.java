package com.wttch.sdh.sdhserver.api;

import com.wttch.sdh.sdhserver.component.JwtTokenProvider;
import com.wttch.sdh.sdhserver.component.SdhUserDetails;
import com.wttch.sdh.sdhserver.entity.User;
import com.wttch.sdh.sdhserver.entity.payload.LoginPayload;
import com.wttch.sdh.sdhserver.entity.vo.APIResponse;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/** 登录接口 */
@RestController
@RequestMapping("/auth")
public class LoginController {

  @Resource private AuthenticationManager authenticationManager;

  @Resource private JwtTokenProvider jwtTokenProvider;

  /** 登录 */
  @PostMapping("login")
  public APIResponse<String> login(@RequestBody LoginPayload payload) {
    try {
      var auth =
          authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(payload.username(), payload.password()));

      if (auth.isAuthenticated()) {
        var token = jwtTokenProvider.createToken(payload.username());

        return APIResponse.success("登录成功", token);
      }
    } catch (Exception e) {
      return APIResponse.error(401, "账号或密码错误！");
    }

    return APIResponse.error(401, "账号或密码错误！");
  }

  /**
   * 获取登录的用户信息
   *
   * @param userDetail 登录的用户信息，spring 自动装配的
   */
  @GetMapping("user")
  public APIResponse<User> userinfo(@AuthenticationPrincipal SdhUserDetails userDetail) {
    var user = userDetail.getUser();
    // 脱敏密码
    user.setPassword(null);
    return APIResponse.success(user);
  }
}
