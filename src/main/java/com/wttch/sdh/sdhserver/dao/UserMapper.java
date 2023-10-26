package com.wttch.sdh.sdhserver.dao;

import com.wttch.sdh.sdhserver.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
  int deleteByPrimaryKey(Integer id);

  int insert(User user);

  int insertSelective(User user);

  User selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(User user);

  int updateByPrimaryKey(User user);

  /**
   * 根据用户名查询
   *
   * @param username 用户名
   */
  User selectByUserName(String username);
}
