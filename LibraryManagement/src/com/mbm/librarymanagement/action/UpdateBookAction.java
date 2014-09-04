package com.mbm.librarymanagement.action;

import com.mbm.librarymanagement.exception.LibraryManagementException;
import com.mbm.librarymanagement.facade.LibraryManagementFacade;
import com.mbm.librarymanagement.model.BookVO;

public class UpdateBookAction extends BaseActionSupport {

	private static final long serialVersionUID = 1L;
	private  BookVO bookVO;
	private int id;
	public BookVO getBookVO() {
		return bookVO;
	}
	public void setBookVO(BookVO bookVO) {
		this.bookVO = bookVO;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String execute(){
		String retValue = SUCCESS;
		LibraryManagementFacade bookFacade = new LibraryManagementFacade();
		if(getSubmit() != null && SUBMIT.equals(getSubmit())) {
			try {
				bookVO = bookFacade.updateBook(getBookVO());
			} catch (LibraryManagementException e) {
				retValue = ERROR;
			}
		} else {
			bookVO = new BookVO();
			bookVO.setId(getId());
			
			try {
				setBookVO(bookFacade.showBook(bookVO)); 
			} catch (LibraryManagementException e) {
				retValue = ERROR;
			}
		}
		return retValue;
		
	}

}
