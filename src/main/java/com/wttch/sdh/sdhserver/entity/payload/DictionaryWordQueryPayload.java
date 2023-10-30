package com.wttch.sdh.sdhserver.entity.payload;

/**
 * 查询词典请求载体。
 *
 * @param ids 词典 id 数组
 */
public record DictionaryWordQueryPayload(Integer[] ids) {}
