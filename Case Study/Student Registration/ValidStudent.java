package com.oupp.jdbc.caseStudy.studentRegistration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ValidStudent {
	static Connection connection;
	static Scanner scanner;

	ValidStudent() {

	}

	ValidStudent(Connection connection, Scanner scanner) {
		this.connection = connection;
		this.scanner = scanner;

	}

	public static  boolean studentCheck() {

		String check_query = "select * from sdvpschool_Registation where roll_no =? password=?";
		System.out.print("Enter your rollno :");
		long rollNo = scanner.nextLong();
		scanner.nextLine();
		System.out.print("Enter Your Password :");
		String password = scanner.nextLine();

		try {
			PreparedStatement preparedstatement = connection.prepareStatement(check_query);
			preparedstatement.setLong(1, rollNo);
			preparedstatement.setString(1, password);

			ResultSet resultset = preparedstatement.executeQuery();
			if (resultset.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return false;

	}

	public void showPassword() {
		String show_query = "select * from sdvpschool_Registation where roll_no=?";
		System.out.println("pls Enter your Roll No");
		int roll_no = scanner.nextInt();
		
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement(show_query); 
			preparedStatement.setLong(1,roll_no);
			
			ResultSet resultset = preparedStatement.executeQuery();
			while(resultset.next()) {
				String password = resultset.getString("studentpassword");
				System.out.println("Your password is "+password);
			}
			
			
			
		}catch(SQLException ob) {
			System.out.println(ob.getMessage());
			
		}
		
	}

	public void changePassword() {
		// TODO Auto-generated method stub
		
	}

}
