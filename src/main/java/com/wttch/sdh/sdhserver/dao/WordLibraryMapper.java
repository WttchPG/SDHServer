package com.wttch.sdh.sdhserver.dao;

import com.wttch.sdh.sdhserver.entity.WordLibrary;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WordLibraryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WordLibrary record);

    int insertSelective(WordLibrary record);

    WordLibrary selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WordLibrary record);

    int updateByPrimaryKey(WordLibrary record);
}