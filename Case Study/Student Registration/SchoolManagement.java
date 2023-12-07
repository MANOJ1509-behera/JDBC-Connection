package com.oupp.jdbc.caseStudy.studentRegistration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class SchoolManagement {

	private static final String url = "jdbc:mysql://localhost:3306/ouppdb";
	private static final String userName = "root";
	private static final String password = "Manoj@2601";

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);

		// Loaded the Driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded succesfully");

		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());

		}

		// connect with Database
		
		System.out.println(" * * * * * * *WELCOME TO SDVP * * * * * * * ");

		try {
			
			Connection connection = DriverManager.getConnection(url, userName, password);
			System.out.println("Connected With Database Succesfully");
			NewStudentRegistration newStudent = new NewStudentRegistration(connection, scanner);
			ValidStudent validstudent = new ValidStudent(connection,scanner);
			boolean enter = true;
			int choice;
			
			while(enter) {
				System.out.println("------ ------ ------ ------ ------ ------");
				System.out.println("1.Admission new Student\n2.Valid Student ");
				System.out.print("Enter Your choice :");
				choice = scanner.nextInt();
				
				
				switch(choice) {
				case 1:
					newStudent.NewAddmission();
					break;
				case 2:
					if(validstudent.studentCheck()) {
						
						int choice2 ;
						boolean codCheck = true;
						while(codCheck) {
							System.out.println("* * * * * * * * * * * * *  *");
							System.out.println("1.Show Your Password\n2.Update Your Password");
							System.out.println("Enter Your Choice : ");
							choice2 = scanner.nextInt();
							
							switch(choice2) {
							case 1:
								validstudent.showPassword();
								break;
							case 2:
								validstudent.changePassword();
							default:
								System.out.println("You Enter Invalid Input");
								codCheck=false;
								
								
							}
						}
						
						
					}else {
						System.out.println("Your are not valid student in out school");
					}
					break;
				default :
					enter=false;
				
				}
			}
			
			System.out.println("* * * * Visit Again * * * * *");

		} catch (SQLException e) {

		}

		
		
		

	}

}
