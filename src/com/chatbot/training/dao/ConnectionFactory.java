package com.chatbot.training.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {
	private static Logger logger = Logger.getLogger(ConnectionFactory.class.getName());

	private static final String H2_URL = "jdbc:h2:tcp://localhost/~/pizza_order";
	private static final String USER = "sa";
	private static final String PASSWORD = "";
	private static final String H2_DRIVER = "org.h2.Driver";

	private static Connection con;

	public static Connection getConnection() {
		try {
			if (Objects.isNull(con) || con.isClosed()) {
				Class.forName(H2_DRIVER);
				con = DriverManager.getConnection(H2_URL, USER, PASSWORD);
			}
		} catch (SQLException | ClassNotFoundException e) {
			logger.log(Level.WARNING, e.getMessage());
		}
		return con;
	}

	public static void closeConnection() {
		if (Objects.nonNull(con)) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
