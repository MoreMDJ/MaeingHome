package com.maeinghome.mybatisstudy.mapper;

import com.maeinghome.mybatisstudy.entity.EvaluationQuestionScore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EvaluationQuestionScoreMapper {
    List<EvaluationQuestionScore> findAll(@Param("id") Long id);

    String findTest();
}