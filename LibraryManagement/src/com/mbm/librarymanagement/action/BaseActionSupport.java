package com.mbm.librarymanagement.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.mbm.librarymanagement.model.UserVO;
import com.opensymphony.xwork2.ActionSupport;

public class BaseActionSupport extends ActionSupport implements ServletRequestAware {
	private static final long serialVersionUID = 1L;
	
	public static String SUBMIT = "submit";
	
	private String submit;
	private UserVO userVO;
	
	private HttpServletRequest servletRequest;
	
	public String getSubmit() {
		return submit;
	}

	public void setSubmit(String submit) {
		this.submit = submit;
	}

	public HttpServletRequest getServletRequest() {
		return servletRequest;
	}

	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}

	public UserVO getUserVO() {
		return userVO;
	}

	public void setUserVO(UserVO userVO) {
		this.userVO = userVO;
	}

	public HttpSession getSession() {
		return servletRequest.getSession();
	}

}
