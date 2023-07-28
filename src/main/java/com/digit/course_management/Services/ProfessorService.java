package com.digit.course_management.Services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.digit.course_management.CourseManagementApp;
import com.digit.course_management.bean.Professor;
import com.digit.course_management.bean.Users;

public class ProfessorService {

	Professor p1 = new Professor();
	Users u1 = new Users();
	private ResultSetMetaData metadata;
	static StudentService s4 = new StudentService();
	private static  Statement stmt;
	private static ResultSet resultSet;
	public static Scanner sc = new Scanner(System.in);
	private static ResultSet resultSet1;
	static String user_name;
	static String password;
	public static boolean st;
	private static PreparedStatement pstmt;
	private static ResultSet res;
	public static ArrayList courseList = new ArrayList();
	private static PreparedStatement qstmt;
	private static int result1;
	private static PreparedStatement pstmt1;

	public void createPro() {
		try {
			System.out.println("\033[1mWELCOME PROFESSOR\033[0m");
			System.out.println("Enter the number of Professor:");
			int n = sc.nextInt();
			for (int i = 0; i < n; i++) {
				String sql = "insert into professor values(?,?,?,?)";
				pstmt = CourseManagementApp.con.prepareStatement(sql);
				System.out.println("Enter the ID of the Professor");
				pstmt.setString(1, sc.next());

				System.out.println("Enter the Name of the Professor");
				pstmt.setString(2, sc.next());

				System.out.println("Enter the Exp of the Professor");
				pstmt.setString(3, sc.next()+"years");
				String sql1 = "select * from courses";

				stmt = CourseManagementApp.con.createStatement();
				resultSet = stmt.executeQuery(sql1);
				while (resultSet.next() == true) {
					courseList.add(resultSet.getString("c_name"));
					System.out.println(resultSet.getString("c_name"));
				}
				System.out.println("Enter the sub of the Professor");
				String subs = sc.next();
				try {
					if (courseList.contains(subs)) {
						pstmt.setString(4, subs);

					} else {
						System.out.println("not");
					}
				} catch (Exception e) {
					System.out.println("Enter Correct option");
				}

				int x = pstmt.executeUpdate();
				System.out.println(courseList);
				if (x > 0) {
					System.out.println("Professor Added.");

				}
				Thread.sleep(1000);


			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void removePro() {
		try {

			String sql = "select * from professor";

			stmt = CourseManagementApp.con.createStatement();
			resultSet = stmt.executeQuery(sql);
			while (resultSet.next() == true) {

				System.out.println(resultSet.getString("pro_name"));

			}
			String sql1 = "delete from professor where pro_name=?";
			pstmt = CourseManagementApp.con.prepareStatement(sql1);

			System.out.println("Enter the name of the Professor name to remove");
			pstmt.setString(1, sc.next());

			int x = pstmt.executeUpdate();
			if (x > 0) {
				System.out.print("Removing the Professor  .");
				Thread.sleep(1000);
				System.out.print(" . ");
				Thread.sleep(1000);
				System.out.print(" . ");
				System.out.println();
				System.out.println("Professor deleted successfully");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void displayPro() {
		try {
			String sql = "select * from professor";

			stmt = CourseManagementApp.con.createStatement();
			resultSet = stmt.executeQuery(sql);
			metadata = resultSet.getMetaData();

			int j = metadata.getColumnCount();

			for (int i = 1; i <= j; i++) {
				System.out.print("\033[1m"+metadata.getColumnName(i) + "    \t\033[0m");
			}
			System.out.println("");
			while (resultSet.next() == true) {
				System.out.print(resultSet.getString(1) + "       \t");
				System.out.print(resultSet.getString(2) + "       \t");
				System.out.print(resultSet.getString(3) + "       \t");
				System.out.print(resultSet.getString(4) + "       \t");
				System.out.println();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static boolean prologin() throws Exception {
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter the Professor user_name:");
		user_name = sc.next();
		System.out.println("Enter the password:");
		password = sc.next();
		String sql = "select * from users where user_name=? and user_pass=?";
		pstmt = CourseManagementApp.con.prepareStatement(sql);
		pstmt.setString(1, user_name);
		pstmt.setString(2, password);
		res = pstmt.executeQuery();
		if (res.next()) {
			System.out.println("Login Successfully");
			Thread.sleep(1000);

			return true;

		} else {
			System.out.println("\u001B[31mLogin Failed...\u001B[0m");
			System.out.println("Login again");
			prologin();
		}
		return true;
	}

	public static void gradeStu() {
		try {
			String sql = "select * from student where status='notmarked'";

			stmt = CourseManagementApp.con.createStatement();
			resultSet = stmt.executeQuery(sql);
			
			while (resultSet.next() == true) {

				String sql1 = "insert into studentmark values(?,?,?,?,?,?,?,?)";
				pstmt = CourseManagementApp.con.prepareStatement(sql1);

				String sql2 = "update student set status=? where stu_id =?";
				qstmt = CourseManagementApp.con.prepareStatement(sql2);

				// Adding Student name in studentmatk from student
				System.out.println("Id is: " + resultSet.getString("stu_id"));
				pstmt.setString(1, resultSet.getString("stu_id"));

				System.out.println("Name is: " + resultSet.getString("stu_name"));
				// Adding Student name in studentmatk from student
				pstmt.setString(2, resultSet.getString("stu_name"));

				System.out.println("Course is: " + resultSet.getString("stu_cou"));
				pstmt.setString(3, resultSet.getString("stu_cou"));

//				// Fullmark
//				System.out.println("Full mark is 100");
//				pstmt.setString(4, "100");
//
//				System.out.println("Enter Mark :");
//				pstmt.setString(5, sc.next());

				System.out.println("Full mark is 100");
				pstmt.setInt(4, 100);

				System.out.println("Enter Mark :");
				int mark=sc.nextInt();
				pstmt.setInt(5, mark);
				String rem=grading(mark);
				pstmt.setString(6, rem);
				pstmt.setString(7,remarking(rem));

				pstmt.setString(8, "Marked");

				qstmt.setString(2, resultSet.getString("stu_id"));
				qstmt.setString(1, "Marked");

				int x = pstmt.executeUpdate();
				if(x>0) {
					System.out.println("Student marked successfully");
				}
//				result1 = pstmt.executeUpdate();
				qstmt.executeUpdate();
//				System.out.println(resultSet);
				Thread.sleep(1000);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void approve() {
		try {

			String sql = "select * from prorequest";

			stmt = CourseManagementApp.con.createStatement();
			resultSet = stmt.executeQuery(sql);
//			
			while (resultSet.next() == true) {
				System.out.println("Professor Requests are Pending");
				System.out.println("Request from Professor -  \033[1m" + resultSet.getString("user_name") + "\033[0m");
				System.out.println("Press Y to Accept\n" + "Press N to reject");
				String op = sc.next();
				if (op.equalsIgnoreCase("y")) {
					String sql1 = "insert into users values(?,?,?)";
					pstmt = CourseManagementApp.con.prepareStatement(sql1);
//					System.out.println("Enter Professor User Id");
					pstmt.setString(1, resultSet.getString("user_name"));

//					System.out.println("Enter the Password");
					pstmt.setString(2, resultSet.getString("user_pass"));

					pstmt.setString(3, resultSet.getString("role"));

					String sql3 = "insert into professor values(?,?,?,?)";
					pstmt1 = CourseManagementApp.con.prepareStatement(sql3);
//					System.out.println("Enter Professor User Id");
					pstmt1.setString(1, resultSet.getString("user_name"));

					pstmt1.setString(2, resultSet.getString("pro_name"));

					pstmt1.setString(3, resultSet.getString("pro_exp"));

					pstmt1.setString(4, resultSet.getString("pro_sub"));

					int x = pstmt.executeUpdate();
					if (x > 0) {
						System.out.println("added to user");
					}
					int y = pstmt1.executeUpdate();
					if (y > 0) {
						System.out.println("added to Professor");
					}

					String sql2 = "delete from prorequest where user_name=?";
					pstmt = CourseManagementApp.con.prepareStatement(sql2);

					pstmt.setString(1, resultSet.getString("user_name"));

					int x1 = pstmt.executeUpdate();
					st = true;
				} else if (op.equalsIgnoreCase("n")) {
					String sql2 = "delete from prorequest where user_name=?";
					pstmt = CourseManagementApp.con.prepareStatement(sql2);

					pstmt.setString(1, resultSet.getString("user_name"));

					int x = pstmt.executeUpdate();
					System.out.println("Not approve");
					st = false;
				}
				Thread.sleep(1000);


			}
//			}
//			else {
//				System.out.println("There is no Login Request .");
//			
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

//	void accept() {
//		try {
//			String sql = "insert into users values(?,?,?)";
//			pstmt = CourseManagementApp.con.prepareStatement(sql);
//			System.out.println("Enter Professor User Id");
//			pstmt.setString(1, sc.next());
//
//			System.out.println("Enter the Password");
//			pstmt.setString(2, sc.next());
//
//			pstmt.setString(3, "Professor");
//
//			int x = pstmt.executeUpdate();
//			if (x > 0) {
//				System.out.println("Added");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
	
	public static String grading(int b) {
		
		if(b>=90)
	         return "A+";
	    else if(b>=80 && b<90)
	         return "A";
	      else if(b>=70 && b<80)
	         return "B";
	      else if(b>=60 && b<70)
	         return "C";
	      else if(b>=50 && b<60)
	         return "D";
	      else if(b>=40 && b<50)
	         return "E";
	      else if(b>=30 && b<40)
		         return "E-";
		      
	      else
	         return "F";
		
	}

public static String remarking(String a) {
		
		if(a=="A+") {
			return "OutStanding";
		}
		else if(a=="A") {
			return "Excellent";
		}
		else if(a=="B") {
			return "Very Good";
		}
		else if(a=="C") {
			return "Good";
		}
		else if(a=="D") {
			return "Average";
		}
		else if(a=="E") {
			return "Below Average";
		}
		else if(a=="E-") {
			return "Bad";
		}
		else {
			return "Fail";
		}
		
	}
}
