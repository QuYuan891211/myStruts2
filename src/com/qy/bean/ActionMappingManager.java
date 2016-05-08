package com.qy.bean;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ActionMappingManager {
/*管理类：
	1.加载配置文件
	2.封装所有*/

	
	private Map<String,ActionMapping> allAction;

	public ActionMappingManager(){
		allAction = new HashMap<String,ActionMapping>();
		this.init();
	}
	
	public Map<String, ActionMapping> getAllAction() {
		return allAction;
	}

	public void setAllAction(Map<String, ActionMapping> allAction) {
		this.allAction = allAction;
	}
	
	//根据请求名称，返回Action映射
	public ActionMapping getActionMapping(String actionName){
		if (actionName == null) {
			throw new RuntimeException("传入参数有误");
		}
		
		ActionMapping actionMapping = allAction.get(actionName);
		if (actionMapping == null) {
			throw new RuntimeException("对象为空");
		}
		return actionMapping;
	}
//	初始化allAction集合
	
	private void init(){
		//DOM4j读取配置文件
		//1.得到解析器
		try {
			SAXReader reader = new SAXReader();
			InputStream inputStream = this.getClass().getResourceAsStream("/myStruts.xml");
			Document doc = reader.read(inputStream);
			//2.得到跟节点
			Element root = doc.getRootElement();
			//3.得到package节点
			Element ele = root.element("package");
			//4.得到所有action节点
			List<Element> eles = ele.elements("action");
			for(Element ele_action: eles){
				ActionMapping am = new ActionMapping();
				//5.封装
				am.setName(ele_action.attributeValue("name"));
				am.setClassname(ele_action.attributeValue("class"));
				am.setMethod(ele_action.attributeValue("method"));
			
				//6.封装当前action的所有的结果
				
				Map<String,Result> results = new HashMap<String,Result>();
				Iterator<Element> it = ele_action.elementIterator("result");
				while(it.hasNext()){
					Result r = new Result();
					Element result = it.next();
					r.setName(result.attributeValue("name"));
					r.setType(result.attributeValue("type"));
					r.setPage(result.getTextTrim());
					results.put(r.getName(), r);
				}
				
				am.setAllResults(results);
			
				allAction.put(am.getName(), am);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("初始化失败",e);
		} 
	}
	
}
