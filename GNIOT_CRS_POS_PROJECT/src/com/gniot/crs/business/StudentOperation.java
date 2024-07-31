/**
 * 
 */
package com.gniot.crs.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.gniot.crs.bean.*;
import com.gniot.crs.bean.Grade;
import com.gniot.crs.dao.StudentDAOImpl;
import com.gniot.crs.exception.*;

/**
 * 
 */
public class StudentOperation implements StudentInterface {
	private static Scanner scanner = new Scanner(System.in);
	private StudentDAOImpl studentDAO = new StudentDAOImpl();
	int studentId = studentDAO.currentStudentId();
	final String GREENBG = "\u001B[42m"; // ANSI escape code for green text
	final String REDBG = "\u001B[41m"; // ANSI escape code for red text
	final String RESET = "\u001B[0m";
	final String CYAN = "\u001B[96m";
	final String GREEN = "\u001B[32m"; // ANSI escape code for green text
	final String RED = "\u001B[31m"; // ANSI escape code for red text
	final String YELLOW = "\u001B[33m";
	String status = "Success";
	String paymentMethod;

	public StudentOperation() {
	}

	// browse Catalog
	public void browseCatalogForCoures() {
		try {
			List<Course> courses = studentDAO.browseCatalogForCourses();
			if (courses.isEmpty()) {
				System.out.println("No courses found in the catalog.");
			} else {
				System.out.println("Available Courses:");
				printHorizontalLine(113);
				System.out.printf("| %-8s | %-11s | %-25s | %-15s | %-25s| %-15s|\n", "CourseID", "Course Name",
						"Course Code", "Professor Id", "Professor Name", "Bill Amount");
				printHorizontalLine(113);
				for (Course course : courses) {

					System.out.printf("| %-8s | %-11s | %-25s | %-15s | %-25s| %-15s|\n", course.getCourse_id(),
							course.getCourseCode(), course.getCourseName(), course.getprofessorId(),
							course.getProfessorName(), course.getBillAmount());
					printHorizontalLine(113);
				}

			}
		} catch (CourseCatalogException e) {
			System.out.println(RED + e.getMessage() + RESET);
		}

	}
	// Add Course

	@Override
	public void addCourse() {
		// 1. Check if student is already enrolled in 4 courses
		try {
			List<Course> existingEnrollments = studentDAO.getEnrolledCourses(studentId);
			if (existingEnrollments.size() >= 4) {
				System.out.println(YELLOW + "You are already enrolled in the maximum of 4 courses." + RESET);
				return;
			}

			// 2. Display available courses
			List<Course> courses = studentDAO.browseCatalogForCourses();
			if (courses.isEmpty()) {
				System.out.println("No courses found in the catalog.");
				return;
			}

			System.out.println("Available Courses:");
			printHorizontalLine(110); // Adjust width as needed
			System.out.printf("| %-10s | %-25s | %-20s |%-25s|%-20s |\n", "Course ID", "Course Name", "Professor Id",
					"Professor Name", "Bill Amount");
			printHorizontalLine(110);
			for (Course course : courses) {
				System.out.printf("| %-10d | %-25s | %-20s |%-25s|%-20s |\n", course.getCourse_id(),
						course.getCourseName(), course.getprofessorId(), course.getProfessorName(),
						course.getBillAmount());
				printHorizontalLine(110);
			}

			// 3. Get course choices (primary and alternative)
			List<Integer> primaryChoices = getCourseChoices("primary", 4, courses); // Get 4 primary choices
			List<Integer> alternativeChoices = getCourseChoices("alternative", 2, courses); // Get 2 alternative choices

			// 4. Process enrollment choices
			enrollStudent(primaryChoices, alternativeChoices, courses);
		} catch (EnrollmentException e) {
			System.out.println(RED + e.getMessage() + RESET); // Display the specific error message
		}
	}

	// Helper method to get course choices from the user
	private List<Integer> getCourseChoices(String choiceType, int numChoices, List<Course> courses) {
		List<Integer> choices = new ArrayList<>();
		while (choices.size() < numChoices) {
			System.out.print(CYAN + "Enter " + choiceType + " course ID (or 0 to finish): " + RESET);
			int courseId = getValidCourseIdInput(courses);
			if (courseId == 0) {
				break;
			}
			choices.add(courseId);
		}
		return choices;
	}

	// Enroll Student in courses
	private void enrollStudent(List<Integer> primaryChoices, List<Integer> alternativeChoices, List<Course> courses) {
		for (int courseId : primaryChoices) {
			Course course = studentDAO.getCourseById(courseId);

			// Check course capacity and availability
			if (course.getCurrentEnrollment() >= course.getCapacity()) {
				System.out.println(REDBG + "Course " + courseId + " is full. Trying alternatives..." + RESET);

				// Try to enroll in an alternative course
				boolean enrolledInAlternative = false;
				for (int altCourseId : alternativeChoices) {
					if (altCourseId == courseId) {
						continue; // Skip if the alternative is the same as the primary choice
					}

					// Check if the student is already enrolled in the alternative course
					if (studentDAO.isStudentEnrolled(studentId, altCourseId)) {
						System.out.println(YELLOW + "Warning: You are already enrolled in alternative course "
								+ altCourseId + RESET);
						continue; // Skip to the next alternative if already enrolled
					}

					Course altCourse = studentDAO.getCourseById(altCourseId);
					if (altCourse.getCurrentEnrollment() < altCourse.getCapacity()) {
						studentDAO.addCourse(studentId, altCourseId);
						studentDAO.updateCourseEnrollment(altCourseId, altCourse.getCurrentEnrollment() + 1);
						enrolledInAlternative = true;
						System.out.println(GREEN + "Enrolled in alternative course " + altCourseId + RESET);
						break;
					}
				}

				if (!enrolledInAlternative) {
					System.out.println(REDBG + "No suitable alternative courses available for " + courseId + RESET);
				}
			} else {
				studentDAO.addCourse(studentId, courseId);
				studentDAO.updateCourseEnrollment(courseId, course.getCurrentEnrollment() + 1);
				System.out.println(GREEN + "Enrolled in course " + courseId + RESET);
			}
		}

		// After enrollments, send billing information if at least one course was added
		List<Course> enrolledCourses = studentDAO.getEnrolledCourses(studentId);
		if (!enrolledCourses.isEmpty()) {
			sendBillingInformation(enrolledCourses);
		}
	}

	// Remove Course

	@Override
	public void removeCourse() {
		// 1. Display enrolled courses
		List<Course> enrolledCourses = studentDAO.getEnrolledCourses(studentId);
		if (enrolledCourses.isEmpty()) {
			System.out.println("You are not enrolled in any courses.");
			return;
		}

		System.out.println("Enrolled Courses:");
		printHorizontalLine(90); // Adjust width as needed
		System.out.printf("| %-10s | %-25s | %-20s |%-25s |\n", "Course ID", "Course Name", "Professor Id",
				"Professor Name");
		printHorizontalLine(90);
		for (Course course : enrolledCourses) {
			System.out.printf("| %-10d | %-25s | %-20s |%-25s |\n", course.getCourse_id(), course.getCourseName(),
					course.getprofessorId(), course.getProfessorName());
			printHorizontalLine(90);
		}

		// 2. Get course ID input from user
		while (true) {
			System.out.print(CYAN + "Enter the course ID you want to remove (or '0' to finish): " + RESET);
			String courseIdStr = scanner.nextLine();

			if (courseIdStr.equals("0")) {
				break;
			}

			try {
				int courseId = Integer.parseInt(courseIdStr.trim());
				studentDAO.removeCourse(studentId, courseId); // Directly remove the course
				break; // Exit the loop after successful removal or error message
			} catch (NumberFormatException e) {
				System.out.println(REDBG + "Invalid course ID: " + courseIdStr + RESET);
			}
		}
	}

	// view grade

	@Override
	public void viewGrades() {
		try {
			List<Grade> grades = studentDAO.viewGrades(studentId);
			if (grades.isEmpty()) {
				System.out.println(YELLOW + "No grades found for this student." + RESET);
			} else {
				System.out.println("Your Grades:");
				printHorizontalLine(75);
				System.out.printf("| %-5s |%-10s| %-30s| %-10s| %-10s|\n", "ID", "Student ID", "Course Name",
						"Course Code", "Grade"); // Added Student ID
				printHorizontalLine(75); // Increased width for the added column

				for (Grade grade : grades) {
					System.out.printf("|%-6d |%-10d| %-30s| %-11s| %-10s|\n", grade.getGrade_id(), grade.getStudentId(),
							grade.getCourseName(), grade.getcourseCode(), grade.getGrade()); // Assuming your Grade
																								// class
																								// has appropriate
																								// getters
					printHorizontalLine(75);
				}

			}
		} catch (GradeRetrievalException e) {
			System.out.println(RED + e.getMessage() + RESET); // Display the error message
		}
	}

	// changes Password

	// view Schedule

	// Account Info

	@Override
	public void accountInfo() {
		try {
			StudentDetails details = studentDAO.accountInfo(studentId);
			if (details != null) {
				System.out.println("Your Account Information:");
				System.out.println(details);
			}
		} catch (StudentNotFoundException e) { // Handle student not found case
			System.out.println(RED + e.getMessage() + RESET);
		} catch (RuntimeException e) { // Handle other database errors
			System.err.println("An error occurred while fetching account information.");
			// Log the error: logger.error("Error in accountInfo operation", e);
		}
	}

	// Payment
	public void payment() {
		// 1. Calculate the current due amount:
		try {
			double totalBillAmount = studentDAO.calculateTotalBillAmount(studentId);
			try {
			double totalPaidAmount = studentDAO.getTotalPaidAmount(studentId); // Fetch the total amount paid so far
			double currentDueAmount = totalBillAmount - totalPaidAmount;

			System.out.println("Your current due amount is: ₹" + currentDueAmount);

			// 2. Check if there is any outstanding amount
			if (currentDueAmount <= 0) {
				System.out.println(YELLOW + "You have no outstanding payments." + RESET);
				return;
			}

			// 3. Display payment menu
			while (true) {
				System.out.println("\nPayment Options:");
				System.out.println("1. Process Payment");
				System.out.println("2. View Payment History");
				System.out.println("0. Go Back");

				System.out.print(CYAN + "Enter your choice: " + RESET);
				int choice;
				try {
					choice = Integer.parseInt(scanner.nextLine().trim());
				} catch (NumberFormatException e) {
					System.out.println(REDBG + "Invalid choice. Please enter a number." + RESET);
					continue;
				}

				if (choice == 1) {
					double amountToPay = promptAmountToPay(currentDueAmount); // Prompt for the amount to pay

					if (amountToPay <= 0) {
						System.out.println(REDBG + "Invalid amount. Please enter a positive value." + RESET);
						continue;
					}

					boolean paymentSuccess = processPayment(amountToPay); // Pass amountToPay to processPayment
					if (paymentSuccess) {
						totalBillAmount = studentDAO.calculateTotalBillAmount(studentId); // Recalculate after payment
						System.out.println(GREENBG + "Payment successfully done!" + RESET);
					}

					break; // Exit the loop even if payment is not successful.
				} else if (choice == 2) {
				    try {
				        displayPaymentHistory();
				    } catch (PaymentHistoryRetrievalException e) {
				        System.out.println(RED + e.getMessage() + RESET); 
				    } 
				} else if (choice == 0) {
					return;
				} else {
					System.out.println(REDBG + "Invalid choice. Please select a valid option." + RESET);
				}
			}
			}catch (TotalPaidAmountRetrievalException e) {
			    System.out.println(RED + e.getMessage() + RESET); 
			}
		} catch (BillCalculationException ex) {
			System.out.println(RED + ex.getMessage() + RESET);
		}
	}

	private double promptAmountToPay(double totalBillAmount) {
		while (true) {
			System.out.print(CYAN + "Enter the amount you want to pay (or 0 to cancel): ₹" + RESET);
			try {
				double amount = Double.parseDouble(scanner.nextLine().trim());
				if (amount <= totalBillAmount) {
					return amount;
				} else {
					System.out.println(REDBG + "Amount exceeds the total bill amount." + RESET);
				}
			} catch (NumberFormatException e) {
				System.out.println(REDBG + "Invalid input. Please enter a number." + RESET);
			}
		}
	}

	// New method to handle payment processing
	private boolean processPayment(double amount) {
		System.out.println("\nSelect Payment Method:");
		System.out.println("1. Credit Card");
		System.out.println("2. Debit Card");
		System.out.println("3. Net Banking");
		System.out.print(CYAN + "Enter your choice: " + RESET);

		try {
			int paymentMethodChoice = Integer.parseInt(scanner.nextLine().trim());
			switch (paymentMethodChoice) {
			case 1:
				paymentMethod = "Credit Card";
				break;
			case 2:
				paymentMethod = "Debit Card";
				break;
			case 3:
				paymentMethod = "Net Banking";
				break;
			default:
				System.out.println(REDBG + "Invalid payment method choice." + RESET);
				return false;
			}

			// Simulate payment processing (replace with actual payment logic)
			System.out.println("Processing payment...");

			// Simulate success (replace with actual payment logic)
			boolean paymentSuccessful = true; // Replace with your actual logic

			if (paymentSuccessful) {
				double newTotalBillAmount = studentDAO.calculateTotalBillAmount(studentId);
				double remainingDues = Math.max(0, newTotalBillAmount - amount);

				// Record the payment only if the payment is successful and there's a valid
				// payment method
				try {
					studentDAO.recordPayment(studentId, amount, paymentMethod, status, remainingDues);
					 try {
					        studentDAO.updateDueAmountsForStudent(studentId);
					    } catch (StudentDueUpdateException ex) {
					        System.out.println(RED + "Error updating due amounts: " + ex.getMessage() + RESET);
					        // ... (add more specific error handling or logging if needed) ...
					        return false; // Indicate payment failure
					    }
					return true;
				} catch (PaymentHistoryException e) {
					System.out.println(RED + e.getMessage() + RESET); 
					return false; // Indicate payment failure
				}
			} else {
				System.out.println(REDBG + "Payment failed: Unknown error" + RESET); // Display error
																						// if available
				status = "Failed";

				while (true) {
					System.out.print(CYAN + "Do you want to retry the payment? (y/n): " + RESET);
					String retryChoice = scanner.nextLine().trim();

					if (retryChoice.equalsIgnoreCase("y")) {
						return processPayment(amount); // Retry with the same amount
					} else if (retryChoice.equalsIgnoreCase("n")) {
						return false; // Cancel the payment
					} else {
						System.out.println(REDBG + "Invalid choice. Please enter 'y' or 'n'." + RESET);
					}
				}
			}
		} catch (NumberFormatException e) {
			System.out.println(REDBG + "Invalid input. Please enter a number." + RESET);
			return false;
		}
	}

	private void sendBillingInformation(List<Course> enrolledCourses) {
		double totalBillAmount = enrolledCourses.stream().mapToDouble(Course::getBillAmount).sum();

		System.out.println("\nBilling Information:");
		System.out.println("-------------------");
		for (Course course : enrolledCourses) {
			System.out.println("Course Name: " + course.getCourseName() + " - ₹" + course.getBillAmount());
		}
		System.out.println("-------------------");
		System.out.println("Total amount due: ₹" + totalBillAmount);

		// Simulated billing information sending (replace with actual integration)
		try {
			// ... Your code to interact with the billing system API or generate an invoice
			// ...
			// Example:
			// BillingSystemAPI.sendInvoice(studentId, enrolledCourses, totalBillAmount);

			System.out.println(GREENBG + "Billing information sent successfully." + RESET);
		} catch (Exception e) { // Catch any potential exceptions during billing
			System.out.println(REDBG + "Error sending billing information: " + e.getMessage() + RESET);
			// ... (Log the error for debugging) ...
		}
	}

	private void displayPaymentHistory() {
		List<Payment> paymentHistory = studentDAO.getPaymentHistory(studentId); // Fetch the history

		if (paymentHistory.isEmpty()) {
			System.out.println(YELLOW + "No payment history found." + RESET);
		} else {
			System.out.println("Payment History:");
			System.out.println("+-----------+------------+-----------------+--------------------+------------+");
			System.out.println("| Payment ID | Amount     | Payment Method  |      Date         | Due Amount  |");
			System.out.println("+-----------+------------+-----------------+--------------------+------------+");

			for (Payment payment : paymentHistory) {
				System.out.format("| %-10d | ₹%-10.2f | %-15s | %-18s | ₹%-10.2f |%n", payment.getPaymentId(),
						payment.getAmount(), payment.getPaymentMethod(), payment.getPaymentDate(),
						payment.getDueAmount());
			}
			System.out.format("+-----------+------------+-----------------+--------------------+------------+%n");
		}
	}

	private int getValidCourseIdInput(List<Course> courses) {
		while (true) {
			String courseIdStr = scanner.nextLine();
			if (courseIdStr.equals("0")) {
				return 0; // Exit if user enters 0
			}
			try {
				int courseId = Integer.parseInt(courseIdStr.trim());
				if (courses.stream().anyMatch(c -> c.getCourse_id() == courseId)) {
					return courseId; // Valid course ID found
				} else {
					System.out.println(REDBG + "Invalid course ID. Please enter a valid course ID." + RESET);
				}
			} catch (NumberFormatException e) {
				System.out.println(REDBG + "Invalid input. Please enter a number." + RESET);
			}
		}
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

}
