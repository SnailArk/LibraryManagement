package com.mbm.librarymanagement.action;

import java.util.ArrayList;
import java.util.List;

import com.mbm.librarymanagement.exception.LibraryManagementException;
import com.mbm.librarymanagement.facade.LibraryManagementFacade;
import com.mbm.librarymanagement.model.StudentVO;



public class SearchStudentAction extends BaseActionSupport {

	private static final long serialVersionUID = 1L;
	private StudentVO studentVO;
	private List<StudentVO> studentList =null;
	
	public List<StudentVO> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<StudentVO> studentList) {
		this.studentList = studentList;
	}

	public StudentVO getStudentVO() {
		return studentVO;
	}

	public void setStudentVO(StudentVO studentVO) {
		this.studentVO = studentVO;
	}

	public String execute(){
		String retValue = SUCCESS;
		studentList = new ArrayList<StudentVO>();
		LibraryManagementFacade facade = new LibraryManagementFacade();
		if(getSubmit() != null && SUBMIT.equals(getSubmit())) {
			try {
				studentList = facade.showAllStudent(getStudentVO());
			} catch (LibraryManagementException e) {
				
			}
		} else {
			try {
				studentList = facade.showAllStudent(getStudentVO());
			} catch (LibraryManagementException e) {
			
			}
			retValue = "show";
		}
		return retValue;
	}
}
