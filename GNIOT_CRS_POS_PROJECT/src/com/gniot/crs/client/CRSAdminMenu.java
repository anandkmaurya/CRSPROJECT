
package com.gniot.crs.client;
import com.gniot.crs.business.AdminOperation;

import java.util.List;
import java.util.Scanner;

import com.gniot.crs.bean.User;
import com.gniot.crs.business.AdminInterface;
/**
 * Manages the menu for the CRS Admin and admin operations.
 */
public class CRSAdminMenu {

    private static Scanner scanner = new Scanner(System.in);

    public static void displayAdminMenu(List<User> users) {
        AdminInterface adminOps = new AdminOperation();
        while (true) {
            System.out.println("Admin Menu");
            System.out.println("1. Add course to catalog");
            System.out.println("2. Remove course from catalog");
            System.out.println("3. Set Bill Amount of Courses");
            System.out.println("4. Assign Course to Professor");
            System.out.println("5. Remove Professor From Course");
            System.out.println("6. Approve Professor");
            System.out.println("7. Remove Professor");
            System.out.println("8. Approve Student");
            System.out.println("9. View Catalog");
            System.out.println("0. Logout");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
            case 1:
                adminOps.addCourseToCatalog();
                break;
            case 2:
                adminOps.removeCourseFromCatalog();
                break;
            case 3:
                adminOps.setBillAmount();;
                break;
            case 4:
                adminOps.assignCourseToProfessor();
                break;
            case 5:
                adminOps.removeProfessorFromCourse();;
                break;
            case 6:
                adminOps.approveProfessor();
                break;
            case 7:
                adminOps.removeProfessor();
                break;
            case 8:
            	adminOps.listPendingStudents();
                System.out.print("Enter the username of the student to approve: ");
                String studentUsername = scanner.nextLine();
                adminOps.approveStudent(studentUsername);
                break;
  
            case 9:
            	adminOps.displayCourseCatalog();
            	break;
            case 0:
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
