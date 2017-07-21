package com.example.demo.spring.dao;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;
import com.example.demo.spring.paginate.OffsetLimitInterceptor;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"com.example.demo.core.mapper.oracle"},sqlSessionFactoryRef = "secSqlSessionFactory")
public class MyBatisOracleConfig {

    @Resource(name = "txManagerOracle")
    private PlatformTransactionManager txManagerOracle;
    
    @Bean(name = "secondaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource secondaryDataSource(){
        return DataSourceBuilder.create().type(DruidDataSource.class).build();
    }
    
    @Bean(name = "secSqlSessionFactory")
    public SqlSessionFactory secSqlSessionFactoryBean(@Qualifier("secondaryDataSource") DataSource secondaryDataSource) throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(secondaryDataSource);
        OffsetLimitInterceptor interceptor  = new OffsetLimitInterceptor();
        interceptor.setDialectClass("com.example.demo.spring.paginate.dialect.OracleDialect");
        sqlSessionFactoryBean.setPlugins(new Interceptor[] { interceptor });
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mapper/oracle/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }
    
    // 创建事务管理器
    @Bean(name = "txManagerOracle")
    public PlatformTransactionManager txManagerOracle() {
        return new DataSourceTransactionManager(secondaryDataSource());
    }
}
