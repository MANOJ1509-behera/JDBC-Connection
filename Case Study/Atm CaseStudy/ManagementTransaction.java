package com.oupp.jdbc.caseStudy.atmCaseStudy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ManagementTransaction {
	Connection connection;
	Scanner scanner;

	public ManagementTransaction(Connection connection, Scanner scanner) {
		this.connection = connection;
		this.scanner = scanner;
	}

	/*
	 * in this method we take the security key from the user and ask how much amount
	 * he want to save and by the help preparedstatment we fetch account baance and
	 * save in database
	 */
	public void deposite() {
		System.out.print("pls Enter How much money you want to deposite : ");
		long amount = scanner.nextLong();
		scanner.nextLine();
		System.out.print("pls Enter your security key :");
		String securityPin = scanner.nextLine();

		String deposite_query = "update atm_transaction set amount = amount + ? where securityPin =?";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(deposite_query);
			preparedStatement.setLong(1, amount);
			preparedStatement.setString(2, securityPin);

			int rowEffected = preparedStatement.executeUpdate();
			if (rowEffected > 0) {
				System.out.println("Money Deposite Succesfully");

			} else {
				System.out.println("Transaction failed due to technical error");
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());

		}

	}

	public void withdraw() {
		System.out.print("How Much Amount You Want to withdraw :");
		long amountWithdraw = scanner.nextLong();
		scanner.nextLine();
		System.out.println("Enter Your Security Key :");
		String SecurityKey = scanner.nextLine();

		String withdraw_query = "select * from atm_transaction where securityPin=?";

		try {
			// System.out.println("i am inside try");
			PreparedStatement preparedstatment = connection.prepareStatement(withdraw_query);
			preparedstatment.setString(1, SecurityKey);

			ResultSet resultset = preparedstatment.executeQuery();

			if (resultset.next()) {
				long current_balance = resultset.getLong("amount");
				if (current_balance > amountWithdraw) {
					String debit_query = "update atm_transaction set amount=amount-? where securityPin =?";
					PreparedStatement preparedstatment1 = connection.prepareStatement(debit_query);
					preparedstatment1.setLong(1, amountWithdraw);
					preparedstatment1.setString(2, SecurityKey);
					int rowsEffected = preparedstatment1.executeUpdate();

					if (rowsEffected > 0) {
						System.out.println("The " + amountWithdraw + " amount withdraw from your account");
						// connection.commit();
					} else {
						System.out.println("Money cannot withdraw due to some technical error");
						connection.rollback();
					}

				} else {
					System.out.println("Insufficient Balance in your account");
				}

			}

		} catch (SQLException e) {
			// e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}

}
