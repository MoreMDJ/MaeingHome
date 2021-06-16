package com.maeinghome.mybatisstudy;

import com.maeinghome.mybatisstudy.entity.EvaluationQuestionScore;
import com.maeinghome.mybatisstudy.maeingpool.info.UserContextHolder;
import com.maeinghome.mybatisstudy.mapper.EvaluationQuestionScoreMapper;
import com.maeinghome.tool.times.SystemClock;
import org.apache.ibatis.datasource.pooled.PooledDataSourceFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

public class MybatisTest {

    private static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String MYSQL_URL = "jdbc:mysql://172.16.5.14:3306/bclp?allowMultiQueries=true&useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai";
    private static final String MYSQL_USERNAME = "bclp";
    private static final String MYSQL_PASSWORD = "T9VjQUAIDJHrfdwA";

    public static void main(String[] args) throws Throwable {
        initMybatis(null);
    }

    public static void initMybatis(String[] args) throws IOException {
        long now = SystemClock.now();
        UserContextHolder.userName.set("test");
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(inputStream);
        Configuration configuration = ssf.getConfiguration();
        SqlSession sqlSession = ssf.openSession();
        System.out.println("args = " + (SystemClock.now() - now));
        EvaluationQuestionScoreMapper mapper = sqlSession.getMapper(EvaluationQuestionScoreMapper.class);
        List<EvaluationQuestionScore> all = mapper.findAll(1L);

        mapper.findAll(1L);
    }

    public static void connectTest() throws SQLException {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        PooledDataSourceFactory pdsf = new PooledDataSourceFactory();
        Properties properties = new Properties();
        properties.setProperty("driver",MYSQL_DRIVER);
        properties.setProperty("url",MYSQL_URL);
        properties.setProperty("username",MYSQL_USERNAME);
        properties.setProperty("password",MYSQL_PASSWORD);
        pdsf.setProperties(properties);
        DataSource dataSource = pdsf.getDataSource();
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        String s = connection.nativeSQL("select * from evaluation_question_score where id < 2");
        PreparedStatement preparedStatement = connection.prepareStatement("select * from evaluation_question_score where id < 2");
        ResultSet resultSet = preparedStatement.executeQuery();
    }

    public static void originSql() throws ClassNotFoundException, SQLException {
        System.out.println();
        Class.forName(MYSQL_DRIVER);
        Properties properties = new Properties();
        properties.setProperty("driver",MYSQL_DRIVER);
        properties.setProperty("url",MYSQL_URL);
        properties.setProperty("username",MYSQL_USERNAME);
        properties.setProperty("password",MYSQL_PASSWORD);
        DriverManager.getConnection(MYSQL_URL,properties);
    }
    public static void testMethod(){
        DotMethod method = new DotMethod();
        System.out.println(method.noGetter);
        System.out.println(method.hasGetter);
    }

    public static void testSpringBoot(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    }
}
