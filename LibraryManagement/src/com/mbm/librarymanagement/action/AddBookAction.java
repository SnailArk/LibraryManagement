package com.mbm.librarymanagement.action;

import org.apache.commons.lang.StringUtils;

import com.mbm.librarymanagement.exception.LibraryManagementException;
import com.mbm.librarymanagement.facade.LibraryManagementFacade;
import com.mbm.librarymanagement.model.BookVO;
import com.mbm.librarymanagement.model.UserVO;

public class AddBookAction extends BaseActionSupport {

	private static final long serialVersionUID = 1L;
	private BookVO bookVO;
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String execute() {
		String returnValue = SUCCESS;
		if("operator".equals(((UserVO)getSession().getAttribute("user")).getUserType())) {
			if (getSubmit() != null && SUBMIT.equals(getSubmit())) {
				LibraryManagementFacade bookFacade = new LibraryManagementFacade();
				try {
					bookFacade.addBook(getBookVO());
					this.setId(bookVO.getId());
					//receive bookVO and set id
					returnValue = "update";
				} catch (LibraryManagementException lme) {
					returnValue = ERROR;
				}
			} 
		} else {
			returnValue = "invalidAccess";
		}
		return returnValue;
	}

	@Override
	public void validate() {
		super.validate();
		if (SUBMIT.equals(getSubmit())) {
			if (StringUtils.isBlank(bookVO.getBookName())
					|| !(StringUtils.isAlphaSpace(bookVO.getBookName()))) {
				addFieldError("bookVO.bookName", "Please enter valid book name");
			}
			if (StringUtils.isBlank(bookVO.getPublisher())
					|| !(StringUtils.isAlphaSpace(bookVO.getPublisher()))) {
				addFieldError("bookVO.publisher", "Please enter valid publisher name");
			}
			if (StringUtils.isBlank(bookVO.getAuthor())
					|| !(StringUtils.isAlphaSpace(bookVO.getAuthor()))) {
				addFieldError("bookVO.author", "Please enter valid author name");
			}

		}
	}

	public BookVO getBookVO() {
		return bookVO;
	}

	public void setBookVO(BookVO bookVO) {
		this.bookVO = bookVO;
	}

}
