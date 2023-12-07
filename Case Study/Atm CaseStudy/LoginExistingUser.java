package com.oupp.jdbc.caseStudy.atmCaseStudy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class LoginExistingUser {

	Connection connection;
	Scanner scanner;

	LoginExistingUser() {

	}

	LoginExistingUser(Connection connection, Scanner scanner) {
		this.connection = connection;
		this.scanner = scanner;

	}

	public boolean loginCheck() {
		System.out.print("Enter your account no :");
		long accountNo = scanner.nextLong();
		scanner.nextLine();
		System.out.print("Enter your securitypin :");
		String securityPin = scanner.nextLine();

		String valid_query = "select * from atm_transaction where accountNumber=? and securityPin=?";

		try {
			PreparedStatement preparedstatement	 = connection.prepareStatement(valid_query);
			preparedstatement.setLong(1, accountNo);
			preparedstatement.setString(2, securityPin);
		
			ResultSet resultset = preparedstatement.executeQuery();

			if(resultset.next()) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return false;

	}

}
