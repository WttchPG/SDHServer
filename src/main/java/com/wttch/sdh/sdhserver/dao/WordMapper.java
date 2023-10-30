package com.wttch.sdh.sdhserver.dao;

import com.wttch.sdh.sdhserver.entity.Word;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WordMapper {
  int deleteByPrimaryKey(Integer id);

  int insert(Word record);

  int insertSelective(Word record);

  Word selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(Word record);

  int updateByPrimaryKey(Word record);

  /**
   * 根据词典 id 查询词典包含的所有单词。
   *
   * @param dictionaryId 词典id
   * @return 词典包含的所有单词
   */
  List<Word> selectByWordDictionaryId(@Param("dictionaryId") Integer dictionaryId);
}
