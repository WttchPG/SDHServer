package com.wttch.sdh.sdhserver.service;

import com.wttch.sdh.sdhserver.entity.WordDictionary;
import com.wttch.sdh.sdhserver.entity.vo.WordDictionaryVO;
import java.util.List;

/**
 * 词典服务。
 *
 * <p>词典包含系统给定的词典和用户自建的，包含生词本等。
 */
public interface WordDictionaryService {

  /**
   * 根据用户 id 查询词典列表。
   *
   * @param userId 用户 id
   * @return 用户的词典列表和系统默认的词典
   */
  List<WordDictionary> findDictionaryByUserId(Integer userId);

  /**
   * 根据 id 查询词典。
   *
   * <p>同时从单词中查询词典的所有单词。
   *
   * @param ids 词典 id 数组
   * @return 词典列表，同时包含词典中所有单词
   */
  List<WordDictionaryVO> findDictionaryWordByIds(Integer[] ids);
}
