/**
 * RegistrationInterface defines the methods for various registration-related operations.
 */
package com.gniot.crs.business;

/**
 * RegistrationInterface specifies the operations that can be performed related to student registration.
 * These operations include viewing grades and dropping courses.
 */
public interface RegistrationInterface {

    /**
     * Drops the courses registered by the student.
     */
    public void dropCourses();

    /**
     * Views the grades of the student.
     */
    public void viewGrade();
}
