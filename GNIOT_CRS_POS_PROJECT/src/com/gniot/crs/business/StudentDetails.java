package com.gniot.crs.business;

public class StudentDetails {
	String firstName;
	String lastName;
	String gender;
	int age;
	double tenthPercentage;
	double twelfthPercentage;
	String address;
	String phoneNumber;
	String emailId;

	// Constructor
	public StudentDetails(String firstName, String lastName, String gender, int age, double tenthPercentage,
			double twelfthPercentage, String address, String phoneNumber, String emailId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.age = age;
		this.tenthPercentage = tenthPercentage;
		this.twelfthPercentage = twelfthPercentage;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.emailId = emailId;
	}

	@Override
	public String toString() {
	    StringBuilder result = new StringBuilder(); // Use StringBuilder for efficiency

	    result.append(printHorizontalLine(60) + "\n"); // Add newline after each line

	    // Use String.format to build the table rows
	    result.append(String.format("| %-25s | %-25s |%n", "Key", "Values"));
	    result.append(printHorizontalLine(60) + "\n");
	    result.append(String.format("| %-25s | %-25s |%n", "First Name:", firstName));
	    result.append(String.format("| %-25s | %-25s |%n", "Last Name:", lastName));
	    result.append(String.format("| %-25s | %-25s |%n", "Gender:", gender));
	    result.append(String.format("| %-25s | %-25s |%n", "Age:", age));
	    result.append(String.format("| %-25s | %-25s |%n", "10th Percentage:", tenthPercentage));
	    result.append(String.format("| %-25s | %-25s |%n", "12th Percentage:", twelfthPercentage));
	    result.append(String.format("| %-25s | %-25s |%n", "Address:", address));
	    result.append(String.format("| %-25s | %-25s |%n", "Phone Number:", phoneNumber));
	    result.append(String.format("| %-25s | %-25s |%n", "Email:", emailId));

	    result.append(printHorizontalLine(60)); // Add the final line

	    return result.toString(); // Return the constructed string
	}

	// Getters (add for each field)
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getGender() {
		return gender;
	}

	public int getAge() {
		return age;
	}

	public double getTenthPercentage() {
		return tenthPercentage;
	}

	public double getTwelfthPercentage() {
		return twelfthPercentage;
	}

	public String getAddress() {
		return address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmailId() {
		return emailId;
	}
	private String printHorizontalLine(int length) {
	    return "-".repeat(length); // Use String.repeat for cleaner repetition
	}
}