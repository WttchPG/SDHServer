package com.wttch.sdh.sdhserver.entity.payload;

/**
 * 登录请求载体
 *
 * @param username 用户名
 * @param password 密码
 */
public record LoginPayload(String username, String password) {}
