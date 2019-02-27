package com.chatbot.training.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
public class Order {
	private int id;
	private int pizzAmount;
	private String size;
	private String flavor;
	private String cusName;
	private String cusAddress;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPizzAmount() {
		return pizzAmount;
	}

	public void setPizzAmount(int pizzAmount) {
		this.pizzAmount = pizzAmount;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getFlavor() {
		return flavor;
	}

	public void setFlavor(String flavor) {
		this.flavor = flavor;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getCusAddress() {
		return cusAddress;
	}

	public void setCusAddress(String cusAddress) {
		this.cusAddress = cusAddress;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", pizzAmount=" + pizzAmount + ", size=" + size + ", flavor=" + flavor + ", cusName="
				+ cusName + ", cusAddress=" + cusAddress + "]";
	}

}
