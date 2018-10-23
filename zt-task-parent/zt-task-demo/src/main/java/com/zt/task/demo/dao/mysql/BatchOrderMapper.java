package com.zt.task.demo.dao.mysql;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
/**
* @ClassName: BatchOrderMapper 
* @Description:  mysql 数据库
* @author Alex-晓帅
* @date 2018年9月10日 下午12:24:43
 */
@Repository
public interface BatchOrderMapper {


	List<Map<String,Object>> getAllBatchOrderData()throws Exception;
	
	
}
