package com.mbm.librarymanagement.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.mbm.librarymanagement.exception.LibraryManagementException;
import com.mbm.librarymanagement.facade.LibraryManagementFacade;
import com.mbm.librarymanagement.model.UserVO;

public class LoginAction extends BaseActionSupport {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	public String execute() {
		String returnVal = SUCCESS;
		if (getSubmit() != null && SUBMIT.equals(getSubmit())) {
			validate();
			LibraryManagementFacade libraryManagementFacade = new LibraryManagementFacade();
			try {
				UserVO validUserVO = libraryManagementFacade
						.authenticate(getUserVO());
				if (validUserVO != null) {
					Map<String, Object> sessionAttribute = new HashMap<String, Object>();
					getSession().setAttribute("user", validUserVO);
					if ("operator".equals(validUserVO.getUserType())) {
						returnVal = "operatorpanel";
					} else if ("student".equals(validUserVO.getUserType())) {
						returnVal = "studentpanel";
					}
				}
			} catch (LibraryManagementException e) {
				addActionError(e.getExceptionCategory().getMessage());
				returnVal = ERROR;
			}
		} else {
			returnVal = "login"; // when comes first time
		}
		return returnVal;
	}

	@Override
	public void validate() {
		super.validate();
		if (getSubmit() != null && SUBMIT.equals(getSubmit())) {
			if (StringUtils.isBlank(getUserVO().getUserId())) {
				addFieldError("userVO.userId", "Enter valid User ID");
			}

			if (StringUtils.isBlank(getUserVO().getPassword())) {
				addFieldError("userVO.password", "Enter valid Password");
			}

			if (StringUtils.isBlank(getUserVO().getUserType())) {
				addFieldError("userVO.userType", "Please select Option");
			}
		}
	}
}
