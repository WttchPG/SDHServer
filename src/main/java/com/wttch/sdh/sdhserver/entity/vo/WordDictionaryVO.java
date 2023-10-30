package com.wttch.sdh.sdhserver.entity.vo;

import com.wttch.sdh.sdhserver.entity.Word;
import com.wttch.sdh.sdhserver.entity.WordDictionary;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WordDictionaryVO extends WordDictionary {
  private List<Word> words;
}
