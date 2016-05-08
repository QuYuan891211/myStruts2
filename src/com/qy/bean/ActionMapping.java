package com.qy.bean;

import java.util.Map;

//封装action
public class ActionMapping {
	//private String name;
	private String classname;
	private String name;
	private String method;
	private Map<String,Result> allResults;
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Map<String, Result> getAllResults() {
		return allResults;
	}
	public void setAllResults(Map<String, Result> allResults) {
		this.allResults = allResults;
	}
	//根据标记得到result对象
	public Result getResult(String resultName){
		if(resultName==null){
			throw new RuntimeException("传入参数有误");
		}
		return allResults.get(resultName);
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	}

