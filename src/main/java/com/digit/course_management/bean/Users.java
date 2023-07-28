package com.digit.course_management.bean;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Scanner;

import com.digit.course_management.CourseManagementApp;
import com.digit.course_management.Services.AdminServices;
import com.digit.course_management.Services.ProfessorService;
import com.digit.course_management.Services.StudentService;

public class Users {
	static String user_name;
	static String password;
	private static PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet resultSet;
	private ResultSetMetaData metadata;
	private static ResultSet res;

	static Scanner sc1 = new Scanner(System.in);

	public static boolean admlogin() throws Exception {

		Scanner sc = new Scanner(System.in);
		System.out.println("\033[1mWELCOME ADMIN\033[0m");
		System.out.println("Enter Admin User Name:");
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
			admlogin();
		}
		return true;

	}

	public void displayUser() {
		try {
			String sql = "select * from users";

			stmt = CourseManagementApp.con.createStatement();
			resultSet = stmt.executeQuery(sql);
			metadata = resultSet.getMetaData();
			int j= metadata.getColumnCount();
			
			for(int i=1;i<=j;i++) {
				System.out.print(metadata.getColumnName(i) + "\t");
			}
			System.out.println();
			while (resultSet.next() == true) {

				System.out.print(resultSet.getString(1) + "       \t");
				System.out.print(resultSet.getString(2) + "       \t");
				System.out.print(resultSet.getString(3) + "       \t");
				System.out.println();
			
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void removeuser() {
		try {
			String sql = "select * from users";

			stmt = CourseManagementApp.con.createStatement();
			resultSet = stmt.executeQuery(sql);
			while (resultSet.next() == true) {

				System.out.println(resultSet.getString("user_name"));

			}
			String sql1 = "delete from users where user_name=?";
			pstmt = CourseManagementApp.con.prepareStatement(sql1);

			System.out.println("Enter the name of the User name to remove");
			pstmt.setString(1, sc1.next());

			int x = pstmt.executeUpdate();
			if (x > 0) {
				System.out.println("User deleted");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static String getUser_name() {
		return user_name;
	}

	public static void setUser_name(String user_name) {
		Users.user_name = user_name;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		Users.password = password;
	}

}
