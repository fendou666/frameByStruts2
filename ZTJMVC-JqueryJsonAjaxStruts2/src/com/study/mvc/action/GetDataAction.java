package com.study.mvc.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.study.mvc.model.bean.StudentInfo;
import com.study.mvc.model.service.IStudentService;
import com.study.mvc.model.service.StudentServiceImp;
import com.study.mvc.tools.PageManager;

public class GetDataAction extends ActionSupport {

	private static final long serialVersionUID = 2930622159811385953L;
	private String page;
	private String stuname;
	private String result;
	private int maxpage;
	public int getMaxpage() {
		return maxpage;
	}

	public void setMaxpage(int maxpage) {
		this.maxpage = maxpage;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getStuname() {
		return stuname;
	}

	public void setStuname(String stuname) {
		this.stuname = stuname;
	}

	public String getResult() {
		return result;
	}
	
	public void setResult(String result) {
		this.result = result;
	}
	
	@Override
	public String execute() throws Exception {
		if(page!=null){
			
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
		}
		IStudentService stuService = new StudentServiceImp();
		List<StudentInfo> list = stuService.getStudentsByName(stuname);
		this.maxpage=PageManager.getMaxPageNo();
		System.out.println("maxpage:"+PageManager.getMaxPageNo());
		if(list!=null){
			JSONArray jsonArray = JSONArray.fromObject(list);
			System.out.println("list size:"+list.size());
			result = jsonArray.toString();
			System.out.println(result);
		}
		//ActionContext.getContext().getSession().put("maxpage", PageManager.getMaxPageNo());
		/*HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("maxpage", PageManager.getMaxPageNo());*/
		/*ActionContext.getContext().put("maxpage", PageManager.getMaxPageNo());*/
		return SUCCESS;
	}

}
