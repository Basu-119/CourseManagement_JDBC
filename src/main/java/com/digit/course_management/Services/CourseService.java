package com.digit.course_management.Services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.digit.course_management.CourseManagementApp;

public class CourseService {

	private PreparedStatement pstmt;
	Scanner sc = new Scanner(System.in);
	private Statement stmt;
	private ResultSet resultSet;

	public void createCou() {
		try {
			System.out.println("Enter the number of courses:");
			int n = sc.nextInt();
			for (int i = 0; i < n; i++) {
				String sql = "insert into courses values(?,?,?)";
				pstmt = CourseManagementApp.con.prepareStatement(sql);

				System.out.println("Enter the Id of the course");
				pstmt.setString(1, sc.next());

				System.out.println("Enter the Name of the course");
				pstmt.setString(2, sc.next());

				System.out.println("Enter the Duration of the course");
				pstmt.setString(3, sc.next() + "months");

				int x = pstmt.executeUpdate();
				if (x > 0) {
					System.out.println("Course Added");
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void removeCou() {
		try {
			String sql = "select * from courses";

			stmt = CourseManagementApp.con.createStatement();
			resultSet = stmt.executeQuery(sql);
		
			while (resultSet.next() == true) {

				System.out.println(resultSet.getString("c_name"));

			}
			String sql1 = "delete from courses where c_name=?";
			pstmt = CourseManagementApp.con.prepareStatement(sql1);

			System.out.println("Enter the name of the Course name to remove");
			pstmt.setString(1, sc.next());

			int x = pstmt.executeUpdate();
			if (x > 0) {
				System.out.println("Course deleted");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void displayCou() {
		try {
			String sql = "select * from courses";

			stmt = CourseManagementApp.con.createStatement();
			resultSet = stmt.executeQuery(sql);
			while (resultSet.next() == true) {
				System.out.print(resultSet.getString(1)+"\t");
				System.out.print(resultSet.getString(2)+"\t");
				System.out.print(resultSet.getString(3)+"\t");
				System.out.println();
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
