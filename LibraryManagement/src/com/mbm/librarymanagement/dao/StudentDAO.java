package com.mbm.librarymanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mbm.librarymanagement.db.ConnectionFactory;
import com.mbm.librarymanagement.exception.ExceptionCategory;
import com.mbm.librarymanagement.exception.LibraryManagementException;
import com.mbm.librarymanagement.model.StudentVO;
import com.mbm.librarymanagement.model.UserVO;

public class StudentDAO {

	public boolean registeredStundent(StudentVO studentVO)
			throws LibraryManagementException {

		PreparedStatement preparedStatement = null;
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.getConnection();

		String sqlquery = "insert into STUDENT (FIRST_NAME, LAST_NAME, "
				+ "FATHER_NAME, GENDER, EMAIL, MOBILE, STUDENT_NUMBER"
				+ ", ADMISSION_YEAR, ENROLL_NUMBER, U_ID, PASSWORD) values(?,?,?,?,?,?,?,?,?,?,?)";
		boolean created;
		int flag=0;
		try {
			preparedStatement = connection.prepareStatement(sqlquery);
			preparedStatement.setString(1, studentVO.getFirstName());
			preparedStatement.setString(2, studentVO.getLastName());
			preparedStatement.setString(3, studentVO.getFatherName());
			preparedStatement.setString(4, studentVO.getGender());
			preparedStatement.setString(5, studentVO.getEmail());
			preparedStatement.setString(6, studentVO.getMobile());
			preparedStatement.setString(7, studentVO.getStudentNumber());
			preparedStatement.setInt(8, studentVO.getAdmissionYear());
			preparedStatement.setString(9, studentVO.getEnrollNumber());
			preparedStatement.setString(10, studentVO.getuID());
			preparedStatement.setString(11, studentVO.getPassword());
			flag = preparedStatement.executeUpdate();
			if(flag == 0) {
				created = false;
			} else {
				created =true;
			}
		} catch (SQLException e) {
			throw new LibraryManagementException(ExceptionCategory.SYSTEM);
		}
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				throw new LibraryManagementException(ExceptionCategory.SYSTEM);
			}
		}

		try {
			connection.close();
		} catch (SQLException e) {
			throw new LibraryManagementException(ExceptionCategory.SYSTEM);
		}
		return created;
	}

	public boolean updatedStudent(StudentVO requestVO)
			throws LibraryManagementException {
		PreparedStatement preparedStatement = null;
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.getConnection();

		String sqlquery = "update STUDENT set FIRST_NAME = ?, LAST_NAME = ?, FATHER_NAME = ?,"
				+ " GENDER = ?, MOBILE = ?, STUDENT_NUMBER = ?, ADMISSION_YEAR = ?, ENROLL_NUMBER = ?, PASSWORD = ? where ID = ? ";
		boolean updated;
		try {
			preparedStatement = connection.prepareStatement(sqlquery);
			preparedStatement.setString(1, requestVO.getFirstName());
			preparedStatement.setString(2, requestVO.getLastName());
			preparedStatement.setString(3, requestVO.getFatherName());
			preparedStatement.setString(4, requestVO.getGender());
			preparedStatement.setString(5, requestVO.getMobile());
			preparedStatement.setString(6, requestVO.getStudentNumber());
			preparedStatement.setInt(7, requestVO.getAdmissionYear());
			preparedStatement.setString(8, requestVO.getEnrollNumber());
			preparedStatement.setString(9, requestVO.getPassword());
			preparedStatement.setInt(10, requestVO.getId());
			updated = preparedStatement.execute();
		} catch (SQLException e) {
			throw new LibraryManagementException(ExceptionCategory.SYSTEM);
		}
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				throw new LibraryManagementException(ExceptionCategory.SYSTEM);
			}
		}
		return updated;
	}

	public StudentVO searchStudentByIDOrEmail(StudentVO requestVO)
			throws LibraryManagementException {

		PreparedStatement preparedStatement = null;
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.getConnection();
		ResultSet resultSet = null;
		String sqlquery = "select * from STUDENT";
		StudentVO studentVO = null;
		boolean whereClause = false;

		if ((requestVO != null) && (requestVO.getId() != 0)) {
			sqlquery += " where ID = '" + requestVO.getId() + "'";
			whereClause = true;
		}
		if ((requestVO != null) && (requestVO.getEmail() != null)) {
			if (whereClause) {
				sqlquery += " and EMAIL = '" + requestVO.getEmail() + "'";
			} else {
				sqlquery += " where EMAIL = '" + requestVO.getEmail() + "'";
			}
		}
		
		try {

			preparedStatement = connection.prepareStatement(sqlquery);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				studentVO = new StudentVO();
				studentVO.setId(resultSet.getInt("ID"));
				studentVO.setFirstName(resultSet.getString("FIRST_NAME"));
				studentVO.setLastName(resultSet.getString("LAST_NAME"));
				studentVO.setFatherName(resultSet.getString("FATHER_NAME"));
				studentVO.setGender(resultSet.getString("GENDER"));
				studentVO.setEmail(resultSet.getString("EMAIL"));
				studentVO.setMobile(resultSet.getString("MOBILE"));
				studentVO.setStudentNumber(resultSet
						.getString("STUDENT_NUMBER"));
				studentVO.setAdmissionYear(resultSet.getInt("ADMISSION_YEAR"));
				studentVO.setEnrollNumber(resultSet.getString("ENROLL_NUMBER"));
				studentVO.setuID(resultSet.getString("U_ID"));
				studentVO.setPassword(resultSet.getString("PASSWORD"));
			}
		} catch (SQLException e) {
			throw new LibraryManagementException(ExceptionCategory.SYSTEM);
		}

		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				throw new LibraryManagementException(ExceptionCategory.SYSTEM);
			}
		}
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				throw new LibraryManagementException(ExceptionCategory.SYSTEM);
			}
		}

		return studentVO;
	}

	public List<StudentVO> searchStudent(StudentVO requestVO)
			throws LibraryManagementException {

		PreparedStatement preparedStatement = null;
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.getConnection();
		ResultSet resultSet = null;
		List<StudentVO> listOfStudents = new ArrayList<StudentVO>();
		boolean whereClause = false;
		String sqlquery = "select * from STUDENT";

		if ((requestVO != null) && (requestVO.getFirstName() != null)
				&& !("".equals(requestVO.getFirstName()))) {
			sqlquery += " where FIRST_NAME like '%" + requestVO.getFirstName()
					+ "%'";
			whereClause = true;
		}
		if ((requestVO != null) && (requestVO.getLastName() != null)
				&& !("".equals(requestVO.getLastName()))) {
			if (whereClause) {
				sqlquery += " and LAST_NAME like '%" + requestVO.getLastName()
						+ "%'";
			} else {
				sqlquery += " where LAST_NAME like '%"
						+ requestVO.getLastName() + "%'";
			}
		}
		if ((requestVO != null) && (requestVO.getEmail() != null)
				&& !("".equals(requestVO.getEmail()))) {
			if (whereClause) {
				sqlquery += " and EMAIL  like '%" + requestVO.getEmail() + "%'";
			} else {
				sqlquery += " where EMAIL  like '%" + requestVO.getEmail()
						+ "%'";
			}
		}

		try {
			preparedStatement = connection.prepareStatement(sqlquery);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				StudentVO studentVO = new StudentVO();
				studentVO.setFirstName(resultSet.getString("FIRST_NAME"));
				studentVO.setLastName(resultSet.getString("LAST_NAME"));
				studentVO.setEmail(resultSet.getString("EMAIL"));
				studentVO.setFatherName(resultSet.getString("FATHER_NAME"));
				studentVO.setGender(resultSet.getString("GENDER"));
				studentVO.setId(resultSet.getInt("ID"));
				studentVO.setMobile(resultSet.getString("MOBILE"));
				studentVO.setStudentNumber(resultSet
						.getString("STUDENT_NUMBER"));
				studentVO.setAdmissionYear(resultSet.getInt("ADMISSION_YEAR"));
				studentVO.setEnrollNumber(resultSet.getString("ENROLL_NUMBER"));
				studentVO.setuID(resultSet.getString("U_ID"));
				studentVO.setPassword(resultSet.getString("PASSWORD"));
				listOfStudents.add(studentVO);
			}
		} catch (SQLException e) {
			throw new LibraryManagementException(ExceptionCategory.SYSTEM);
		}

		return listOfStudents;
	}
	
	public UserVO getStudent(UserVO userVO)
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
						.prepareStatement("select * from STUDENT where EMAIL = ?");
				preparedStatement.setString(1, userVO.getUserId());
				resultSet = preparedStatement.executeQuery();
				if(resultSet.next()) {
					userVO = new  UserVO();
					userVO.setId(resultSet.getInt("ID"));
					userVO.setUserId(resultSet.getString("EMAIL"));
					userVO.setPassword(resultSet.getString("PASSWORD"));
					userVO.setUserType("student");
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
