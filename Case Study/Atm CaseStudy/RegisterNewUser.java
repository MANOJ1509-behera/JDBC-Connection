package com.oupp.jdbc.caseStudy.atmCaseStudy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class RegisterNewUser {
	Connection connection;
	Scanner scanner;

	RegisterNewUser() {

	}

	RegisterNewUser(Connection connection, Scanner scanner) {
		this.connection = connection;
		this.scanner = scanner;

	}

	public void newUser() {
		scanner.nextLine();
		System.out.print("Enter your name : ");
		String name = scanner.nextLine();
		System.out.print("Enter your AccountNo :");
		long accNo = scanner.nextLong();
		scanner.nextLine();
		System.out.print("Enter your email :");
		String email = scanner.nextLine();
		System.out.print("Enter Initial Amount :");
		long amount = scanner.nextLong();
		scanner.nextLine();
		System.out.print("Enter your security pin :");
		String securitypin = scanner.nextLine();

		// check the account is already exit or not

		if (!account_exit(accNo)) {
			String insert_query = "insert into atm_transaction(accountNumber,full_name,email,amount,securityPin) values(?,?,?,?,?)";
			try {
					PreparedStatement preparedStatement = connection.prepareStatement(insert_query);
					preparedStatement.setLong(1,accNo);
					preparedStatement.setString(2,name);
					preparedStatement.setString(3,email);
					preparedStatement.setLong(4,amount);
					preparedStatement.setString(5,securitypin);
					
					int rowEffected = preparedStatement.executeUpdate();
					
						if(rowEffected > 0) {
							System.out.println("Data inserted succesfullly");
						}else {
							System.out.println("There is some technical issue");
						}
				
			}catch(SQLException e) {
				System.out.println("error");
				System.out.println(e.getMessage());
				
			}

		}else {
			System.out.println("Account is alreay exit");
		}

	}

	private boolean account_exit(long accNo) {

		String exit_query = "select * from atm_transaction where accountNumber=?";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(exit_query);
			preparedStatement.setLong(1, accNo);
			ResultSet resultset = preparedStatement.executeQuery();

			if (resultset.next()) {
				return true;

			} else {
				return false;
			}

		} catch (SQLException e) {

		}

		return false;
	}

}
