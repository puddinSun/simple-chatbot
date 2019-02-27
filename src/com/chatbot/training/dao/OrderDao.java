package com.chatbot.training.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.chatbot.training.model.Order;

public class OrderDao {
	private static Logger logger = Logger.getLogger(OrderDao.class.getName());

	private Connection con;
	private String saveOrder = "INSERT INTO orders(amount, flavor, size, customer_name, customer_address) VALUES (?, ?, ?, ?, ?)";
	private String findAllSql = "SELECT * FROM orders";

	public void saveOrder(Order order) {
		logger.info("Begin save order ...");
		con = ConnectionFactory.getConnection();
		try {
			PreparedStatement preStmt = con.prepareStatement(saveOrder);
			preStmt.setInt(1, order.getPizzAmount());
			preStmt.setString(2, order.getFlavor());
			preStmt.setString(3, order.getSize());
			preStmt.setString(4, order.getCusName());
			preStmt.setString(5, order.getCusAddress());

			int resultNumber = preStmt.executeUpdate();

			if (resultNumber > 0) {
				logger.info("Save order successfully!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection();
		}
	}

	public List<Order> findAllOrder() {
		logger.info("Begin list all orders ...");
		con = ConnectionFactory.getConnection();
		List<Order> orders = new ArrayList<>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(findAllSql);

			while (rs.next()) {
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setSize(rs.getString("size"));
				order.setPizzAmount(rs.getInt("amount"));
				order.setFlavor(rs.getString("flavor"));
				order.setCusName(rs.getString("customer_name"));
				order.setCusAddress(rs.getString("customer_address"));

				orders.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			logger.info("Close connection ...");
			ConnectionFactory.closeConnection();
		}

		return orders;
	}
}
