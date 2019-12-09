package com.jiangli.api.conf;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.File;

import static com.jiangli.api.conf.KotlinConfigForSpringBoot2Kt.postProcess;


@Configuration
@MapperScan(basePackages = "com.jiangli.api.mapper", sqlSessionFactoryRef = "sqlSessionFactoryCommon")
public class CommonAppConfigure {
    protected Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${druid.common.url}")
    String jdbc;

    @Autowired
    ApplicationConfig applicationConfig;

    @Autowired
    ConfigurationPropertiesBindingPostProcessor processor;

    public CommonAppConfigure() {
        log.debug("CommonAppConfigure");
        log.info("CommonAppConfigure");
        log.warn("CommonAppConfigure");
        log.error("CommonAppConfigure");
        System.out.println("CommonAppConfigure");
    }


    public File getBaseJarPath() {
        ApplicationHome home = new ApplicationHome(CommonAppConfigure.class);
        File jarFile = home.getSource();

        //for test env
        if (jarFile == null) {
            File target = new File(home.getDir(), "target");
            if (target.exists()) {
                return target;
            }

        }
        return jarFile.getParentFile();
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
     * 使用本地配置
     * @param dataSource
     * @return
     */
    @Bean
    public DataSource dataSourceModified(@Qualifier("dataSourceCommon") DataSource dataSource) {
        DruidDataSource build = (DruidDataSource) dataSource;
        System.out.println(getBaseJarPath());
        System.out.println(applicationConfig);

        log.warn("before config:{}",applicationConfig);
        postProcess(processor,applicationConfig,getBaseJarPath(),"config.properties");
        log.warn("after config:{}",applicationConfig);

        //log.warn("before:{}",dataSource);
        log.warn("before build:{}",build.getUrl());
        try {
            BeanUtils.copyProperties(applicationConfig,build);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //log.warn("after:{}",build);
        log.warn("after build:{}",build.getUrl());

        return dataSource;
    }

    /**
     * 公共数据源事务
     * @return
     */
    @Bean
    public PlatformTransactionManager transactionManagerCommon(@Qualifier("dataSourceModified") DataSource dataSource) {
        PlatformTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
        return transactionManager;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactoryCommon(@Qualifier("dataSourceModified") DataSource dataSource) throws Exception {
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
