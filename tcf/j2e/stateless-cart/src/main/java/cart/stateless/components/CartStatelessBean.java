package cart.stateless.components;


import cart.components.CartBean;
import database.Database;
import entities.Customer;
import entities.Item;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.HashSet;
import java.util.Set;

@Stateless(name = "cart-stateless")
public class CartStatelessBean extends CartBean {

	@EJB
	private Database memory;

	@Override
	public boolean add(Customer c, Item item) {
		memory.getCarts().put(c, updateCart(c, item));
		return true;
	}

	@Override
	public Set<Item> contents(Customer c) {
		return memory.getCarts().getOrDefault(c, new HashSet<Item>());
	}

}
