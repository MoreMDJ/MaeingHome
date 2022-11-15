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
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.*;
import java.util.Date;
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
//        StringContains();
        for (int i = 0; i < 10; i++) {
            exeInsert();
        }
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

    public static void exeInsert() throws ClassNotFoundException, SQLException {
        final String url = "jdbc:mysql://10.173.42.51:3306/cerebrum_dev?serverTimezone=Asia/Shanghai&characterEncoding=utf8&useSSL=false";
        final String name = "com.mysql.jdbc.Driver";
        final String user = "cerebrum";
        final String password = "Iddc123456!@";
        Connection conn = null;
        Class.forName(name);//指定连接类型
        conn = DriverManager.getConnection(url, user, password);//获取连接
        if (conn != null) {
            System.out.println("获取连接成功");
            insert(conn);
        } else {
            System.out.println("获取连接失败");
        }
    }

    public static String getRandomChar() {
        StringBuilder sb = new StringBuilder();
        for (int i1 = 0; i1 <= new Random().nextInt(9); i1++) {
            sb.append(getRandomChar01());
        }
        return sb.toString();
    }

    private static Integer getRandomId() {
        return new Random().nextInt(Integer.MAX_VALUE);
    }

    private static char getRandomChar01() {
        String str = "";
        int hightPos; //
        int lowPos;
        Random random = new Random();
        hightPos = (176 + Math.abs(random.nextInt(39)));
        lowPos = (161 + Math.abs(random.nextInt(93)));
        byte[] b = new byte[2];
        b[0] = (Integer.valueOf(hightPos)).byteValue();
        b[1] = (Integer.valueOf(lowPos)).byteValue();
        try {
            str = new String(b, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println("错误");
        }
        return str.charAt(0);
    }

    public static void insert(Connection conn) {
        // 开始时间
        Long begin = new java.util.Date().getTime();
        // sql前缀
        //String prefixs = "`cs_info0` (`name`, `password`, `gotowhere`, `userinfo`, `nicename`, `age`, `create_user`, `update_user`, `deleted`, `whereisname`, `thisname`, `infoname`, `passinfo`, `getuserinfo`, `nobasedate`, `whereisne`, `hwois`, `catename`, `companyname`, `credicode`, `ads`, `bbss`, `type`, `duty`, `perfornanc`, `perforpart`, `aaaa`, `name1`, `password1`, `gotowhere1`, `userinfo1`, `nicename1`, `age1`, `create_user1`, `update_user1`, `deleted1`, `whereisname1`, `thisname1`, `infoname1`, `passinfo1`, `getuserinfo1`, `nobasedate1`, `whereisne1`, `hwois1`, `catename1`, `companyname1`, `credicode1`, `ads1`, `bbss1`, `type1`, `duty1`, `perfornanc1`, `perforpart1`, `name12`, `password12`, `gotowhere12`, `userinfo12`, `nicename12`, `age12`, `create_user12`, `update_user12`, `deleted12`, `whereisname12`, `thisname12`, `infoname12`, `passinfo12`, `getuserinfo12`, `nobasedate12`, `whereisne12`, `hwois12`, `catename12`, `companyname12`, `credicode12`, `ads12`, `bbss12`, `type12`, `duty12`, `perfornanc12`, `perforpart12`, `create_time`, `create_by`) VALUES ('1l', '1', '2', '3', '3', '3', '3', '3', '0', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '2021-11-25 17:08:05', '系统生成');";
        String preFixInfo = "insert into `cs_info4` (`name`, `password`, `gotowhere`, `userinfo`, `nicename`, `age`, `create_user`, `update_user`, `deleted`, `whereisname`, `thisname`, `infoname`, `passinfo`, `getuserinfo`, `nobasedate`, `whereisne`, `hwois`, `catename`, `companyname`, `credicode`, `ads`, `bbss`, `type`, `duty`, `perfornanc`, `perforpart`, `aaaa`, `name1`, `password1`, `gotowhere1`, `userinfo1`, `nicename1`, `age1`, `create_user1`, `update_user1`, `deleted1`, `whereisname1`, `thisname1`, `infoname1`, `passinfo1`, `getuserinfo1`, `nobasedate1`, `whereisne1`, `hwois1`, `catename1`, `companyname1`, `credicode1`, `ads1`, `bbss1`, `type1`, `duty1`, `perfornanc1`, `perforpart1`, `name12`, `password12`, `gotowhere12`, `userinfo12`, `nicename12`, `age12`, `create_user12`, `update_user12`, `deleted12`, `whereisname12`, `thisname12`, `infoname12`, `passinfo12`, `getuserinfo12`, `nobasedate12`, `whereisne12`, `hwois12`, `catename12`, `companyname12`, `credicode12`, `ads12`, `bbss12`, `type12`, `duty12`, `perfornanc12`, `perforpart12`, `create_time`, `create_by`) VALUES";
        String prefix = "`evaluation_question_score_copy` (`baseuser_id`, `evaluation_id`, `question_id`, `others_score`, `high_level_score`, `same_level_score`, `lower_level_score`, `self_score`, `high_valid_count`, `same_valid_count`, `lower_valid_count`, `created_user_id`, `created_date`, `modified_user_id`, `modified_date`, `deleted`) VALUES";
        String prefixInfoKey = "INSERT INTO `cs_info_key_001` (`key`, `relate_id`) VALUES ";

        try {
            // 保存sql后缀
            StringBuffer suffix = new StringBuffer();
            // 设置事务为非自动提交
            conn.setAutoCommit(false);
            // 比起st，pst会更好些
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement("select count(*) from cs_info9;");//准备执行语句
            // 外层循环，总提交事务次数
            for (int i = 1; i <= 100; i++) {
                suffix = new StringBuffer();
                // 第j次提交步长
                for (int j = 1; j <= 100000; j++) {
                    // 构建SQL后缀
                    //suffix.append("('" + uutil.UUIDUtil.getUUID()+"','"+i*j+"','123456'"+ ",'男'"+",'教师'"+",'www.bbk.com'"+",'XX大学'"+",'"+"2016-08-12 14:43:26"+"','备注'" +"),");
                    //suffix.append("(1309161, 1, 1, -9999.00000, -9999.00000, -9999.00000, -9999.00000, 1.00000, 0, 0, 0, 9999, '2022-03-17 15:46:36', 9999, '2022-03-17 15:50:46', 0),");
                    //suffix.append("('1l', '1', '2', '3', '3', '3', '3', '3', '0', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '2021-11-25 17:08:05', '系统生成'),");

                    suffix.append("('" + getRandomChar() + "', '" + getRandomId() + "'),");
                }
                // 构建完整SQL
                String sql = prefixInfoKey + suffix.substring(0, suffix.length() - 1);
                // 添加执行SQL
                pst.addBatch(sql);
                // 执行操作
                pst.executeBatch();
                // 提交事务
                conn.commit();
                // 清空上一次添加的数据
                suffix = new StringBuffer();
            }
            // 头等连接
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 结束时间
        Long end = new Date().getTime();
        // 耗时
        System.out.println("1000万条数据插入花费时间 : " + (end - begin) / 1000 + " s");
        System.out.println("插入完成");
    }
}
