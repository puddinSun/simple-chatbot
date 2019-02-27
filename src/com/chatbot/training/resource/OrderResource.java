package com.chatbot.training.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.chatbot.training.dao.OrderDao;
import com.chatbot.training.model.Order;

@Path("/order")
public class OrderResource {

	private OrderDao orderDao;

	public OrderResource() {
		orderDao = new OrderDao();
	}

	@POST
	@Path("/save")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void saveOrder(Order order) {
		orderDao.saveOrder(order);
	}

	@GET
	@Path("/all")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Order> findAllOrder() {
		return orderDao.findAllOrder();
	}
}
