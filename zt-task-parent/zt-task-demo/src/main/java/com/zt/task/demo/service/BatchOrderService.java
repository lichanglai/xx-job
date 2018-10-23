package com.zt.task.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zt.task.demo.dao.mysql.BatchOrderMapper;

@Service("mysql_testservice")
@Transactional
public class BatchOrderService {

	@Autowired
	private BatchOrderMapper  batordermap;
	
	public    List<Map<String,Object>> getAllBatchOrderData() throws Exception{
		return batordermap.getAllBatchOrderData();
	}
	
}
