package com.gniot.crs.business;

/**
 * RegistrationOperation class implements the RegistrationInterface.
 * This class is responsible for operations related to student registration such as viewing grades and dropping courses.
 */
public class RegistrationOperation implements RegistrationInterface {
    
    /**
     * Views the grades of the student.
     */
    public void viewGrade() {
        System.out.println("<----View Grade---->");
    }

    /**
     * Drops the courses registered by the student.
     */
    public void dropCourses() {
        System.out.println("<----Drop Courses---->");
    }
}
