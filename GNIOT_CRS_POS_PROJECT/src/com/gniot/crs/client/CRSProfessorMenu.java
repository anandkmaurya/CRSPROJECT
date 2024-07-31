package com.gniot.crs.client;

import java.util.Scanner;
import com.gniot.crs.business.ProfessorInterface;
import com.gniot.crs.business.ProfessorOperation;

/**
 * Class to display professor menu and handle interactions.
 */
public class CRSProfessorMenu {
    private static Scanner scanner = new Scanner(System.in);

    public static void displayProfessorMenu() {
        ProfessorInterface professorOps = new ProfessorOperation();
        while (true) {
            try {
                System.out.println("Professor Menu");
                System.out.println("1. Add Grade");
                System.out.println("2. View Enrolled Students");
                System.out.println("3. Logout");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                switch (choice) {
                    case 1:
                        professorOps.addGrade();
                        break;
                    case 2:
                         professorOps.viewEnrolledStudents();
                        break;
                    case 3:
                        System.out.println("Logging out...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
                e.printStackTrace();
                scanner.nextLine(); // Consume any remaining input to prevent loop issues
            }
        }
    }
}
