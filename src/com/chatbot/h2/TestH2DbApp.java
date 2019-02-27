package com.chatbot.h2;

import java.util.logging.Logger;

import com.chatbot.training.dao.OrderDao;

public class TestH2DbApp {
	public static Logger logger = Logger.getLogger(TestH2DbApp.class.getName());

	public static void main(String[] args) {
		// Order order = new Order();
		// order.setPizzAmount(3);
		// order.setSize("Small");
		// order.setFlavor("Chicken");
		// order.setCusName("Alice");
		// order.setCusAddress("Ha Noi");

		OrderDao orderDao = new OrderDao();
		System.out.println(orderDao.findAllOrder());
	}
}
