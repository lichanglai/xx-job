package com.zt.task.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zt.task.demo.dao.oracle.TestMapper;
/**
* @ClassName: TestService 
* @Description:   测试业务层
* @author Alex-晓帅
* @date 2018年9月10日 上午10:43:53
 */
@Service("oracle_testservice")
@Transactional
public class TestService {

	@Autowired
	private TestMapper  testmap;
	
	public    List<Map<String,Object>> getAllTestData() throws Exception{
		return testmap.getAllTestData();
	}
	
}
