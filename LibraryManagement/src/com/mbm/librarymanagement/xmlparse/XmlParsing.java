package com.mbm.librarymanagement.xmlparse;

import java.util.ArrayList;
import java.util.List;

import com.mbm.librarymanagement.exception.LibraryManagementException;
import com.mbm.librarymanagement.facade.LibraryManagementFacade;
import com.mbm.librarymanagement.model.StudentVO;

public class XmlParsing {

	public static void main(String args[]) {
		StaxParser staxParser = new StaxParser();
		List<StudentVO> studentList = new ArrayList<StudentVO>();
		StudentVO registeredVO=null;
		studentList = staxParser
				.readXmlFile("C:\\Users\\admin\\WebProgramming\\librarymanagement\\src\\com\\mbm\\librarymanagement\\xmlparse\\studentinfo.xml");
		LibraryManagementFacade libraryManagementFacade = new LibraryManagementFacade();
		
		for (StudentVO studentVO : studentList) {
			try {
				registeredVO = libraryManagementFacade.registerStudent(studentVO);
				if(registeredVO != null) {
					System.out.println("xml data added to database.");
				}
			} catch (LibraryManagementException e) {
				System.out.println(e.getExceptionCategory().getMessage());
				//e.printStackTrace();
			}
		}
	}
	

}
