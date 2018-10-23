package com.zt.task.demo.config.database.mysql;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.github.pagehelper.PageHelper;

/**
* @ClassName: MybatisMySqlConfig 
* @Description:  mysql 数据库配置
* @author Alex-晓帅
* @date 2018年9月10日 下午1:52:06
 */
@Configuration
@MapperScan(basePackages = { "com.zt.**.dao.mysql" }, sqlSessionTemplateRef = "mysqlSessionTemplate")
public class MybatisMySqlConfig {
	
	@Value("${mybatismysqlconfig.mapper_locations}")
	private String mapper_locations;
	
	@Autowired
	org.apache.ibatis.session.Configuration mybatisConfig;
	
	@Bean(name = "mysqlDS")
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource.mysql") // application.properteis中对应属性的前缀
	public DataSource mysqlDS() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean
	@Primary
    public SqlSessionFactory mysqlSessionFactory(@Qualifier("mysqlDS") DataSource dataSource)throws Exception
	{
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource); // 使用titan数据源, 连接titan库
		
		//添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
        	factoryBean.setConfiguration(mybatisConfig);
        	factoryBean.setMapperLocations(resolver.getResources(mapper_locations));
            return factoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
	}

	@Bean
	@Primary
    public PlatformTransactionManager mysqlTransactionManager(@Qualifier("mysqlDS")DataSource mysqlDS) {
     return new DataSourceTransactionManager(mysqlDS);
    }
	
	
	@Bean
	@Primary
	public SqlSessionTemplate mysqlSessionTemplate(@Qualifier("mysqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory); // 使用上面配置的Factory
		return template;
	}
	
	@Bean
	@Primary
	public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("helperDialect", "mysql");
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "false");
        p.setProperty("returnPageInfo", "check");
        p.setProperty("params", "count=countSql");
        pageHelper.setProperties(p);
        return pageHelper;
    }

	
	public String getMapper_locations() {
		return mapper_locations;
	}

	public void setMapper_locations(String mapper_locations) {
		this.mapper_locations = mapper_locations;
	}

}