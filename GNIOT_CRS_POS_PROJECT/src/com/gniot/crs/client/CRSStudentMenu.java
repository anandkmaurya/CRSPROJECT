/**
 * 
 */
package com.gniot.crs.client;

import java.util.Scanner;

import com.gniot.crs.business.StudentInterface;
import com.gniot.crs.business.StudentOperation;

/**
 * Represents the student menu in the course registration system.
 */
public class CRSStudentMenu {
	/**
     * Displays the student menu and handles user input.
     * 
     * @param studentId The ID of the student currently logged in.
     */
	private static Scanner scanner = new Scanner(System.in);
	public static void displayStudentMenu() { // Pass studentId
		StudentInterface studOps = new StudentOperation();
		while (true) {
			System.out.println("Student Menu");
			System.out.println("1. Browse Catalog for Courses");
			System.out.println("2. Add Course");
			System.out.println("3. Remove Course");
			System.out.println("4. View Grades");
			System.out.println("5. Account Info");
			System.out.println("6. Payment");
			System.out.println("7. Logout");

			System.out.print("Enter your choice: ");
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				studOps.browseCatalogForCoures();
				break;
			case 2:
				studOps.addCourse();
				break;
			case 3:
				studOps.removeCourse();
				break;
			case 4:
				studOps.viewGrades();
				break;
			case 5:
				studOps.accountInfo();
				break;
			case 6:
				studOps.payment();
				break;
			case 7:
				return;
			default:
				System.out.println("Invalid choice. Please try again.");

			}
		}
	}


}
