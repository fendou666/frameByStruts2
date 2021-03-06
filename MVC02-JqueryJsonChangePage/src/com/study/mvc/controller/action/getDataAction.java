package com.study.mvc.controller.action;

import java.io.PrintWriter;
import java.util.List;

import net.sf.json.JSONArray;

import com.opensymphony.xwork2.ActionSupport;
import com.study.mvc.model.bean.StudentInfo;
import com.study.mvc.model.service.IStudentService;
import com.study.mvc.model.service.StudentServiceImp;
import com.study.mvc.tools.PageManager;

public class getDataAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private String page;
	private String findCondition;
	private String result="";
	
	public String getPage() {
		return page;
	}
	
	public void setPage(String page) {
		this.page = page;
	}

	public String getFindCondition() {
		return findCondition;
	}

	public void setFindCondition(String findCondition) {
		this.findCondition = findCondition;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}


	@Override
	public String execute() throws Exception {
		
		if(findCondition!=null){
			//------------确定页码需求
			if(page.equals("first")){
				PageManager.currentPageNo=1;
			}
			else if(page.equals("pre")){
				PageManager.currentPageNo=PageManager.getPrePageNo();
			}
			else if(page.equals("nxt")){
				PageManager.currentPageNo=PageManager.getNextPageNo();
			}
			else if(page.equals("last")){
				PageManager.currentPageNo=PageManager.getMaxPageNo();
			}
			else{
				PageManager.getMaxPageNo();
				PageManager.currentPageNo=Integer.parseInt(page);		
			}
			//-----------------------
			IStudentService setudentService=new StudentServiceImp();
			List<StudentInfo> studentAll = setudentService.getStudentsByName(findCondition);			
			if(studentAll!=null){
				JSONArray fromObject = JSONArray.fromObject(studentAll);
				result = fromObject.toString();
			}else{
				result = "[]";
			}
			
//			result += ("[");
//			//最大页码交给画面
//			System.out.println("PageManager.getMaxPageNo:"+PageManager.getMaxPageNo());
//			//request.getSession().setAttribute("maxp", PageManager.getMaxPageNo());
//			if(studentAll!=null){
//				if(studentAll.size()>0){
//					int i=0;
//					for(StudentInfo x: studentAll){
//						i++;
//						result += ("{");
//						result += ("\"id\":\""+x.getId()+"\",");
//						result += ("\"name\":\""+x.getName()+"\",");
//						result += ("\"sex\":\""+x.getSex()+"\",");
//						result += ("\"age\":\""+x.getAge()+"\",");
//						result += ("\"gradeFrom\":\""+x.getGradeFrom()+"\"");
//						if(i==studentAll.size()){
//							result += ("}");
//						}
//						else{
//							result += ("},");
//						}
//					}
//				}
//				else{
//					result += ("{");
//					result += ("\"id\":\"\",");
//					result += ("\"name\":\"\",");
//					result += ("\"sex\":\"\",");
//					result += ("\"age\":\"\",");
//					result += ("\"gradeFrom\":\"\"");
//					result += ("}");
//				}
//			}
//			else{
//				result += ("{");
//				result += ("\"id\":\"\",");
//				result += ("\"name\":\"\",");
//				result += ("\"sex\":\"\",");
//				result += ("\"age\":\"\",");
//				result += ("\"gradeFrom\":\"\"");
//				result += ("}");
//			}
//			result += ("]");
//		}
//		else{
//			result += ("[");
//			result += ("{");
//			result += ("\"id\":\"\",");
//			result += ("\"name\":\"\",");
//			result += ("\"sex\":\"\",");
//			result += ("\"age\":\"\",");
//			result += ("\"gradeFrom\":\"\"");
//			result += ("}");
//			result += ("]");
		}
		System.out.println("result 结果值为" + result);
		
		
		return SUCCESS;
	}

}
