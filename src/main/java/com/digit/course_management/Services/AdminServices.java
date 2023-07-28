package com.digit.course_management.Services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.digit.course_management.CourseManagementApp;
import com.digit.course_management.bean.Users;

public class AdminServices {
	ProfessorService p2 = new ProfessorService();
	CourseService c2 = new CourseService();
	StudentService s2 = new StudentService();
	Users us = new Users();
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet resultSet;
	static Scanner sc = new Scanner(System.in);
	public static ArrayList courseList = new ArrayList();

	public void first() throws InterruptedException {
		Thread.sleep(500);
		System.out.println("Welcome To Course Management Student");
		System.out.println("Log-in Yourself here ");
		Thread.sleep(1000);

		System.out.println("1. Admin \n" + "2. Professor\n" + "3. Student\n" + "4. Student Register\n"
				+ "5. Professor Register\n");
		System.out.println("Choose option:");
		try {
			Scanner sc = new Scanner(System.in);
			int n = sc.nextInt();
			switch (n) {
			case 1: {
				boolean b = us.admlogin();
				if (b == true) {
					register();
				}
				break;
			}
			case 2: {
				if (p2.prologin() == true) {

					System.out.print("The detail of unmarked Students are Loading .");
					Thread.sleep(500);
					System.out.print(" .");
					Thread.sleep(500);
					System.out.print(" .");
					System.out.println();
					p2.gradeStu();
				}
				break;
			}
			case 3: {
				if (s2.stulogin() == true) {
					s2.getMark();
				}
				break;
			}
			case 4: {
				stuReq();
//				s2.getMark();
				break;
			}
			case 5: {
				proReq();
				break;
			}

			default: {
				System.out.println("Enter the valid option:");
				first();
			}
			}
		} catch (Exception e) {
			System.out.println("INPUT MISMATCHED");
			Thread.sleep(1500);
			first();

		}

	}

	public void register() throws InterruptedException {
		System.out.println();
		Thread.sleep(500);
		System.out.println("1. Add \n" + "2. view\n" + "3. Remove\n" + "4. Approve Registration\n" + "0. Swap role\n");
		System.out.println("Choose option:");

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		try {
			switch (n) {
			case 1: {
				add();
				register();
				break;
			}
			case 2: {
				view();
				register();
				break;
			}
			case 3: {
				remove();
				register();
				break;
			}
			case 4: {
				p2.approve();
				s2.approveStu();
				System.out.println("There is no request pending...");
				register();
				break;
			}
			case 0: {
				switchrole();
				break;
			}
			default:
				System.out.println("Enter the valid option:");
				register();
			}

		} catch (Exception e) {
			System.out.println("Input mismatch");
			register();
		}
	}

	void add() throws InterruptedException {
		Thread.sleep(500);
		System.out.println("1. Add course\n" + "2. Add Student\n" + "3. Add Professor\n" + "0. Swap role\n");
		System.out.println("Choose option:");

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		try {
			switch (n) {
			case 1: {
				c2.createCou();
				register();
				break;
			}
			case 2: {
				s2.createStu();
				register();
				break;
			}
			case 3: {
				p2.createPro();
				register();
				break;
			}

			case 0: {
				switchrole();
				break;
			}
			default:
				System.out.println("Enter the valid option:");
				add();
			}

		} catch (Exception e) {
			System.out.println("Input mismatch");
			add();
		}

	}

	void remove() {
		System.out.println("1. Remove Course\n" + "2. Remove Professor\n" + "3. Remove Student\n" + "4. Remove User\n"
				+ "0. Swap role\n");
		System.out.println("Choose option:");

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		try {
			switch (n) {
			case 1: {
				c2.removeCou();
				register();
				break;
			}
			case 2: {
				p2.removePro();
				register();
				break;
			}
			case 3: {
				s2.removeStu();
				register();
				break;
			}
			case 4: {
				us.removeuser();
				register();
				break;
			}
			case 0: {
				switchrole();
				break;
			}
			default:
				System.out.println("Enter the valid option:");
				remove();
			}

		} catch (Exception e) {
			System.out.println("Input mismatch");
			remove();
		}
	}

	void view() {
		System.out.println("1. View All Students\n" + "2. View All Courses\n" + "3. View All Professors\n"
				+ "4. View All Users\n" + "0. Swap role\n");
		System.out.println("Choose option:");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		try {
			switch (n) {
			case 1: {
				s2.displayStu();
				register();
				break;
			}
			case 2: {
				c2.displayCou();
				register();
				break;
			}
			case 3: {
				p2.displayPro();
				register();
				break;
			}
			case 4: {
				us.displayUser();
				register();
				break;
			}

			case 0: {
				switchrole();
				break;
			}
			default:
				System.out.println("Enter the valid option:");
				view();
				break;

			}

		} catch (Exception e) {
			System.out.println("Input mismatch");
		}

	}

	public void proReq() {
		try {
			String sql = "insert into prorequest values(?,?,?,?,?,?)";
			pstmt = CourseManagementApp.con.prepareStatement(sql);
			System.out.println("Enter Professor User Id");
			pstmt.setString(1, sc.next());
			System.out.println("Enter Professor Name");
			pstmt.setString(2, sc.next());
			System.out.println("Enter the Password");
			pstmt.setString(3, sc.next());
			System.out.println("Enter the Subject");
			pstmt.setString(4, sc.next());
			System.out.println("Enter the EXP");
			pstmt.setString(5, sc.next());
			pstmt.setString(6, "Professor");

			int x = pstmt.executeUpdate();
			if (x > 0) {
				System.out.println("Wait for admin to approve you.");
			}
			Thread.sleep(3000);
			if (us.admlogin() == true) {
				register();
			}
			if (p2.st == false) {
				System.out.println("Your request is not Approve by the admin.");
				Thread.sleep(3000);
				first();
			}
		} catch (Exception e) {
			System.out.println("Exception Occured");
			proReq();
		}
	}

	public void stuReq() {
		try {
			String sql = "insert into sturequest values(?,?,?,?,?)";
			pstmt = CourseManagementApp.con.prepareStatement(sql);
			System.out.println("Enter Student User Id");
			pstmt.setString(1, sc.next());

			System.out.println("Enter Student Name");
			pstmt.setString(2, sc.next());

			System.out.println("Enter the Password");
			pstmt.setString(3, sc.next());

			String sql5 = "select * from courses";

			stmt = CourseManagementApp.con.createStatement();
			resultSet = stmt.executeQuery(sql5);
			while (resultSet.next() == true) {
				courseList.add(resultSet.getString("c_name"));
				System.out.println(resultSet.getString("c_name"));
			}
			System.out.println("Enter Student Course");
			pstmt.setString(4, sc.next());

			pstmt.setString(5, "Student");

			int x = pstmt.executeUpdate();
			if (x > 0) {
				System.out.println("Wait for admin to approve you.");
			}
			Thread.sleep(3000);
			if (us.admlogin() == true) {
				register();
			}
			if (s2.st1 == false) {
				System.out.println("Your request is not Approve by the admin.");
				Thread.sleep(3000);
				first();
			}
		} catch (Exception e) {
			System.out.println("Exception Occured");
			stuReq();
		}
	}

	public void switchrole() throws Exception {
		System.out.println("Press\033[1m P \033[0mto login as a \033[1mProfessor\033[0m\n"
				+ "Press \033[1mS\033[0m to login as a  \033[1m Student\033[0m");
		String roles = sc.next();
		if (roles.equalsIgnoreCase("p")) {
			if (p2.prologin() == true) {
				p2.gradeStu();
				Thread.sleep(4000);
				us.admlogin();
			}
		} else if (roles.equalsIgnoreCase("s")) {
			if (s2.stulogin() == true) {
				s2.getMark();
				Thread.sleep(4000);
				us.admlogin();
			}

		} else {
			System.out.println("Enter a valid option");
			switchrole();
		}
	}
}
