package com.mbm.librarymanagement.xmlparse;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import com.mbm.librarymanagement.model.StudentVO;

public class StaxParser {

	static final String STUDENT = "student";
	static final String ID = "id";
	static final String FIRSTNAME = "firstName";
	static final String LASTNAME = "lastName";
	static final String FATHERNAME = "fatherName";
	// static final String DATEOFBIRTH = "dateOfBirth";
	static final String GENDER = "gender";
	static final String EMAIL = "email";
	static final String MOBILE = "mobile";
	static final String STUDENTNUMBER = "studentNumber";
	static final String ADMISSIONYEAR = "admissionYear";
	static final String ENROLLNUMBER = "enrollNumber";
	static final String UID = "uID";
	static final String PASSWORD = "password";

	public List<StudentVO> readXmlFile(String xmlFile) {
		List<StudentVO> studentList = new ArrayList<StudentVO>();

		try {
			// First, create a new XMLInputFactory
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();

			// Setup a new eventReader
			InputStream inputStream = new FileInputStream(xmlFile);

			XMLEventReader eventReader = inputFactory
					.createXMLEventReader(inputStream);

			// read the XML document
			StudentVO studentVO = null;

			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();

				if (event.isStartElement()) {
					StartElement startElement = event.asStartElement();

					// If we have an item element, we create a new item
					if (startElement.getName().getLocalPart() == (STUDENT)) {
						studentVO = new StudentVO();
						/*// We read the attributes from this tag and add the date
						// attribute to our object
						@SuppressWarnings("unchecked")
						Iterator<Attribute> attributes = startElement
								.getAttributes();
						while (attributes.hasNext()) {
							Attribute attribute = attributes.next();
							if (attribute.getName().toString().equals(ID)) {
								studentVO.setId(Integer.parseInt(attribute
										.getValue()));
							}
						}*/
					}

					if (event.isStartElement()) {
						if (event.asStartElement().getName().getLocalPart()
								.equals(FIRSTNAME)) {
							event = eventReader.nextEvent();
							studentVO.setFirstName(event.asCharacters()
									.getData());
							continue;
						}
					}

					if (event.isStartElement()) {
						if (event.asStartElement().getName().getLocalPart()
								.equals(LASTNAME)) {
							event = eventReader.nextEvent();
							studentVO.setLastName(event.asCharacters()
									.getData());
							continue;
						}
					}

					if (event.isStartElement()) {
						if (event.asStartElement().getName().getLocalPart()
								.equals(FATHERNAME)) {
							event = eventReader.nextEvent();
							studentVO.setFatherName(event.asCharacters()
									.getData());
							continue;
						}
					}
					/*
					 * if (event.isStartElement()) { if
					 * (event.asStartElement().getName().getLocalPart()
					 * .equals(DATEOFBIRTH)) { event = eventReader.nextEvent();
					 * studentVO.setDateOfBirth(new Date(event
					 * .asCharacters().getData().toString())); continue; } }
					 */

					if (event.isStartElement()) {
						if (event.asStartElement().getName().getLocalPart()
								.equals(GENDER)) {
							event = eventReader.nextEvent();
							studentVO.setGender(event.asCharacters().getData());
							continue;
						}
					}

					if (event.isStartElement()) {
						if (event.asStartElement().getName().getLocalPart()
								.equals(EMAIL)) {
							event = eventReader.nextEvent();
							studentVO.setEmail(event.asCharacters().getData());
							continue;
						}
					}

					if (event.isStartElement()) {
						if (event.asStartElement().getName().getLocalPart()
								.equals(MOBILE)) {
							event = eventReader.nextEvent();
							studentVO.setMobile(event.asCharacters().getData());
							continue;
						}
					}

					if (event.isStartElement()) {
						if (event.asStartElement().getName().getLocalPart()
								.equals(STUDENTNUMBER)) {
							event = eventReader.nextEvent();
							studentVO.setStudentNumber(event.asCharacters()
									.getData());
							continue;
						}
					}

					if (event.isStartElement()) {
						if (event.asStartElement().getName().getLocalPart()
								.equals(ADMISSIONYEAR)) {
							event = eventReader.nextEvent();
							studentVO.setAdmissionYear(Integer.parseInt(event
									.asCharacters().getData()));
							continue;
						}
					}

					if (event.isStartElement()) {
						if (event.asStartElement().getName().getLocalPart()
								.equals(ENROLLNUMBER)) {
							event = eventReader.nextEvent();
							studentVO.setEnrollNumber(event.asCharacters()
									.getData());
							continue;
						}
					}

					if (event.isStartElement()) {
						if (event.asStartElement().getName().getLocalPart()
								.equals(UID)) {
							event = eventReader.nextEvent();
							studentVO.setuID(event.asCharacters().getData());
							continue;
						}
					}

					if (event.isStartElement()) {
						if (event.asStartElement().getName().getLocalPart()
								.equals(PASSWORD)) {
							event = eventReader.nextEvent();
							studentVO.setPassword(event.asCharacters()
									.getData());
							continue;
						}
					}
				}

				// If we reach the end of an item element, we add it to the list
				if (event.isEndElement()) {
					EndElement endElement = event.asEndElement();
					if (endElement.getName().getLocalPart() == (STUDENT)) {
						studentList.add(studentVO);
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		return studentList;
	}

}
