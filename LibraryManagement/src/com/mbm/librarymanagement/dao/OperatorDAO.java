package com.mbm.librarymanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mbm.librarymanagement.db.ConnectionFactory;
import com.mbm.librarymanagement.exception.ExceptionCategory;
import com.mbm.librarymanagement.exception.LibraryManagementException;
import com.mbm.librarymanagement.model.UserVO;

public class OperatorDAO {
	public UserVO getOperator(UserVO userVO)
			throws LibraryManagementException {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection;
		try {
			connection = connectionFactory.getConnection();
		} catch (LibraryManagementException e) {
			throw e;
		}
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		try {
			if (userVO != null) {
				preparedStatement = connection
						.prepareStatement("select * from OPERATOR where USER_ID = ?");
				preparedStatement.setString(1, userVO.getUserId());
				resultSet = preparedStatement.executeQuery();
				if(resultSet.next()) {
					userVO = new  UserVO();
					userVO.setId(resultSet.getInt("ID"));
					userVO.setUserId(resultSet.getString("USER_ID"));
					userVO.setPassword(resultSet.getString("PASSWORD"));
					userVO.setUserType("operator");
				} else {
					userVO = null;
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
		return userVO;
	}	
}
