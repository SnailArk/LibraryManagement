package com.mbm.librarymanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mbm.librarymanagement.db.ConnectionFactory;
import com.mbm.librarymanagement.exception.ExceptionCategory;
import com.mbm.librarymanagement.exception.LibraryManagementException;
import com.mbm.librarymanagement.model.BookVO;

public class BookDAO {

	public void addBook(BookVO bookVO) throws LibraryManagementException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ConnectionFactory connectionFactory = new ConnectionFactory();
		try {
			connection = connectionFactory.getConnection();
			preparedStatement = connection
					.prepareStatement("insert into BOOK(ID, U_ID, BOOK_NAME, PUBLISHER, AUTHOR, SUMMARY)"
							+ "values(default,?,?,?,?,?)");
			preparedStatement.setString(1, bookVO.getUniqueId());
			preparedStatement.setString(2, bookVO.getBookName());
			preparedStatement.setString(3, bookVO.getPublisher());
			preparedStatement.setString(4, bookVO.getAuthor());
			preparedStatement.setString(5, bookVO.getSummary());
			preparedStatement.execute();
		} catch (LibraryManagementException lme) {
			throw lme;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new LibraryManagementException(ExceptionCategory.SYSTEM);
		} finally {
			try {
				if (preparedStatement != null) {

					preparedStatement.close();
				}
			} catch (SQLException e) {
				throw new LibraryManagementException(ExceptionCategory.SYSTEM);
			}

			try {
				if (connection != null) {
					connectionFactory.closeConnection(connection);
				}
			} catch (LibraryManagementException lme) {
				throw lme;
			}
		}
	}

	public List<BookVO> searchBook(BookVO requestBookVO)
			throws LibraryManagementException {
		List<BookVO> searchedBooks = new ArrayList<BookVO>();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		ConnectionFactory connectionFactory = new ConnectionFactory();
		String query = "select * from BOOK";
		boolean flag = false;
		if (requestBookVO != null && (requestBookVO.getBookName() != null)
				&& !("".equals(requestBookVO.getBookName()))) {
			query += " where BOOK_NAME like '%" + requestBookVO.getBookName()
					+ "%'";
			flag = true;
		}
		if (requestBookVO != null && (requestBookVO.getPublisher() != null)
				&& !("".equals(requestBookVO.getPublisher()))) {
			if (flag == true) {
				query += " and PUBLISHER like '%"
						+ requestBookVO.getPublisher() + "%'";
			} else {

				query += " where PUBLISHER like '%"
						+ requestBookVO.getPublisher() + "%'";
				flag = true;
			}

		}
		if (requestBookVO != null && (requestBookVO.getAuthor() != null)
				&& !("".equals(requestBookVO.getAuthor()))) {
			if (flag == true) {

				query += " and AUTHOR like '%" + requestBookVO.getAuthor()
						+ "%'";
			} else {
				query += " where AUTHOR like '%" + requestBookVO.getAuthor()
						+ "%'";
			}
		}

		try {

			connection = connectionFactory.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				BookVO bookVO = new BookVO();
				bookVO.setId(resultSet.getInt("ID"));
				bookVO.setUniqueId(resultSet.getString("U_ID"));
				bookVO.setBookName(resultSet.getString("BOOK_NAME"));
				bookVO.setPublisher(resultSet.getString("PUBLISHER"));
				bookVO.setAuthor(resultSet.getString("AUTHOR"));
				bookVO.setSummary(resultSet.getString("SUMMARY"));
				searchedBooks.add(bookVO);
			}
		} catch (LibraryManagementException lme) {
			throw lme;
		} catch (SQLException e) {
			throw new LibraryManagementException(ExceptionCategory.SYSTEM);
		}

		try {
			if (statement != null) {
				statement.close();
			}
		} catch (SQLException e) {
			throw new LibraryManagementException(ExceptionCategory.SYSTEM);
		}

		try {
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException e) {
			throw new LibraryManagementException(ExceptionCategory.SYSTEM);
		}

		try {
			if (connection != null) {
				connectionFactory.closeConnection(connection);
			}
		} catch (LibraryManagementException lme) {
			throw lme;
		}

		return searchedBooks;
	}

	public BookVO getBookByUniqueId(BookVO requestbookVO)
			throws LibraryManagementException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ConnectionFactory connectionFactory = new ConnectionFactory();
		BookVO bookVO = null;
		String sqlQuery = "select * from BOOK";
		if (requestbookVO.getId() != 0) {
			sqlQuery += " where ID ='" + requestbookVO.getId() + "'";
		}

		Connection connection = null;
		try {
			connection = connectionFactory.getConnection();
			preparedStatement = connection.prepareStatement(sqlQuery);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				bookVO = new BookVO();
				bookVO.setId(resultSet.getInt("ID"));
				bookVO.setUniqueId(resultSet.getString("U_ID"));
				bookVO.setBookName(resultSet.getString("BOOK_NAME"));
				bookVO.setPublisher(resultSet.getString("PUBLISHER"));
				bookVO.setAuthor(resultSet.getString("AUTHOR"));
				bookVO.setSummary(resultSet.getString("SUMMARY"));
			}
		} catch (SQLException e) {
			throw new LibraryManagementException(ExceptionCategory.SYSTEM);
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					throw new LibraryManagementException(
							ExceptionCategory.SYSTEM);
				}
			}
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
		return bookVO;

	}

	public boolean updateBook(BookVO requestBookVO)
			throws LibraryManagementException {
		PreparedStatement preparedStatement = null;
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.getConnection();
		boolean created;
		try {
			preparedStatement = connection
					.prepareStatement("update BOOK set BOOK_NAME = ?, PUBLISHER = ?, AUTHOR = ?, SUMMARY = ? where ID = ?");
			preparedStatement.setString(1, requestBookVO.getBookName());
			preparedStatement.setString(2, requestBookVO.getPublisher());
			preparedStatement.setString(3, requestBookVO.getAuthor());
			preparedStatement.setString(4, requestBookVO.getSummary());
			preparedStatement.setInt(5, requestBookVO.getId());
			created = preparedStatement.execute();
		} catch (SQLException e) {
			throw new LibraryManagementException(ExceptionCategory.SYSTEM);
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				throw new LibraryManagementException(ExceptionCategory.SYSTEM);
			}

			connectionFactory.closeConnection(connection);

		}
		return created;
	}
}
