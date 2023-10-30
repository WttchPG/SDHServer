package com.wttch.sdh.sdhserver.api;

import com.wttch.sdh.sdhserver.entity.payload.DictionaryWordQueryPayload;
import com.wttch.sdh.sdhserver.entity.vo.APIResponse;
import com.wttch.sdh.sdhserver.entity.vo.WordDictionaryVO;
import com.wttch.sdh.sdhserver.service.WordDictionaryService;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/word-dictionary")
public class WordDictionaryController extends AbstractController {
  @Resource private WordDictionaryService wordDictionaryService;

  /**
   * 获取用户的词典列表。
   *
   * <p>包含系统和用户自己创建的。
   */
  @PostMapping("list")
  public APIResponse<List<WordDictionaryVO>> dictionaryList() {
    var ret =
        wordDictionaryService.findDictionaryByUserId(currentUser().getId()).stream()
            .map(
                wd -> {
                  var vo = new WordDictionaryVO();
                  BeanUtils.copyProperties(wd, vo);
                  return vo;
                })
            .collect(Collectors.toList());
    return APIResponse.success(ret);
  }

  /** 根据 id 查询词典的所有单词。 */
  @PostMapping("words")
  public APIResponse<List<WordDictionaryVO>> dictionaryWords(
      @RequestBody DictionaryWordQueryPayload payload) {
    var ret = wordDictionaryService.findDictionaryWordByIds(payload.ids());
    return APIResponse.success(ret);
  }
}
