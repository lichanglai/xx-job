package com.zt.task.demo.jobhandler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import com.zt.task.demo.service.TestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 任务Handler示例（Bean模式）
 *
 * 开发步骤：
 * 1、继承"IJobHandler"：“com.xxl.job.core.handler.IJobHandler”；
 * 2、注册到Spring容器：添加“@Component”注解，被Spring容器扫描为Bean实例；
 * 3、注册到执行器工厂：添加“@JobHandler(value="自定义jobhandler名称")”注解，注解value值对应的是调度中心新建任务的JobHandler属性的值。
 * 4、执行日志：需要通过 "XxlJobLogger.log" 打印执行日志；
 */
@JobHandler(value="demoJobHandler")
@Component
public class DemoJobHandler extends IJobHandler {
	
	@Autowired
	private TestService  testser;
	
	@Override
	public ReturnT<String> execute(String param) throws Exception{
		XxlJobLogger.log("XXL-JOB, Hello World.");
	    
		List<Map<String,Object>>  list=testser.getAllTestData();
			System.out.println("alex-晓帅："+list.size());
		return SUCCESS;
	}

}
