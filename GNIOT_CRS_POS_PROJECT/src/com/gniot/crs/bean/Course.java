package com.gniot.crs.bean;

public class Course {
    private int courseId;
    private String courseCode;
    private String courseName;
    private String professorId;
    private String professorName;
    private int billAmount;  // Added to match the column name
    private int capacity = 10; // Default capacity
    private int currentEnrollment = 0; 

    // Constructor
    public Course(int courseId, String courseCode, String courseName, String professorId, String professorName, int billAmount, int capacity, int currentEnrollment) {
        this.courseId = courseId;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.professorId = professorId;
        this.setProfessorName(professorName);
        this.billAmount = billAmount;
        this.capacity = capacity; // Set the capacity
        this.currentEnrollment = currentEnrollment; // Set the current enrollment
    }



	// Getters
    public int getCourse_id() { // Changed to match the field name
        return courseId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getprofessorId() { // Changed to getProfessor for consistency
        return professorId;
    }

    public int getBillAmount() { // Changed to getBillAmount for consistency
        return billAmount;
    }

    // Setters (You may not need all of these if some fields are immutable)
    public void setCourse_id(int courseId) { // Changed to match the field name
        this.courseId = courseId;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setProfessorId(String professorId) { // Changed to setProfessor for consistency
        this.professorId = professorId;
    }

    public void setBillAmount(int billAmount) { // Changed to setBillAmount for consistency
        this.billAmount = billAmount;
    }

    @Override
    public String toString() {
        return String.format("Course ID: %d, Course Code: %s, Course Name: %s, Professor: %s, Bill Amount: ₹%d", courseId, courseCode, courseName, professorId, billAmount); // Added billAmount to the output
    }
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCurrentEnrollment() {
        return currentEnrollment;
    }

    public void setCurrentEnrollment(int currentEnrollment) {
        this.currentEnrollment = currentEnrollment;
    }

	public String getProfessorName() {
		return professorName;
	}

	public void setProfessorName(String professorName) {
		this.professorName = professorName;
	}
}
