/**
 * 
 */
package com.gniot.crs.business;

/**
 * 
 */

public interface AdminInterface {

	// Add course to catalog
	public void addCourseToCatalog();

	// Remove course to catalog
	public void removeCourseFromCatalog();

	// Assign Course to Professor
	public void assignCourseToProfessor();
	
	public void removeProfessorFromCourse();

	 public void setBillAmount();
	// Approve Student

	void approveStudent(String studentUsername);
	// display course catalog
	public void displayCourseCatalog();

	public void listPendingStudents();


	public void removeProfessor();

	void listPendingProfessor();

	public void approveProfessor();

}
