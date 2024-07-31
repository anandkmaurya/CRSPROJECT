/**
 * 
 */
package com.gniot.crs.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class Student {
	// Declare all the properties of Student

	private int studentId;
	private String studentName;
	private String studentEmail;
	private String studentPassword;
	private List<Integer> primaryCourseChoices = new ArrayList<>();
	private List<Integer> alternativeCourseChoices = new ArrayList<>();

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	public String getStudentPassword() {
		return studentPassword;
	}

	public void setStudentPassword(String studentPassword) {
		this.studentPassword = studentPassword;
	}

	public List<Integer> getPrimaryCourseChoices() {
		return primaryCourseChoices;
	}

	public void setPrimaryCourseChoices(List<Integer> primaryCourseChoices) {
		this.primaryCourseChoices = primaryCourseChoices;
	}

	public List<Integer> getAlternativeCourseChoices() {
		return alternativeCourseChoices;
	}

	public void setAlternativeCourseChoices(List<Integer> alternativeCourseChoices) {
		this.alternativeCourseChoices = alternativeCourseChoices;
	}
}
