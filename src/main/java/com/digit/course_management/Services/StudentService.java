package com.digit.course_management.Services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.digit.course_management.CourseManagementApp;

public class StudentService {
	static Scanner sc = new Scanner(System.in);
	private static Statement stmt;
	private static ResultSet resultSet;
	static String user_name;
	static String password;
	private static PreparedStatement pstmt;
	private static ResultSet res;
	public static boolean st1;
	public static ArrayList courseList = new ArrayList();
	private ResultSetMetaData metadata;

	private static PreparedStatement pstmt1;

//ProfessorService p5=new ProfessorService();
	public void createStu() {
		try {
			System.out.println("\033[1mWELCOME STUDENT\033[0m");
			System.out.println("Enter the number of Student:");
			int n = sc.nextInt();
			for (int i = 0; i < n; i++) {
				String sql = "insert into student values(?,?,?,?)";
				pstmt = CourseManagementApp.con.prepareStatement(sql);
				System.out.println("Enter the ID of the Student");
				pstmt.setString(1, sc.next());

				System.out.println("Enter the Name of the Student");
				pstmt.setString(2, sc.next());

				String sql1 = "select * from courses";

				stmt = CourseManagementApp.con.createStatement();
				resultSet = stmt.executeQuery(sql1);
				while (resultSet.next() == true) {
					courseList.add(resultSet.getString("c_name"));
					System.out.println(resultSet.getString("c_name"));
				}

				System.out.println("Enter the sub of the Student");
				String subs = sc.next();
				try {
					if (courseList.contains(subs)) {
						pstmt.setString(3, subs);

					} else {
						System.out.println("not");
					}
					pstmt.setString(4, "NotMarked");

				} catch (Exception e) {
					System.out.println("Enter Correct option");
				}

				int x = pstmt.executeUpdate();
				if (x > 0) {
					System.out.println("Student Added.");

				}
				Thread.sleep(1000);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void removeStu() {
		try {

			String sql = "select * from student";

			stmt = CourseManagementApp.con.createStatement();
			resultSet = stmt.executeQuery(sql);
			while (resultSet.next() == true) {

				System.out.println(resultSet.getString("stu_name"));
				System.out.println("-----");

			}
			String sql1 = "delete from student where stu_name=?";
			pstmt = CourseManagementApp.con.prepareStatement(sql1);

			System.out.println("Enter the name of the Student to delete");
			pstmt.setString(1, sc.next());

			int x = pstmt.executeUpdate();
			if (x > 0) {
				System.out.println("student deleted");
			}
			Thread.sleep(1000);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void displayStu() {
		try {
			Thread.sleep(1000);
			String sql = "select * from student";

			stmt = CourseManagementApp.con.createStatement();
			resultSet = stmt.executeQuery(sql);
			metadata = resultSet.getMetaData();

			int j = metadata.getColumnCount();

			for (int i = 1; i <= j; i++) {
				System.out.print("\033[1m"+metadata.getColumnName(i) + "    \t\033[0m");
			}
			System.out.println();
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

	public static void regiStu() {
		try {
			String sql = "insert into sturequest values(?,?,?)";
			pstmt = CourseManagementApp.con.prepareStatement(sql);
			System.out.println("Enter Student User Id");
			pstmt.setString(1, sc.next());

			System.out.println("Enter the Password");
			pstmt.setString(2, sc.next());

			pstmt.setString(3, "Student");

			int x = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			regiStu();
		}
	}

	public static boolean stulogin() throws Exception {
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter the Student user_name:");
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
			return true;

		} else {
			System.out.println("\u001B[31mLogin Failed...\u001B[0m");
			System.out.println("Login again");
			stulogin();
		}
		return true;

	}

	public static void getMark() {
		try {
			String sql = "select * from studentmark where stu_name=?";

			pstmt = CourseManagementApp.con.prepareStatement(sql);
			Scanner sc = new Scanner(System.in);

			System.out.println("Enter the Name to get result:");
			pstmt.setString(1, sc.nextLine());
			resultSet = pstmt.executeQuery();
			while (resultSet.next() == true) {

				System.out.println("Student ID is :\033[1m" + resultSet.getString("stu_id") + "\033[0m");
				System.out.println("Student Name is :\033[1m" + resultSet.getString("stu_name") + "\033[0m");
				System.out.println("Student Course is :\033[1m" + resultSet.getString("stu_cour") + "\033[0m");
				System.out.println("Student Full Mark  is :\033[1m" + resultSet.getString("stu_fmark") + "\033[0m");
				System.out.println("Student Mark is :\033[1m" + resultSet.getString("stu_mark") + "\033[0m");
				System.out.println("Student Grade is :\033[1m" + resultSet.getString("stu_grade") + "\033[0m");
				System.out.println("Student Remark is :\033[1m" + resultSet.getString("stu_remark") + "\033[0m");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void approveStu() {
		try {

			String sql = "select * from sturequest";

			stmt = CourseManagementApp.con.createStatement();
			resultSet = stmt.executeQuery(sql);
			while (resultSet.next() == true) {
				System.out.println("Student requests are Pending");
				System.out.println("Request from Student-  \033[1m" + resultSet.getString("user_name") + "\033[0m");
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

					String sql3 = "insert into student values(?,?,?,?)";
					pstmt1 = CourseManagementApp.con.prepareStatement(sql3);
//					System.out.println("Enter Professor User Id");
					pstmt1.setString(1, resultSet.getString("user_name"));

					pstmt1.setString(2, resultSet.getString("stu_name"));

					pstmt1.setString(3, resultSet.getString("stu_cou"));

					pstmt1.setString(4, "notmarked");

					int x = pstmt.executeUpdate();
					if (x > 0) {
						System.out.print("Adding . ");
						Thread.sleep(500);
						System.out.print(" . ");
						Thread.sleep(500);
						System.out.print(" . ");
						Thread.sleep(500);
						System.out.println();
					}
					int y = pstmt1.executeUpdate();
					if (y > 0) {
						System.out.println("added to Student");
					}
					String sql2 = "delete from sturequest where user_name=?";
					pstmt = CourseManagementApp.con.prepareStatement(sql2);

					pstmt.setString(1, resultSet.getString("user_name"));

					int x1 = pstmt.executeUpdate();
					st1 = true;
				} else if (op.equalsIgnoreCase("n")) {
					String sql2 = "delete from sturequest where user_name=?";
					pstmt = CourseManagementApp.con.prepareStatement(sql2);

					pstmt.setString(1, resultSet.getString("user_name"));

					int x = pstmt.executeUpdate();
					System.out.println("Not approve");
					System.out.println(st1);
					st1 = false;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
