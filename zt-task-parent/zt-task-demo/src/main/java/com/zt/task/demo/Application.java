package com.zt.task.demo;

import org.apache.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
* @ClassName: Application 
* @Description:  任务调度模板启动类
* @author Alex-晓帅
* @date 2018年9月8日 下午3:34:25
 */
@EnableSwagger2
@SpringBootApplication(scanBasePackages={"com.zt"}, exclude = {DataSourceAutoConfiguration.class})
@MapperScan({"com.zt.task.demo"})
@EnableCaching
public class Application 
{
	private static Logger log = Logger.getLogger(Application.class);

    public static void main( String[] args )
    {
    	SpringApplication.run(Application.class, args);
		log.info("SpringBoot启动！");
    }
}
