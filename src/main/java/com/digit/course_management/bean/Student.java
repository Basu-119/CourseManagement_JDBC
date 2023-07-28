package com.digit.course_management.bean;

import java.sql.PreparedStatement;
import java.util.Scanner;

import com.digit.course_management.CourseManagementApp;

public class Student {
	private PreparedStatement pstmt;
	Scanner sc = new Scanner(System.in);
	String stu_name, stu_cou, stu_pro;
	String stu_id;

	void regiStu() {
		try {
			String sql = "insert into users values(?,?,?)";
			pstmt = CourseManagementApp.con.prepareStatement(sql);
			System.out.println("Enter your User Id");
			pstmt.setString(1, sc.next());

			System.out.println("Enter the Password");
			pstmt.setString(2, sc.next());

			pstmt.setString(3, "Student");

			int x = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getStu_name() {
		return stu_name;
	}

	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}

	public String getStu_cou() {
		return stu_cou;
	}

	public void setStu_cou(String stu_cou) {
		this.stu_cou = stu_cou;
	}

	public String getStu_pro() {
		return stu_pro;
	}

	public void setStu_pro(String stu_pro) {
		this.stu_pro = stu_pro;
	}

	
}
