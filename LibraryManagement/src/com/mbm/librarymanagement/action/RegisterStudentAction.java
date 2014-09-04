package com.mbm.librarymanagement.action;

import org.apache.commons.lang.StringUtils;

import com.mbm.librarymanagement.exception.LibraryManagementException;
import com.mbm.librarymanagement.facade.LibraryManagementFacade;
import com.mbm.librarymanagement.model.StudentVO;

public class RegisterStudentAction extends BaseActionSupport {

	private static final long serialVersionUID = 1L;
	private StudentVO studentVO;
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

	public String execute() {
		String retValue = SUCCESS;
		if (getSubmit() != null && SUBMIT.equals(getSubmit())) {
			LibraryManagementFacade facade = new LibraryManagementFacade();
			try {
				StudentVO createdStudentVO = facade
						.registerStudent(getStudentVO());
				this.setId(createdStudentVO.getId());
			} catch (LibraryManagementException e) {
				addFieldError("studentVO.email",
						"* This Email-ID already exits ");
				retValue = ERROR;
			}
		} else {
			retValue = "create";
		}

		return retValue;
	}

	@Override
	public void validate() {
		super.validate();
		if (SUBMIT.equals(getSubmit())) {
			if ((StringUtils.isBlank(studentVO.getFirstName()))
					|| !(StringUtils.isAlpha(studentVO.getFirstName()))) {
				addFieldError("studentVO.firstName",
						"* Please enter your First Name");
			}
			if (StringUtils.isBlank(studentVO.getLastName())
					|| !StringUtils.isAlpha(studentVO.getLastName())) {
				addFieldError("studentVO.lastName",
						"* Please enter your Last Name");
			}
			if ((StringUtils.isBlank(studentVO.getGender()))
					|| !StringUtils.isAlpha(studentVO.getGender())) {
				addFieldError("studentVO.gender", "* Please enter your Gender");
			}
			if ((StringUtils.isBlank(studentVO.getFatherName()))
					|| !StringUtils.isAlphaSpace(studentVO.getFatherName())) {
				addFieldError("studentVO.fatherName",
						"* Please enter your Father Name");
			}

			/*
			  EmailValidator emailValidator = EmailValidator.getInstance(); if
			  (StringUtils.isBlank(studentVO.getEmail())) {
			  addFieldError("studentVO.email", "* Please enter your Email ID");
			  } else { boolean validators = emailValidator.isValid(studentVO
			  .getEmail()); if (!validators) addFieldError("studentVO.email",
			  "* Invalid Email"); }
			 
			*/
			if ((StringUtils.isBlank(studentVO.getMobile()))
					|| !StringUtils.isNumeric(studentVO.getMobile())) {
				addFieldError("studentVO.mobile", "* Please enter your mobile");
			}

			if ((StringUtils.isBlank(String.valueOf(studentVO.getAdmissionYear())))
					|| !StringUtils
							.isNumeric(String.valueOf(studentVO.getAdmissionYear()))) {
				addFieldError("studentVO.admissionYear",
						"* Please enter your current year");
			}

			if ((StringUtils.isBlank(studentVO.getPassword() + ""))) {
				addFieldError("studentVO.password", "* Please enter password");
			}
		}
	}

}
