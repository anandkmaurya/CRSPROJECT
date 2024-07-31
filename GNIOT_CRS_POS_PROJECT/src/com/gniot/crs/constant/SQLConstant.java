/**
 * 
 */
package com.gniot.crs.constant;

/**
 * 
 */
public class SQLConstant {
	
	
	//AdminDAOImpl
	public static final String INSERT_COURSES = "INSERT INTO course (course_id, course_name, course_code) VALUES (?, ?, ?)";
	public static final String REMOVE_COURSES = "DELETE FROM course WHERE course_id = ?";
	public static final String UPDATE_BILL= "UPDATE course SET bill_amount = ? WHERE course_id = ?";
	public static final String ASSIGN_COURSE = "UPDATE course SET  professor_id = ?,  professor_name = (SELECT CONCAT(first_name, ' ', last_name)FROM professors WHERE professor_id = ?)WHERE course_id = ?";
	public static final String FETCH_PROFESSOR = "SELECT * FROM professors";
	public static final String PROFESSOR_EXIST = "SELECT * FROM professors WHERE professor_id = ?";
	public static final String COURSE_EXIST = "SELECT * FROM course WHERE course_id = ?";
	public static final String APPROVE_PROFESSOR = "UPDATE users SET approved = 1 WHERE user_id = ? AND role = 'professor'";
	public static final String REMOVE_PROFESSOR_FROM_COURSE = "UPDATE course SET professor_id = NULL, professor_name = NULL WHERE course_id=?";
	public static final String REMOVE_PROFESSOR_FROM_PROFESSOR = "DELETE FROM professors WHERE professor_id = ?";
	public static final String REMOVE_PROFESSOR_FROM_USER="DELETE FROM users WHERE user_id = ?";
	public static final String APPROVE_STUDENT = "UPDATE users SET approved = 1 WHERE username = ?";
	public static final String GET_PENDING_STUDENTS = "SELECT user_id, username FROM users WHERE approved = 0 AND role = 'student'";
	public static final String GET_PENDING_PROFESSOR = "SELECT user_id, username FROM users WHERE approved = 0 AND role = 'professor'";
	public static final String GET_COURSE_CATALOG = "SELECT * FROM course";
	
	
	
	//-------------------------------------------//
	// ProfessorDAOImpl
	public static final String FETCH_ADD_GRADE = "SELECT * FROM grades WHERE student_id = ? AND course_id = ?";
	public static final String UPDATE_ADD_GRADE = "UPDATE grades SET grades = ?, created_at = ? WHERE student_id = ? AND course_id = ?";
	public static final String INSERT_ADD_GRADE = "INSERT INTO grades (student_id, grades, course_id, course_code, course_name, created_at) " +
            "SELECT ?, ?, c.course_id, c.course_code, c.course_name, ? " +
            "FROM course c WHERE c.course_id = ?";
	public static final String FETCH_PROFESSOR_COURSES = "SELECT cr.course_id, cr.course_name, cr.course_code, cr.professor_id, cr.bill_amount, cr.capacity, cr.currentEnrollment FROM professors AS pr JOIN course AS cr ON cr.professor_id = ?";
	public static final String FETCH_ENROLLED_STUDENTS = "SELECT s.*,e.* FROM students s JOIN enrollments e ON s.student_id = e.student_id WHERE e.course_id = ?";
	public static final String FETCH_ENROLLED_STUDENTS_PROFESSOR = " SELECT s.*, e.* FROM students s JOIN enrollments e ON s.student_id = e.student_id JOIN course c ON e.course_id = c.course_id  WHERE c.professor_id = ?";
	public static final String FETCH_PROFESSOR_COURSEID = "SELECT * FROM course WHERE professor_id = ?";
	public static final String FETCH_PROFESSOR_ID = "SELECT user_id FROM users WHERE username = ?";
	
	
	
	//--------------------------------------------------------------------------------//
	//StudentDAOImpl
	public static final String DISPLAY_COURSES = "SELECT * FROM course";
	public static final String INSERT_ENROLLED_COURSES = "INSERT INTO enrollments (student_id, course_id, enrollment_date) VALUES (?, ?, ?)";
	public static final String GET_COURSE_BY_ID = "SELECT * FROM course WHERE course_id = ?";
	public static final String UPDATE_COURSE_ENROLLMENT = "UPDATE course SET currentEnrollment = ? WHERE course_id = ?";
	public static final String DELETE_ENROLLED_COURSES = "DELETE FROM enrollments WHERE student_id = ? AND course_id = ?";
	public static final String VIEW_GRADE ="SELECT * FROM grades WHERE student_id = ?";
	public static final String FETCH_STUDENT_DETAILS = "SELECT * FROM students WHERE student_id = ?";
	public static final String CALCULATE_BILL = "SELECT SUM(c.bill_amount) AS total_amount "
			+ "FROM enrollments e JOIN course c ON e.course_id = c.course_id " + "WHERE e.student_id = ?";
	// SQL to insert payments
	public static final String RECORD_PAYMENT = """
		    INSERT INTO payment (student_id, amount, payment_date, payment_method, status, due_amount) 
		    VALUES (?, ?, ?, ?, ?, ?)
		    """;
	// SQL to fetch paymentid
	public static final String FETCH_PAYMENTID = "SELECT payment_id FROM payment WHERE student_id = ? ORDER BY payment_id DESC LIMIT 1";
	// SQL to update dues
	public static final String UPDATE_DUES = "UPDATE payment SET due_amount = ? WHERE payment_id = ?";

	public static final String UPDATE_DUES_STUDENT = """
			UPDATE payment
			SET due_amount = 
			    CASE
			        WHEN payment_id = (SELECT MAX(payment_id) FROM payment WHERE student_id = ?) THEN ?
			        ELSE due_amount - ?
			    END
			WHERE student_id = ? AND payment_id < (SELECT MAX(payment_id) FROM payment WHERE student_id = ?)
			""";
	
	public static final String PAYMENT_HISTORY = """
        SELECT *, (
            SELECT IFNULL(
                       SUM(c.bill_amount) - 
                       (SELECT IFNULL(SUM(amount), 0) 
                        FROM payment 
                        WHERE student_id = p.student_id 
                          AND payment_date < p.payment_date), 
                       SUM(c.bill_amount)
                   )
            FROM enrollments e
            JOIN course c ON e.course_id = c.course_id
            WHERE e.student_id = p.student_id
        ) AS initial_due
        FROM payment p
        WHERE student_id = ?
        ORDER BY payment_date ASC -- Order by DATE, not TIMESTAMP
        """;
	
	
	public static final String TOTAL_PAID_AMOUNT="SELECT SUM(amount) AS total_paid FROM payment WHERE student_id = ? AND status = 'Success'";


	public static final String FETCH_STUDENTID = "SELECT user_id FROM users WHERE username = ?";
	public static final String FETCH_STUDENT_ENROLLLED_COURSES = "SELECT c.* FROM course c JOIN enrollments e ON c.course_id = e.course_id WHERE e.student_id = ?";
	public static final String CHECK_STUDENT_ENROLLLED = "SELECT 1 FROM enrollments WHERE student_id = ? AND course_id = ?";
	public static final String COUNT_STUDENT_ENROLLLED = "SELECT COUNT(*) AS course_count FROM enrollments WHERE student_id = ?";
	public static final String STUDENT_EXISTS = "SELECT 1 FROM students WHERE student_id = ?";
	public static final String UPDATE_DUE_AMOUNTS_LATEST_PAYMENT = """
		    SELECT due_amount INTO @latest_due
		    FROM payment
		    WHERE student_id = ?
		    ORDER BY payment_id DESC
		    LIMIT 1;
		    """;

		public static final String UPDATE_DUE_AMOUNTS_PREVIOUS_PAYMENTS = """
		    UPDATE payment 
		    SET due_amount = @latest_due
		    WHERE student_id = ? AND payment_id < @latest_payment_id;
		    """;
	
	
	//---------------------------------------------------------------------------//
	//UserDAOImpl
	public static final String FETCH_LOGIN = "SELECT * FROM users WHERE username = ? AND password = ?";
	public static final String INSERT_LOGIN = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
	public static final String INSERT_STUDENT_DETAILS = "INSERT INTO students (student_id, first_name, last_name, gender, age, tenth_percentage, twelfth_percentage, address, phone_number, email_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String INSERT_PROFESSOR_DETAIL = "INSERT INTO professors (professor_id, first_name, last_name, gender, age, address, phone_number, email_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String FETCHCOURSE_NAME = "SELECT course_name FROM course WHERE course_id = ?";
	public static final String UPDATE_COURSE_SQL = "UPDATE course SET professor = ? WHERE course_id = ?";
	public static final String INSERT_PROFESSOR = "INSERT INTO professors (name, email, department, Designation, password, Doj, id) VALUES (?, ?, ?, ?, ?, ?, ?)";
	public static final String MATCH_PASSWORD = "SELECT * FROM users WHERE username = ? AND password = ?";
	public static final String UPDATE_NEW_PASSWORD = "UPDATE users SET password = ? WHERE username = ?";
	public static final String CHECK_APPROVED ="SELECT approved FROM users WHERE username = ?";

}
