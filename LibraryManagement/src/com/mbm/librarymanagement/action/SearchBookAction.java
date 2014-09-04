package com.mbm.librarymanagement.action;

import java.util.List;

import com.mbm.librarymanagement.exception.LibraryManagementException;
import com.mbm.librarymanagement.facade.LibraryManagementFacade;
import com.mbm.librarymanagement.model.BookVO;
import com.mbm.librarymanagement.model.UserVO;

public class SearchBookAction extends BaseActionSupport {

	private static final long serialVersionUID = 1L;
	private BookVO bookVO;
	private List<BookVO> bookVOs;
	private boolean editable = false;
	
	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public BookVO getBookVO() {
		return bookVO;
	}

	public void setBookVO(BookVO bookVO) {
		this.bookVO = bookVO;
	}

	public List<BookVO> getBookVOs() {
		return bookVOs;
	}

	public void setBookVOs(List<BookVO> bookVOs) {
		this.bookVOs = bookVOs;
	}


	public String execute() {
		String returnValue = SUCCESS;
		LibraryManagementFacade bookFacade = new LibraryManagementFacade();
		try {
			bookVOs = bookFacade.searchBooks(getBookVO());
			if("operator".equals(((UserVO)getSession().getAttribute("user")).getUserType())) {
				setEditable(true);
			}
		} catch (LibraryManagementException e) {
			returnValue = ERROR;
		}
		return returnValue;
	}
}
