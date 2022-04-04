package com.maeinghome.mybatisstudy;

import com.maeinghome.mybatisstudy.entity.EvaluationQuestionScore;
import com.maeinghome.mybatisstudy.maeingpool.info.UserContextHolder;
import com.maeinghome.mybatisstudy.mapper.EvaluationQuestionScoreMapper;
import com.maeinghome.util.times.SystemClock;
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
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MybatisTest {

    private static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String MYSQL_URL = "jdbc:mysql://172.16.5.14:3306/bclp?allowMultiQueries=true&useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai";
    private static final String MYSQL_USERNAME = "bclp";
    private static final String MYSQL_PASSWORD = "T9VjQUAIDJHrfdwA";

    public static void main(String[] args) throws Throwable {
//        initMybatis(null);
        StringContains();
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
        properties.setProperty("driver", MYSQL_DRIVER);
        properties.setProperty("url", MYSQL_URL);
        properties.setProperty("username", MYSQL_USERNAME);
        properties.setProperty("password", MYSQL_PASSWORD);
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
        properties.setProperty("driver", MYSQL_DRIVER);
        properties.setProperty("url", MYSQL_URL);
        properties.setProperty("username", MYSQL_USERNAME);
        properties.setProperty("password", MYSQL_PASSWORD);
        DriverManager.getConnection(MYSQL_URL, properties);
    }

    public static void testMethod() {
        DotMethod method = new DotMethod();
        System.out.println(method.noGetter);
        System.out.println(method.hasGetter);
    }

    public static void testSpringBoot() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    }

    public static void StringContains() {
        System.out.println("1231".contains("1231"));
    }

    public static void StringBuilderTest() {
        StringBuilder sb = new StringBuilder(3);
        sb.append("abc");
        sb.append("def");
        sb.setLength(7);
        System.out.println(sb.toString());

    }

    public void threah() {
        new Thread();
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(10, 200, 20, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5));

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
        for (int i = 0; i < 100; i++) {
            MyTask task = new MyTask(i);
            threadPoolExecutor.execute(task);
        }

    }

    class MyTask implements Runnable {
        private int taskNum;

        public MyTask(int num) {
            this.taskNum = num;
        }

        @Override
        public void run() {
            System.out.println("正在执行task " + taskNum);
            try {
                Thread.currentThread().sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task " + taskNum + "执行完毕");
        }
    }
}
