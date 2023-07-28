package com.digit.course_management;

import java.sql.Connection;
import java.sql.DriverManager;

import com.digit.course_management.Services.AdminServices;
import com.digit.course_management.Services.ProfessorService;
import com.digit.course_management.bean.Users;

public class CourseManagementApp {
	public static Connection con;

	public static void main(String[] args) throws Exception {
		AdminServices adm = new AdminServices();
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver Loaded");
		String url = "jdbc:mysql://localhost:3306/crs";

		String user = "root";
		String pwd = "root";
		con = DriverManager.getConnection(url, user, pwd);

		adm.first();
	}
}
