package com.mbm.librarymanagement.action;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.mbm.librarymanagement.exception.LibraryManagementException;
import com.mbm.librarymanagement.facade.LibraryManagementFacade;
import com.mbm.librarymanagement.model.IssueBookVO;

public class IssueBookAction extends BaseActionSupport {
	private static final long serialVersionUID = 1L;

	private IssueBookVO issueBookVO;
	private String action;

	public IssueBookVO getIssueBookVO() {
		return issueBookVO;
	}

	public void setIssueBookVO(IssueBookVO issueBookVO) {
		this.issueBookVO = issueBookVO;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String execute() {
		String returnVal = SUCCESS;

		if (getSubmit() != null && SUBMIT.equals(getSubmit())) {
			LibraryManagementFacade facade = new LibraryManagementFacade();
			try {
				Calendar calendar = Calendar.getInstance();
				if ("issue".equals(getAction())) {
					Date issueDate = calendar.getTime();
					getIssueBookVO().setIssueDate(issueDate);
					facade.issueBook(getIssueBookVO());
					addActionMessage("Book " + issueBookVO.getBookID()
							+ " is issued to " + issueBookVO.getStudentID());
				} else if ("receive".equals(getAction())) {
					Date receiveDate = calendar.getTime();
					getIssueBookVO().setReceiveDate(receiveDate);
					facade.receiveBook(getIssueBookVO());
					addActionMessage("Book " + issueBookVO.getBookID()
							+ " is received by " + issueBookVO.getStudentID());
				}
			} catch (LibraryManagementException e) {
				addActionError(e.getExceptionCategory().getMessage());
				returnVal = ERROR;
			}
		} else {
			returnVal = "issuebook"; // when comes first time
		}
		return returnVal;
	}

	@Override
	public void validate() {
		super.validate();
		if (SUBMIT.equals(getSubmit())) {
			if (StringUtils.isBlank(issueBookVO.getStudentID())
					//|| !(StringUtils.isAlphanumeric(issueBookVO.getStudentID()))
			) {
				addFieldError("issueBookVO.studentID", "Enter valid student ID");
			}

			if (StringUtils.isBlank(issueBookVO.getBookID())
					|| !(StringUtils.isAlphanumeric(issueBookVO.getBookID()))) {
				addFieldError("issueBookVO.bookID", "Enter valid book ID");
			}

			if (StringUtils.isBlank(getAction())) {
				addFieldError("action", "Please select Option");
			}
		}
	}
}