package com.gniot.crs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


import com.gniot.crs.bean.User;
import com.gniot.crs.business.StudentDetails;
import com.gniot.crs.client.CRSAdminMenu;
import com.gniot.crs.client.CRSProfessorMenu;
import com.gniot.crs.client.CRSStudentMenu;
import com.gniot.crs.constant.SQLConstant;
import com.gniot.crs.exception.*;
import com.gniot.crs.utils.DBUtils;

/**
 * Implementation of UserDAOInterface. This class provides methods to perform
 * user authentication, registration, and password management.
 */

public class UserDAOImpl implements UserDAOInterface {
	public static String errorMessage;
	public static List<User> users = new ArrayList<>();
	public static Scanner scanner = new Scanner(System.in);
	public static User currentUser;
	public static String loggedInUsername;

	public static Connection getConnection() throws SQLException {
		return DBUtils.getConnection();
	}

	/**
	 * Performs user login. This method prompts the user to enter their username and
	 * password, authenticates the user credentials against the database, and
	 * redirects the user to the appropriate menu based on their role (student,
	 * admin, professor).
	 */
	public void login() {
		System.out.print("User Name: ");
		String username = scanner.nextLine();
		System.out.print("Password: ");
		String password = scanner.nextLine();

		try (Connection conn = DBUtils.getConnection()) {
			String sql = SQLConstant.FETCH_LOGIN;
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, username);
				pstmt.setString(2, password);

				try (ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
						String role = rs.getString("role");
						boolean approved = rs.getBoolean("approved"); // Check approval status
						loggedInUsername = rs.getString("username");
						currentUser = new User(username, password, role);

						if (role.equalsIgnoreCase("student") && !approved) {
							errorMessage = String.format("\u001B[34m%s\u001B[0m",
									"Your registration is pending approval by the admin.");
							System.out.println(errorMessage);
							return;
						}else if (role.equalsIgnoreCase("professor")&& !approved) {
							errorMessage = String.format("\u001B[34m%s\u001B[0m",
							"Your registration is pending approval by the admin.");
						System.out.println(errorMessage);
						return;}
						switch (role.toLowerCase()) {
						case "student":
							CRSStudentMenu.displayStudentMenu();
							break;
						case "admin":
							CRSAdminMenu.displayAdminMenu(users);
							break;
						case "professor":
							CRSProfessorMenu.displayProfessorMenu();
							break;
						default:
							System.out.println("Invalid role!");
						}
					} else {
						errorMessage = String.format("\u001B[31m%s\u001B[0m", "Invalid username or password.");
						System.out.println(errorMessage);
					}
				}
			}
		} catch (SQLException ex) {
	        // Log the error
	        throw new LoginException("Error during login", ex); // Wrap SQLException
	    }
	}

	/**
	 * Registers a new user. This method allows a new user to register by providing
	 * a username, password, and role. If the user is a student, additional
	 * information such as name, gender, age, etc., is collected. After successful
	 * registration, the user's information is stored in the database. Students are
	 * required to wait for admin approval before they can log in.
	 */
	@Override
	public void register() {
		String username, password, confirmPassword, role;

		do {
			System.out.print("User Name: ");
			username = scanner.nextLine();

			System.out.print("Password: ");
			password = scanner.nextLine();

			System.out.print("Confirm Password: ");
			confirmPassword = scanner.nextLine();

			if (!password.equals(confirmPassword)) {
				errorMessage = String.format("\u001B[31m%s\u001B[0m", "Passwords do not match. Please try again.");
				System.out.println(errorMessage);
			}
		} while (!password.equals(confirmPassword));

		System.out.print("Role (student/professor): ");
		role = scanner.nextLine();

		if (findUserByUsername(username) == null) {
			StudentDetails studentDetails = null;
			try (Connection conn = DBUtils.getConnection()) {
				// Insert into User table
				String userInsertSql = SQLConstant.INSERT_LOGIN;
				try (PreparedStatement pstmt = conn.prepareStatement(userInsertSql, Statement.RETURN_GENERATED_KEYS)) {
					pstmt.setString(1, username);
					pstmt.setString(2, password);
					pstmt.setString(3, role);
					pstmt.executeUpdate();

					ResultSet generatedKeys = pstmt.getGeneratedKeys();
					int userId = -1;
					if (generatedKeys.next()) {
						userId = generatedKeys.getInt(1);
					} else {
						throw new SQLException("Creating user failed, no ID obtained.");
					}

					// Handle student or professor registration based on the role
					if (role.equalsIgnoreCase("student")) {
						System.out.print("First Name: ");
						String firstName = scanner.nextLine();
						System.out.print("Last Name: ");
						String lastName = scanner.nextLine();
						System.out.print("Gender: ");
						String gender = scanner.nextLine();
						System.out.print("Age: ");
						int age = scanner.nextInt();
						scanner.nextLine(); // Consume newline
						System.out.print("10th Percentage: ");
						double tenthPercentage = scanner.nextDouble();
						scanner.nextLine(); // Consume newline
						System.out.print("12th Percentage: ");
						double twelfthPercentage = scanner.nextDouble();
						scanner.nextLine(); // Consume newline
						System.out.print("Address: ");
						String address = scanner.nextLine();
						System.out.print("Phone Number: ");
						String phoneNumber = scanner.nextLine();
						System.out.print("Email ID: ");
						String emailId = scanner.nextLine();

						studentDetails = new StudentDetails(firstName, lastName, gender, age, tenthPercentage,
								twelfthPercentage, address, phoneNumber, emailId);

						// Insert into Students table
						String studentInsertSql = SQLConstant.INSERT_STUDENT_DETAILS;
						try (PreparedStatement studentPstmt = conn.prepareStatement(studentInsertSql)) {
							studentPstmt.setInt(1, userId);
							studentPstmt.setString(2, studentDetails.getFirstName());
							studentPstmt.setString(3, studentDetails.getLastName());
							studentPstmt.setString(4, studentDetails.getGender());
							studentPstmt.setInt(5, studentDetails.getAge());
							studentPstmt.setDouble(6, studentDetails.getTenthPercentage());
							studentPstmt.setDouble(7, studentDetails.getTwelfthPercentage());
							studentPstmt.setString(8, studentDetails.getAddress());
							studentPstmt.setString(9, studentDetails.getPhoneNumber());
							studentPstmt.setString(10, studentDetails.getEmailId());

							studentPstmt.executeUpdate();
						}
					} else if (role.equalsIgnoreCase("professor")) {
						System.out.print("First Name: ");
						String firstName = scanner.nextLine();
						System.out.print("Last Name: ");
						String lastName = scanner.nextLine();
						System.out.print("Gender: ");
						String gender = scanner.nextLine();
						System.out.print("Age: ");
						int age = scanner.nextInt();
						scanner.nextLine(); // Consume newline
						System.out.print("Address: ");
						String address = scanner.nextLine();
						System.out.print("Phone Number: ");
						String phoneNumber = scanner.nextLine();
						System.out.print("Email ID: ");
						String emailId = scanner.nextLine();

						// Insert into Professors table (assuming you have a 'professors' table)
																																																	// query
																																																	// to
																																																	// match
																																																	// your
																																																	// table
						try (PreparedStatement professorPstmt = conn.prepareStatement(SQLConstant.INSERT_PROFESSOR_DETAIL)) {
							professorPstmt.setInt(1, userId); // Assuming professor_id is the primary key
							professorPstmt.setString(2, firstName);
							professorPstmt.setString(3, lastName);
							professorPstmt.setString(4, gender);
							professorPstmt.setInt(5, age);
							professorPstmt.setString(6, address);
							professorPstmt.setString(7, phoneNumber);
							professorPstmt.setString(8, emailId);

							professorPstmt.executeUpdate();
						}
					} else {
						errorMessage = String.format("\u001B[31m%s\u001B[0m",
								"Invalid role. Please choose either 'student' or 'professor'.");
						System.out.println(errorMessage);
						return; // Exit if the role is invalid
					}

					errorMessage = String.format("\u001B[32m%s\u001B[0m",
							"Registration successful. Please wait for admin approval!");
					System.out.println(errorMessage);
				}
			} catch (SQLException ex) {
				errorMessage = String.format("\u001B[31m%s\u001B[0m", "Error during registration: " + ex.getMessage());
				System.out.println(errorMessage);
			}
		} else {
			errorMessage = String.format("\u001B[31m%s\u001B[0m",
					"User already exists. Please try a different username.");
			System.out.println(errorMessage);
		}
	}

	/**
	 * Changes the password of a user. This method allows a logged-in user to change
	 * their password. The user is prompted to enter their old password for
	 * verification and then provide a new password. The new password is updated in
	 * the database after successful authentication.
	 */
	public void changePassword() {
		String newPassword, newConfirmPassword;

		System.out.print("User Name: ");
		String username = scanner.nextLine();
		System.out.print("Old Password: ");
		String oldPassword = scanner.nextLine();

		try (Connection conn = DBUtils.getConnection()) {
			// Check if the username and old password match
			String checkSql = SQLConstant.MATCH_PASSWORD;
			try (PreparedStatement checkPstmt = conn.prepareStatement(checkSql)) {
				checkPstmt.setString(1, username);
				checkPstmt.setString(2, oldPassword);

				try (ResultSet rs = checkPstmt.executeQuery()) {
					if (!rs.next()) {
						errorMessage = String.format("\u001B[31m%s\u001B[0m", "Invalid username or old password.");
						System.out.println(errorMessage);
						return; // Exit the function if credentials are incorrect
					}
				}
			}

			// Get new passwords and ensure they match
			do {
				System.out.print("New Password: ");
				newPassword = scanner.nextLine();
				System.out.print("Confirm New Password: ");
				newConfirmPassword = scanner.nextLine();

				if (!newPassword.equals(newConfirmPassword)) {
					errorMessage = String.format("\u001B[31m%s\u001B[0m", "Passwords do not match. Please try again.");
					System.out.println(errorMessage);
				}
			} while (!newPassword.equals(newConfirmPassword));

			// Update the password in the database
			String updateSql = SQLConstant.UPDATE_NEW_PASSWORD;
			try (PreparedStatement updatePstmt = conn.prepareStatement(updateSql)) {
				updatePstmt.setString(1, newPassword);
				updatePstmt.setString(2, username);

				int rowsAffected = updatePstmt.executeUpdate();
				if (rowsAffected > 0) {
					errorMessage = String.format("\u001B[32m%s\u001B[0m", "Password changed successfully!");
					System.out.println(errorMessage);
				} else {
					// This shouldn't happen if the previous check passed, but handle it just in
					// case
					errorMessage = String.format("\u001B[31m%s\u001B[0m", "Failed to update password.");
					System.out.println(errorMessage);
				}
			}

		} catch (SQLException ex) {
			errorMessage = String.format("\u001B[31m%s\u001B[0m", "Error during password change: " + ex.getMessage());
			System.out.println(errorMessage);
		}
	}

	public boolean isApproved(String username) {
		try (Connection conn = DBUtils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(SQLConstant.CHECK_APPROVED)) {

			pstmt.setString(1, username);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return rs.getBoolean("approved"); // Returns true if the user is approved
				} else {
					return false; // User not found, so not approved by default
				}
			}
		} catch (SQLException ex) {
			// Log the error: logger.error("Error checking approval status: {}",
			// e.getMessage());
			throw new RuntimeException("Error checking approval status", ex);
		}
	}

	public User findUserByUsername(String username) {
		for (User user : users) {
			if (user.getUsername().equals(username)) {
				return user;
			}
		}
		return null;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public String getLoggedInUsername() { // getter method
		return loggedInUsername;
	}
}