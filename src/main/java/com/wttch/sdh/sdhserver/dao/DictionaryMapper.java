package com.wttch.sdh.sdhserver.dao;

import com.wttch.sdh.sdhserver.entity.Dictionary;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DictionaryMapper {
  int deleteByPrimaryKey(Integer id);

  int insert(Dictionary record);

  int insertSelective(Dictionary record);

  Dictionary selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(Dictionary record);

  int updateByPrimaryKey(Dictionary record);

  /**
   * 根据用户 id 查询。
   *
   * <p>查询用户 id 创建的词典和系统默认的词典。
   *
   * @param userId 用户 id
   * @return 用户 id 创建的词典和系统默认的词典
   */
  List<Dictionary> selectByUserId(Integer userId);
}
