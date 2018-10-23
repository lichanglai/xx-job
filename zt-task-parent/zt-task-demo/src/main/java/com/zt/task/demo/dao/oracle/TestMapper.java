package com.zt.task.demo.dao.oracle;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
* @ClassName: TestMapper 
* @Description:  获取测试数据表结构
* @author Alex-晓帅
* @date 2018年9月10日 上午10:36:01
 */
@Repository
public interface TestMapper {
	/**
	* @Title: getAllTestData 
	* @author Alex-xiaoshuai
	* @Description: 获取所有测试数据信息
	* @param @return
	* @param @throws Exception    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @date   2018年9月10日 上午10:40:11 
	* @throws
	 */
	List<Map<String,Object>> getAllTestData()throws  Exception;
	 
}
