package com.gniot.crs.bean;

import java.sql.Date;

public class Payment {
    private int paymentId;
    private int studentId;
    private double amount;
    private Date paymentDate;
    private String paymentMethod;
    private String status;
    private double dueAmount;
    private double initialDue; // Removed originalDueAmount

    // Constructor (updated to include initialDue)
    public Payment(int paymentId, int studentId, double amount, Date paymentDate, String paymentMethod, String status, double dueAmount, double initialDue) {
        this.paymentId = paymentId;
        this.studentId = studentId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.dueAmount = dueAmount;
        this.initialDue = initialDue; // Use initialDue for the original due amount
    }

    // Constructor for recordPayment method (sets default paymentId to 0)
    public Payment(int studentId, double amount, Date paymentDate, String paymentMethod, String status, double dueAmount) {
        this(0, studentId, amount, paymentDate, paymentMethod, status, dueAmount, 0); // Default paymentId to 0
    }

    // Getters
    public int getPaymentId() {
        return paymentId;
    }

    public int getStudentId() {
        return studentId;
    }

    public double getAmount() {
        return amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    // Setters (You may not need all of these depending on your application logic)
    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    public double getDueAmount() {
        return dueAmount;
    }
    public void setDueAmount(double dueAmount) {
        this.dueAmount = dueAmount;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getInitialDue() {
        return initialDue;
    }

    // Setters (including initialDue)
    // ... your existing setters ...

    public void setInitialDue(double initialDue) {
        this.initialDue = initialDue;
    }

    // toString method (updated)
    @Override
    public String toString() {
        return "Payment{" +
               "paymentId=" + paymentId +
               ", studentId=" + studentId +
               ", amount=" + amount +
               ", paymentDate=" + paymentDate +
               ", paymentMethod='" + paymentMethod + '\'' +
               ", status='" + status + '\'' +
               ", dueAmount=" + dueAmount +
               ", initialDue=" + initialDue + // Corrected to initialDue
               '}';
    }
}
