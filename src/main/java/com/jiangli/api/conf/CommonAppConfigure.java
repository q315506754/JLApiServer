package com.jiangli.api.conf;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.jiangli.api.mapper", sqlSessionFactoryRef = "sqlSessionFactoryCommon")
public class CommonAppConfigure {
    protected Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${druid.common.url}")
    String jdbc;

    public CommonAppConfigure() {
        log.debug("CommonAppConfigure");
        log.info("CommonAppConfigure");
        log.warn("CommonAppConfigure");
        log.error("CommonAppConfigure");
        System.out.println("CommonAppConfigure");
    }

    /**
     * 公共数据源
     * @return
     */
    @Bean
    @ConfigurationProperties("druid.common")
    public DataSource dataSourceCommon() {
        DruidDataSource build = DruidDataSourceBuilder.create().
                build();
        return build;
    }

    /**
     * 公共数据源事务
     * @return
     */
    @Bean
    public PlatformTransactionManager transactionManagerCommon(@Qualifier("dataSourceCommon") DataSource dataSource) {
        PlatformTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
        return transactionManager;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactoryCommon(@Qualifier("dataSourceCommon") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        factoryBean.setConfiguration(configuration);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        factoryBean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
        factoryBean.setTypeAliasesPackage("com.jiangli.api.model");
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplateCommon(@Qualifier("sqlSessionFactoryCommon") SqlSessionFactory sessionFactory) throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sessionFactory);
        return template;
    }

}
