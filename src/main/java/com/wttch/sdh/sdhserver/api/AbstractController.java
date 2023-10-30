package com.wttch.sdh.sdhserver.api;

import com.wttch.sdh.sdhserver.component.SdhUserDetails;
import com.wttch.sdh.sdhserver.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

/** Controller 基类，方便获取已经登录的用户信息。 */
public abstract class AbstractController {

  protected SdhUserDetails currentUserDetail() {
    return (SdhUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  }

  protected User currentUser() {
    return currentUserDetail().getUser();
  }
}
