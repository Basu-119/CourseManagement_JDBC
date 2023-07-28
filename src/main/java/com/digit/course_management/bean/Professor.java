package com.digit.course_management.bean;

import java.sql.PreparedStatement;
import java.util.Scanner;

import com.digit.course_management.CourseManagementApp;

public class Professor {
	String pro_id;
	String pro_name;
	String pro_exp;
	String pro_sub;
	
	

	public String getPro_id() {
		return pro_id;
	}

	public void setPro_id(String pro_id) {
		this.pro_id = pro_id;
	}

	public String getPro_name() {
		return pro_name;
	}

	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}

	public String getPro_exp() {
		return pro_exp;
	}

	public void setPro_exp(String pro_exp) {
		this.pro_exp = pro_exp;
	}

	public String getPro_sub() {
		return pro_sub;
	}

	public void setPro_sub(String pro_sub) {
		this.pro_sub = pro_sub;
	}

}
