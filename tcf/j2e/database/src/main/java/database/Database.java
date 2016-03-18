package database;


import entities.Customer;
import entities.Item;
import entities.Order;

import javax.ejb.Singleton;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Singleton
public class Database {

	private Map<Customer, Set<Item>> carts;
	public Map<Customer, Set<Item>> getCarts() { return carts; }

	private Map<String, Customer> customers;
	public Map<String, Customer> getCustomers() { return customers; }

	private Map<String, Order> orders;
	public Map<String, Order> getOrders() { return orders; }

	private int cartCounter = 0;
	public void incrementCarts() { cartCounter++; }
	public int howManyCarts() { return cartCounter; }

	public Database() {
		flush();
	}

	public void flush() {
		carts       = new HashMap<>();
		customers   = new HashMap<>();
		orders      = new HashMap<>();
		cartCounter = 0;
	}

}
