package com.oupp.jdbc.caseStudy.atmCaseStudy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DisplayAccount {
	Connection connection;
	Scanner scanner;

	DisplayAccount() {

	}

	DisplayAccount(Connection connection, Scanner scanner) {
		this.connection = connection;
		this.scanner = scanner;
	}

	public void display() {

		String display_query = "select * from atm_transaction where securityPin=?";
		scanner.nextLine();
		System.out.print("pls Enter Your Security pin :");
		String SecurityPin = scanner.nextLine();

		try {
			/*
			 * by the help of security pin we traverse throuch our database by the help of
			 * result set using resultset we find how much money your account have and then
			 * display the how remaining balance you have
			 */
			PreparedStatement preparedstatment = connection.prepareStatement(display_query);
			preparedstatment.setString(1, SecurityPin);

			ResultSet resultSet = preparedstatment.executeQuery();

			if (resultSet.next()) {
				long balance = resultSet.getLong("amount");
				System.out.println("your account have only " + balance + " only");

			} else {
				System.out.println("due to some technical issue the amount can not fetch");
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

}
