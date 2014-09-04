package com.mbm.librarymanagement.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mbm.librarymanagement.db.ConnectionFactory;
import com.mbm.librarymanagement.exception.ExceptionCategory;
import com.mbm.librarymanagement.exception.LibraryManagementException;
import com.mbm.librarymanagement.model.IssueBookVO;

public class IssueBookDAO {

	public boolean issueBook(IssueBookVO issueBookVO)
			throws LibraryManagementException {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection;
		try {
			connection = connectionFactory.getConnection();
		} catch (LibraryManagementException e) {
			throw e;
		}

		PreparedStatement preparedStatement = null;
		int checkInsertData = 0;
		try {
			if (issueBookVO != null) {
				preparedStatement = connection
						.prepareStatement("insert into ISSUE_BOOK"
								+ " (ID, STUDENT_ID, BOOK_ID, ISSUE_DATE)"
								+ " values(default, ?, ?, ?)");
				preparedStatement.setString(1, issueBookVO.getStudentID());
				preparedStatement.setString(2, issueBookVO.getBookID());
				preparedStatement.setDate(3, new Date(issueBookVO
						.getIssueDate().getTime()));
				checkInsertData = preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			throw new LibraryManagementException(ExceptionCategory.SYSTEM);
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					throw new LibraryManagementException(
							ExceptionCategory.SYSTEM);
				}
			}
			connectionFactory.closeConnection(connection);
		}

		if (checkInsertData == 0) {
			return false; // no insertion
		} else {
			return true;
		}
	}

	public boolean receiveBook(IssueBookVO issueBookVO)
			throws LibraryManagementException {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection;
		try {
			connection = connectionFactory.getConnection();
		} catch (LibraryManagementException e) {
			throw e;
		}

		PreparedStatement preparedStatement = null;
		int checkInsertData = 0;
		try {
			if (issueBookVO != null) {
				preparedStatement = connection
						.prepareStatement("update ISSUE_BOOK set RECEIVE_DATE = ?"
								+ " where STUDENT_ID = ? and BOOK_ID = ?;");
				preparedStatement.setDate(1, new Date(issueBookVO
						.getReceiveDate().getTime()));
				preparedStatement.setString(2, issueBookVO.getStudentID());
				preparedStatement.setString(3, issueBookVO.getBookID());
				checkInsertData = preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			throw new LibraryManagementException(ExceptionCategory.SYSTEM);
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					throw new LibraryManagementException(
							ExceptionCategory.SYSTEM);
				}
			}
			connectionFactory.closeConnection(connection);
		}

		if (checkInsertData == 0) {
			return false; // no update
		} else {
			return true;
		}
	}

	public boolean getStudentByUniqueId(IssueBookVO issueBookVO)
			throws LibraryManagementException {
		boolean returnValue = true;

		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection;
		try {
			connection = connectionFactory.getConnection();
		} catch (LibraryManagementException e) {
			throw e;
		}

		PreparedStatement preparedStatement = null;
		ResultSet resultSet;
		try {
			preparedStatement = connection
					.prepareStatement("select * from STUDENT where U_ID = ?");
			preparedStatement.setString(1, issueBookVO.getStudentID());
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				returnValue = false;
				throw new LibraryManagementException(
						ExceptionCategory.STUDENT_NOT_REGISTERED);
			}
		} catch (SQLException e) {
			throw new LibraryManagementException(ExceptionCategory.SYSTEM);
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					throw new LibraryManagementException(
							ExceptionCategory.SYSTEM);
				}
			}
			connectionFactory.closeConnection(connection);
		}
		return returnValue;
	}

	public boolean getBookByUniqueId(IssueBookVO issueBookVO)
			throws LibraryManagementException {
		boolean returnValue = true;

		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection;
		try {
			connection = connectionFactory.getConnection();
		} catch (LibraryManagementException e) {
			throw e;
		}

		PreparedStatement preparedStatement = null;
		ResultSet resultSet;
		try {
			preparedStatement = connection
					.prepareStatement("select * from BOOK where U_ID = ?");
			preparedStatement.setString(1, issueBookVO.getBookID());
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				returnValue = false;
				throw new LibraryManagementException(
						ExceptionCategory.BOOK_NOT_REGISTERED);
			}
		} catch (SQLException e) {
			throw new LibraryManagementException(ExceptionCategory.SYSTEM);
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					throw new LibraryManagementException(
							ExceptionCategory.SYSTEM);
				}
			}
			connectionFactory.closeConnection(connection);
		}
		return returnValue;
	}

	public boolean checkForBookIssuedOrNot(IssueBookVO issueBookVO)
			throws LibraryManagementException {
		boolean returnValue = true;

		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection;
		try {
			connection = connectionFactory.getConnection();
		} catch (LibraryManagementException e) {
			throw e;
		}

		PreparedStatement preparedStatement = null;
		ResultSet resultSet;
		try {

			preparedStatement = connection
					.prepareStatement("select * from ISSUE_BOOK where BOOK_ID = ?"
							+ " and RECEIVE_DATE is null");
			preparedStatement.setString(1, issueBookVO.getBookID());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				returnValue = false;
				throw new LibraryManagementException(
						ExceptionCategory.BOOK_ALREADY_ISSUED);
			}
		} catch (SQLException e) {
			throw new LibraryManagementException(ExceptionCategory.SYSTEM);
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					throw new LibraryManagementException(
							ExceptionCategory.SYSTEM);
				}
			}
			connectionFactory.closeConnection(connection);
		}
		return returnValue;
	}

	public boolean getStudentIdByBookId(IssueBookVO issueBookVO)
			throws LibraryManagementException {
		boolean returnValue = true;

		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection;
		try {
			connection = connectionFactory.getConnection();
		} catch (LibraryManagementException e) {
			throw e;
		}

		PreparedStatement preparedStatement = null;
		ResultSet resultSet;
		try {

			preparedStatement = connection
					.prepareStatement("select STUDENT_ID from ISSUE_BOOK where BOOK_ID = ?"
							+ " and RECEIVE_DATE is null");
			preparedStatement.setString(1, issueBookVO.getBookID());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String studentIdDB = resultSet.getString("STUDENT_ID");
				if (!studentIdDB.equals(issueBookVO.getStudentID())) {
					returnValue = false;
					throw new LibraryManagementException(
							ExceptionCategory.BOOK_IS_ISSUED_TO_OTHER_STUDENT);
				}
			}
		} catch (SQLException e) {
			throw new LibraryManagementException(ExceptionCategory.SYSTEM);
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					throw new LibraryManagementException(
							ExceptionCategory.SYSTEM);
				}
			}
			connectionFactory.closeConnection(connection);
		}
		return returnValue;
	}

	public boolean getReceiveDateByBookID(IssueBookVO issueBookVO)
			throws LibraryManagementException {
		boolean returnValue = true;

		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection;
		try {
			connection = connectionFactory.getConnection();
		} catch (LibraryManagementException e) {
			throw e;
		}

		PreparedStatement preparedStatement = null;
		ResultSet resultSet;
		try {
			preparedStatement = connection
					.prepareStatement("select * from ISSUE_BOOK"
							+ " where STUDENT_ID = ? and BOOK_ID = ? and RECEIVE_DATE is null");
			preparedStatement.setString(1, issueBookVO.getStudentID());
			preparedStatement.setString(2, issueBookVO.getBookID());
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
					returnValue = false;
					throw new LibraryManagementException(
							ExceptionCategory.BOOK_ALREADY_RECEIVED);
			}
		} catch (SQLException e) {
			throw new LibraryManagementException(ExceptionCategory.SYSTEM);
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					throw new LibraryManagementException(
							ExceptionCategory.SYSTEM);
				}
			}
			connectionFactory.closeConnection(connection);
		}
		return returnValue;
	}

}
