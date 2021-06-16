package com.maeinghome.mybatisstudy.service.impl;

import com.maeinghome.mybatisstudy.mapper.EvaluationQuestionScoreMapper;
import com.maeinghome.mybatisstudy.service.TestService;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public String getName() {
        Configuration configuration = sqlSessionFactory.getConfiguration();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EvaluationQuestionScoreMapper mapper = sqlSession.getMapper(EvaluationQuestionScoreMapper.class);
        return mapper.findTest();
    }
}
