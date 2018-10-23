package com.zt.task.demo.util;

public class JsonResult {

	private Integer  code;
	
	private String  msg;
	
	private Object  obj;

	private boolean  success;
	
	public JsonResult(){
		this.code=1000;
		this.msg="数据初始化错误";
		this.success=false;
	}

	public boolean isSuccess() {
		return success;
	}



	public void setSuccess(boolean success) {
		this.success = success;
	}



	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	
}
