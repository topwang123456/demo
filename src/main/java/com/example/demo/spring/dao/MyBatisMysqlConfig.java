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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import com.alibaba.druid.pool.DruidDataSource;
import com.example.demo.spring.paginate.OffsetLimitInterceptor;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"com.example.demo.core.mapper.mysql"},sqlSessionFactoryRef = "sqlSessionFactory")
public class MyBatisMysqlConfig implements TransactionManagementConfigurer {
    
    @Resource(name = "txManagerMysql")
    private PlatformTransactionManager txManagerMysql;
    
    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource.first")
    public DataSource dataSource(){
        return DataSourceBuilder.create().type(DruidDataSource.class).build();
    }
    
    @Bean("sqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactoryBean(@Qualifier("dataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        OffsetLimitInterceptor interceptor  = new OffsetLimitInterceptor();
        interceptor.setDialectClass("com.example.demo.spring.paginate.dialect.MySQLDialect");
        sqlSessionFactoryBean.setPlugins(new Interceptor[] { interceptor });
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mapper/mysql/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    // 创建事务管理器
    @Bean(name = "txManagerMysql")
    public PlatformTransactionManager txManagerMysql() {
        return new DataSourceTransactionManager(dataSource());
    }
    
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
       return txManagerMysql; 
    }
}
