package com.gniot.crs.client;

import com.gniot.crs.dao.UserDAOImpl;
import com.gniot.crs.dao.UserDAOInterface;
import com.gniot.crs.exception.CRSException;

import java.util.Scanner;

/**
 * Represents the CRS (Course Registration System) client application.
 */
public class CRSClientApplication {

	public static String errorMessage;
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		try {
			UserDAOInterface userDAO = new UserDAOImpl();

			// Main application loop

			while (true) {
				System.out.println("Welcome to the CRS Application");
				System.out.println("1. Login");
				System.out.println("2. Registration of the Student/ Professor/ Admin");
				System.out.println("3. Change password");
				System.out.println("4. Exit");

				System.out.print("Enter your choice: ");
				int choice = scanner.nextInt();
				scanner.nextLine();

				switch (choice) {
				case 1:
					userDAO.login();
					break;
				case 2:
					userDAO.register();
					break;
				case 3:
					userDAO.changePassword();
					break;
				case 4:
					System.out.println("Exiting...");
					System.exit(0);
				default:
					errorMessage = String.format("\u001B[31m%s\u001B[0m", "Invalid choice. Please try again.");
					System.out.println(errorMessage);
				}
			}
		} catch (CRSException ex) {
			System.err.println("Error: " + ex.getMessage());
		}
	}

}