package com.gniot.crs.business;

import java.util.*;
import java.util.Scanner;
import com.gniot.crs.dao.ProfessorDAOInterface;
import com.gniot.crs.exception.*;
import com.gniot.crs.bean.Course;
import com.gniot.crs.dao.ProfessorDAOImpl;

/**
 * Implementation of ProfessorInterface to handle professor operations.
 */
public class ProfessorOperation implements ProfessorInterface {
	private Scanner scanner = new Scanner(System.in);
	private ProfessorDAOInterface professorDAO = new ProfessorDAOImpl();

	// ANSI escape codes for color formatting
	final String GREEN = "\u001B[32m"; // ANSI escape code for green text
	final String RED = "\u001B[31m"; // ANSI escape code for red text
	final String GREENBG = "\u001B[42m"; // ANSI escape code for green text
	final String REDBG = "\u001B[41m"; // ANSI escape code for red text
	final String CYAN = "\u001B[96m";
	final String YELLOW = "\u001B[33m";
	final String RESET = "\u001B[0m";

	public void addGrade() {
		int professorId = professorDAO.currentProfessorId(); // get the professor id of the logged-in professor
		if (professorId == -1) {
			System.out.println(RED + "Error: Professor not found." + RESET);
			return;
		}

		// 1. Get courses taught by the professor
		List<Course> courses = professorDAO.getProfessorCourses(professorId);
		if (courses.isEmpty()) {
			System.out.println(YELLOW + "You are not assigned to any courses." + RESET);
			return;
		}

		System.out.println("Courses You Teach:");
		printCoursesTable(courses); // Assuming you have this utility method

		// 2. Get course ID input from user
		System.out.print(CYAN + "Enter the course ID: " + RESET);
		int courseId;
		try {
			courseId = Integer.parseInt(scanner.nextLine().trim());
		} catch (NumberFormatException e) {
			System.out.println(REDBG + "Invalid course ID. Please enter a number." + RESET);
			return;
		}

		// 3. Display enrolled students for the chosen course
		List<StudentDetails> students = professorDAO.getEnrolledStudentsInCourse(courseId);
		if (students.isEmpty()) {
			System.out.println(YELLOW + "No students are enrolled in this course." + RESET);
			return;
		}

		System.out.println("Enrolled Students:");
		printStudentDetailsTable(students);

		// 4. Get student ID input from user
		System.out.print(CYAN + "Enter the student ID: " + RESET);
		int studentId;
		try {
			studentId = Integer.parseInt(scanner.nextLine().trim());
		} catch (NumberFormatException e) {
			System.out.println(REDBG + "Invalid student ID. Please enter a number." + RESET);
			return;
		}

		// 5. Get the grade and update the database
		System.out.print(CYAN + "Enter the grade(A to F): " + RESET);
		String grade = scanner.nextLine();
		try {
			professorDAO.addGrade(studentId, courseId, grade);
		} catch (GradeUpdateException | UnauthorizedGradeException e) { 
		    System.out.println(RED + e.getMessage() + RESET);
		    // ... (optionally, you can add more specific handling or logging here)
		}
	}

	public void viewEnrolledStudents() {
		try {
		int professorId = professorDAO.currentProfessorId(); // get the professor id of the logged-in professor
		if (professorId == -1) {
			System.out.println(RED + "Error: Professor not found." + RESET);
			return;
		}

		List<StudentDetails> students = professorDAO.getEnrolledStudents(professorId);
		if (students.isEmpty()) {
			System.out.println(YELLOW + "No students enrolled in your courses." + RESET);
		} else {
			System.out.println("Enrolled Students in Your Courses:");
			printStudentDetailsTable(students); // Assuming you have this utility method
		}
		}catch (EnrolledStudentsRetrievalException e) {
	        System.out.println(RED + e.getMessage() + RESET);
	    }
	}

	// Helper method to print the student details table
	private void printStudentDetailsTable(List<StudentDetails> students) {
		printHorizontalLine(130);
		System.out.printf("| %-5s | %-15s | %-15s | %-7s | %-4s | %-25s | %-15s | %-25s |%n", " ID   ", "  First Name",
				"  Last Name", "  Gender ", "  Age ", "Address", " Phone Number", " Email ID");
		printHorizontalLine(130);
		int index = 1; // Counter for student IDs in the table
		for (StudentDetails student : students) {
			System.out.printf("| %-6s | %-15s | %-15s | %-9s | %-6s | %-25s | %-15s | %-25s |%n", index,
					student.getFirstName(), student.getLastName(), student.getGender(), student.getAge(),
					student.getAddress(), student.getPhoneNumber(), student.getEmailId());
			index++;
		}
		printHorizontalLine(130);
	}

	private void printHorizontalLine(int... widths) {
		StringBuilder line = new StringBuilder("+");
		for (int width : widths) {
			for (int i = 0; i < width; i++) {
				line.append("-");
			}
			line.append("-+");
		}
		System.out.println(line);
	}

	private void printCoursesTable(List<Course> courses) {
		printHorizontalLine(50);
		System.out.printf("| %-10s | %-20s | %-10s |%n", " Course ID", "Course Name", " Course Code");
		printHorizontalLine(50);
		for (Course course : courses) {
			System.out.printf("| %-10s | %-20s | %-12s |%n", course.getCourse_id(), course.getCourseName(),
					course.getCourseCode());
		}
		printHorizontalLine(50);
	}
}