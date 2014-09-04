package com.mbm.librarymanagement.facade;

import java.util.Calendar;
import java.util.List;

import com.mbm.librarymanagement.dao.BookDAO;
import com.mbm.librarymanagement.dao.IssueBookDAO;
import com.mbm.librarymanagement.dao.OperatorDAO;
import com.mbm.librarymanagement.dao.StudentDAO;
import com.mbm.librarymanagement.exception.ExceptionCategory;
import com.mbm.librarymanagement.exception.LibraryManagementException;
import com.mbm.librarymanagement.model.BookVO;
import com.mbm.librarymanagement.model.IssueBookVO;
import com.mbm.librarymanagement.model.StudentVO;
import com.mbm.librarymanagement.model.UserVO;

public class LibraryManagementFacade {

	public StudentVO registerStudent(StudentVO requestVO)
			throws LibraryManagementException {
		StudentDAO studentDAO = new StudentDAO();
		StudentVO createdStudentVO = null;
		try {
			StudentVO dbStudentVO = studentDAO
					.searchStudentByIDOrEmail(requestVO);
			if (dbStudentVO == null) {
				requestVO.setuID(requestVO.getEmail()); // no uid therefore
														// email is our uid
				boolean created = studentDAO.registeredStundent(requestVO);
				if (created) {
					createdStudentVO = studentDAO
							.searchStudentByIDOrEmail(requestVO);
				}
			} else {
				throw new LibraryManagementException(
						ExceptionCategory.EMAIL_ALREADY_REGISTERED);
			}
		} catch (LibraryManagementException e) {
			throw e;
		}
		return createdStudentVO;
	}

	public StudentVO updateStudent(StudentVO requestVO)
			throws LibraryManagementException {
		StudentDAO studentDAO = new StudentDAO();
		boolean update;
		StudentVO updateStudentVO = null;
		try {
			update = studentDAO.updatedStudent(requestVO);
			if (!update) {
				updateStudentVO = studentDAO
						.searchStudentByIDOrEmail(requestVO);
			}
		} catch (LibraryManagementException e) {
			throw e;
		}
		return updateStudentVO;
	}

	public List<StudentVO> showAllStudent(StudentVO requestVO)
			throws LibraryManagementException {

		StudentDAO studentDAO = new StudentDAO();
		List<StudentVO> dbstudent = null;
		try {
			dbstudent = studentDAO.searchStudent(requestVO);
		} catch (LibraryManagementException e) {
			throw new LibraryManagementException(ExceptionCategory.SYSTEM);
		}
		return dbstudent;
	}

	public StudentVO showUser(StudentVO registeredUserVO)
			throws LibraryManagementException {

		StudentDAO studentDAO = new StudentDAO();
		StudentVO studentVO = null;
		try {
			studentVO = studentDAO.searchStudentByIDOrEmail(registeredUserVO);
		} catch (LibraryManagementException e) {
			throw new LibraryManagementException(ExceptionCategory.SYSTEM);
		}
		return studentVO;
	}

	public BookVO addBook(BookVO requestbookVO)
			throws LibraryManagementException {
		BookDAO bookDAO = new BookDAO();
		BookVO bookVO = null;
		if (requestbookVO.getUniqueId() == null) {
			requestbookVO.setUniqueId(Calendar.getInstance().getTimeInMillis()
					+ "");
		}
		try {

			bookDAO.addBook(requestbookVO);
			bookVO = bookDAO.getBookByUniqueId(requestbookVO);
		} catch (LibraryManagementException lme) {

			throw new LibraryManagementException(ExceptionCategory.SYSTEM);
		}

		return bookVO;
	}

	public List<BookVO> searchBooks(BookVO requestBookVO)
			throws LibraryManagementException {
		List<BookVO> searchedBooks = null;
		BookDAO bookDAO = new BookDAO();
		try {
			searchedBooks = bookDAO.searchBook(requestBookVO);
		} catch (LibraryManagementException e) {
			// TODO Auto-generated catch block
			throw e;
		}
		return searchedBooks;
	}

	public BookVO showBook(BookVO bookVO) throws LibraryManagementException {

		BookDAO bookDAO = new BookDAO();
		BookVO bookVO2 = null;
		try {
			bookVO2 = bookDAO.getBookByUniqueId(bookVO);
		} catch (LibraryManagementException e) {
			throw new LibraryManagementException(ExceptionCategory.SYSTEM);
		}
		return bookVO2;
	}

	public BookVO updateBook(BookVO requestBookVO)
			throws LibraryManagementException {
		BookVO updatedVO = null;
		BookDAO bookDAO = new BookDAO();
		try {
			bookDAO.updateBook(requestBookVO);
			updatedVO = bookDAO.getBookByUniqueId(requestBookVO);
		} catch (LibraryManagementException e) {
			throw e;
		}
		return updatedVO;
	}

	public boolean issueBook(IssueBookVO issueBookVO)
			throws LibraryManagementException {
		boolean returnValue = false;
		IssueBookDAO issueBookDAO = new IssueBookDAO();
		try {
			if (issueBookDAO.getStudentByUniqueId(issueBookVO)
					&& issueBookDAO.getBookByUniqueId(issueBookVO)) {
				if (issueBookDAO.checkForBookIssuedOrNot(issueBookVO)) {
					returnValue = issueBookDAO.issueBook(issueBookVO);
				}
			}
		} catch (LibraryManagementException e) {
			throw e;
		}
		return returnValue;
	}

	public boolean receiveBook(IssueBookVO issueBookVO)
			throws LibraryManagementException {
		boolean returnValue = false;
		IssueBookDAO issueBookDAO = new IssueBookDAO();
		try {
			if (issueBookDAO.getStudentByUniqueId(issueBookVO)
					&& issueBookDAO.getBookByUniqueId(issueBookVO)) {
				if (issueBookDAO.getStudentIdByBookId(issueBookVO)) { // book
																		// issued
																		// by
																		// other
																		// student.
					if (issueBookDAO.getReceiveDateByBookID(issueBookVO)) { // book
																			// already
																			// received.
						returnValue = issueBookDAO.receiveBook(issueBookVO);
					}
				}
			}
		} catch (LibraryManagementException e) {
			throw e;
		}
		return returnValue;
	}

	public UserVO authenticate(UserVO userVO) throws LibraryManagementException {
		UserVO dbUserVO = null;
		if ("operator".equals(userVO.getUserType())) { // logic for operator login
			OperatorDAO operatorDAO = new OperatorDAO();
			dbUserVO = operatorDAO.getOperator(userVO);
			if (dbUserVO == null
					|| !(dbUserVO.getPassword().equals(userVO.getPassword()))) {
				throw new LibraryManagementException(
						ExceptionCategory.PASSWORD_INCORRECT);
			}
			return dbUserVO;
		} else { // logic for student login
			StudentDAO studentDAO = new StudentDAO();
			dbUserVO = studentDAO.getStudent(userVO);
			if (dbUserVO == null
					|| !(dbUserVO.getPassword().equals(userVO.getPassword()))) {
				throw new LibraryManagementException(
						ExceptionCategory.PASSWORD_INCORRECT);
			}
			return dbUserVO;
		}
	}

}
