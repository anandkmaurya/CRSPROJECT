package com.gniot.crs.bean;

public class SemesterRegistration {
	private int studentId;
	private int semester;
	private int dateOfRegistration;
	
	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}
	public int getDateOfRegistration() {
		return dateOfRegistration;
	}

	public void setDateOfRegistration(int dateOfRegistration) {
		this.dateOfRegistration = dateOfRegistration;
	}
}
