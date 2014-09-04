package com.mbm.librarymanagement.action;

import com.mbm.librarymanagement.exception.LibraryManagementException;
import com.mbm.librarymanagement.facade.LibraryManagementFacade;
import com.mbm.librarymanagement.model.StudentVO;


public class UpdateStudentAction extends BaseActionSupport {
	private static final long serialVersionUID = 1L;
	private StudentVO studentVO ;
	private int id;
	
	public StudentVO getStudentVO() {
		return studentVO;
	}
	public void setStudentVO(StudentVO studentVO) {
		this.studentVO = studentVO;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String execute(){
		LibraryManagementFacade facade = new LibraryManagementFacade();
		String retValue = SUCCESS ;
		if(getSubmit() != null && SUBMIT.equals(getSubmit())) {
			try {
				setStudentVO(facade.updateStudent(getStudentVO())); 
				if(studentVO != null){
					this.setId(getStudentVO().getId());
					addActionError("Update successfully");
				}
			} catch (LibraryManagementException e) {
				retValue = ERROR;
			}
		} else {
			StudentVO requestVO = new StudentVO();
			requestVO.setId(getId());
			try {
				studentVO = facade.showUser(requestVO);
			} catch (LibraryManagementException e) {
				retValue = ERROR;
			}

		}
		
		return retValue;
	}


}
