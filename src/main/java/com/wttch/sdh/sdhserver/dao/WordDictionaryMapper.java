package com.wttch.sdh.sdhserver.dao;

import com.wttch.sdh.sdhserver.entity.WordDictionary;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WordDictionaryMapper {
  int deleteByPrimaryKey(Integer id);

  int insert(WordDictionary record);

  int insertSelective(WordDictionary record);

  WordDictionary selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(WordDictionary record);

  int updateByPrimaryKey(WordDictionary record);

  /**
   * 根据用户 id 查询。
   *
   * <p>查询用户 id 创建的词典和系统默认的词典。
   *
   * @param userId 用户 id
   * @return 用户 id 创建的词典和系统默认的词典
   */
  List<WordDictionary> selectByUserId(Integer userId);

  /**
   * 根据id列表查询。
   *
   * @param ids id 列表
   * @return 词典列表
   */
  List<WordDictionary> selectByIds(@Param("ids") Integer[] ids);
}
