package com.gniot.crs.bean; // Assuming your beans are in this package

public class Professor {
    private int professorId;
    private int userId;  // To link with the users table (if needed)
    private String firstName;
    private String lastName;
    private String gender;
    private int age;
    private String address;
    private String phoneNumber;
    private String emailId;
    // You can add a constructor, getters, and setters here

    // Constructor
    public Professor(int professorId, String firstName, String lastName, String gender, int age, String address, String phoneNumber, String emailId) {
        this.professorId = professorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.emailId = emailId;
    }

    // Getters
    public int getProfessorId() {
        return professorId;
    }

    public int getUserId() {
        return userId;
    }

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

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    // Setters (You might not need all of these if some fields are immutable)
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    // (Optional) toString for convenient printing
    @Override
    public String toString() {
        return "Professor{" +
               "professorId=" + professorId +
               ", userId=" + userId +
               ", firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", gender='" + gender + '\'' +
               ", age=" + age +
               ", address='" + address + '\'' +
               ", phoneNumber='" + phoneNumber + '\'' +
               ", emailId='" + emailId + '\'' +
               '}';
    }
}
