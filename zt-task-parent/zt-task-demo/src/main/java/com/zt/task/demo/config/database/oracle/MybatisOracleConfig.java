package com.zt.task.demo.config.database.oracle;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.github.pagehelper.PageHelper;
/**
* @ClassName: MybatisOracleConfig 
* @Description:  oracle 数据库配置
* @author Alex-晓帅
* @date 2018年9月10日 下午1:51:35
 */
@Configuration
@MapperScan(basePackages = {"com.zt.**.dao.oracle"},  sqlSessionTemplateRef = "oracleSessionTemplate")
public class MybatisOracleConfig {
	
	@Value("${mybatisoracleconfig.mapper_locations}")
	private String mapper_locations;
	
	@Bean(name = "oracleDS")
	@ConfigurationProperties(prefix = "spring.datasource.oracle") // application.properteis中对应属性的前缀
	public DataSource oracleDS() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean
    public PlatformTransactionManager oralceTransactionManager(@Qualifier("oracleDS")DataSource oracleDS) {
     return new DataSourceTransactionManager(oracleDS);
    }
	
	@Bean
    public SqlSessionFactory oracleSessionFactory(@Qualifier("oracleDS") DataSource dataSource)throws Exception
	{
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource); // 使用titan数据源, 连接titan库
		//添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
        	factoryBean.setMapperLocations(resolver.getResources(mapper_locations));
            return factoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
	}

	@Bean
	public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("helperDialect", "oracle");
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "false");
        p.setProperty("returnPageInfo", "check");
        p.setProperty("params", "count=countSql");
        pageHelper.setProperties(p);
        return pageHelper;
    }

	@Bean
	public SqlSessionTemplate oracleSessionTemplate(@Qualifier("oracleSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory); // 使用上面配置的Factory
		return template;
	}
	
	public String getMapper_locations() {
		return mapper_locations;
	}

	public void setMapper_locations(String mapper_locations) {
		this.mapper_locations = mapper_locations;
	}

}