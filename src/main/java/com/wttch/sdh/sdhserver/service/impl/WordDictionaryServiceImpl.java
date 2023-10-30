package com.wttch.sdh.sdhserver.service.impl;

import com.wttch.sdh.sdhserver.dao.WordDictionaryMapper;
import com.wttch.sdh.sdhserver.dao.WordMapper;
import com.wttch.sdh.sdhserver.entity.WordDictionary;
import com.wttch.sdh.sdhserver.entity.vo.WordDictionaryVO;
import com.wttch.sdh.sdhserver.service.WordDictionaryService;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class WordDictionaryServiceImpl implements WordDictionaryService {
  @Resource private WordDictionaryMapper wordDictionaryMapper;
  @Resource private WordMapper wordMapper;

  @Override
  public List<WordDictionary> findDictionaryByUserId(Integer userId) {
    return wordDictionaryMapper.selectByUserId(userId);
  }

  @Override
  public List<WordDictionaryVO> findDictionaryWordByIds(Integer[] ids) {
    return wordDictionaryMapper.selectByIds(ids).stream()
        .map(
            wd -> {
              var vo = new WordDictionaryVO();
              BeanUtils.copyProperties(wd, vo);
              vo.setWords(wordMapper.selectByWordDictionaryId(wd.getId()));
              return vo;
            })
        .collect(Collectors.toList());
  }
}
