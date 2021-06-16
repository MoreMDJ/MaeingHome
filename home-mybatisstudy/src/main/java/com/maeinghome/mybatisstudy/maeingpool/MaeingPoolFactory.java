package com.maeinghome.mybatisstudy.maeingpool;

import org.apache.ibatis.datasource.DataSourceFactory;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

import javax.sql.DataSource;
import java.util.Properties;

public class MaeingPoolFactory extends UnpooledDataSourceFactory {

    @Override
    public void setProperties(Properties props) {

    }

    @Override
    public DataSource getDataSource() {
        return null;
    }
}
