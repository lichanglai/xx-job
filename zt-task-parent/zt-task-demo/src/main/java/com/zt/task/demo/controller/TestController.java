package com.zt.task.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zt.task.demo.service.BatchOrderService;
import com.zt.task.demo.service.TestService;
import com.zt.task.demo.util.JsonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
* @ClassName: TestController 
* @Description:  测试控制类
* @author Alex-晓帅
* @date 2018年9月10日 下午12:23:15
 */
@Api("测试控制类")
@RestController
@RequestMapping(value="/testapi")
public class TestController {

	
	@Autowired
	@Qualifier(value="oracle_testservice")
	private TestService testser;
	
	@Autowired
	@Qualifier(value="mysql_testservice")
	private BatchOrderService batoerser;
	
	
	@ApiOperation(value="从oracle数据库获取值")
	@PostMapping(value="/getOracleData")
	public @ResponseBody Object getOracleData() {
		JsonResult jr=new  JsonResult();
		try {
			List<Map<String,Object>>   testmap=testser.getAllTestData();
			jr.setCode(200);
			jr.setMsg("查询成功");
			jr.setSuccess(true);
			jr.setObj(testmap);
		} catch (Exception e) {
			jr.setCode(102);
			jr.setMsg("Exception异常提示:"+e.getMessage());
			jr.setSuccess(false);
		}
		return  jr;
	}
	
	@ApiOperation(value="从mysql数据库获取值")
	@PostMapping(value="/getmysqlData")
	public @ResponseBody Object getmysqlData() {
		JsonResult jr=new  JsonResult();
		try {
			List<Map<String,Object>>   mysqltestmap=batoerser.getAllBatchOrderData();
			jr.setCode(200);
			jr.setMsg("查询成功");
			jr.setSuccess(true);
			jr.setObj(mysqltestmap);
		} catch (Exception e) {
			jr.setCode(102);
			jr.setMsg("Exception异常提示:"+e.getMessage());
			jr.setSuccess(false);
		}
		return  jr;
	}
	
	@ApiOperation(value="从两个数据库取值")
	@PostMapping(value="/getMysqlAndOracleData")
	public @ResponseBody Object getMysqlAndOracleData() {
		JsonResult jr=new  JsonResult();
		try {
			List<Map<String,Object>>  listobj=new ArrayList<>();
			Map<String,Object> responsedata=new  HashMap<>();
			List<Map<String,Object>>   mysqlmap=batoerser.getAllBatchOrderData();
			responsedata.put("mysql", mysqlmap);
			List<Map<String,Object>>   oraclemap=testser.getAllTestData();
			responsedata.put("oracle", oraclemap);
			responsedata.put("author", "Alex-晓帅");
			listobj.add(responsedata);
			jr.setCode(200);
			jr.setMsg("查询成功");
			jr.setSuccess(true);
			jr.setObj(listobj);
		} catch (Exception e) {
			jr.setCode(102);
			jr.setMsg("Exception异常提示:"+e.getMessage());
			jr.setSuccess(false);
		}
		return  jr;
	}
	
}
