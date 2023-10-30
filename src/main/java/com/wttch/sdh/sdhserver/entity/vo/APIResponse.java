package com.wttch.sdh.sdhserver.entity.vo;

public record APIResponse<T>(int code, String message, T data) {

  public static APIResponse<String> error(int code, String message) {
    return new APIResponse<>(code, message, null);
  }

  /**
   * 构造代码为 200 的成功响应。
   *
   * @param message 响应消息。
   * @param data 响应数据
   * @return 构造的响应
   * @param <T> 响应数据的类型。
   */
  public static <T> APIResponse<T> success(String message, T data) {
    return new APIResponse<>(200, message, data);
  }

  /**
   * 构造代码为 200 的成功响应。
   *
   * @param data 响应数据
   * @return 构造的响应
   * @param <T> 响应数据的类型。
   */
  public static <T> APIResponse<T> success(T data) {
    return APIResponse.success("请求成功", data);
  }
}
