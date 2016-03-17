package cart.stateful.components;


import cart.components.CartBean;
import entities.Customer;
import entities.Item;

import javax.ejb.Stateful;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Stateful(name = "cart-stateful")
public class CartStatefulBean extends CartBean {

	private Map<Customer, Set<Item>> carts = new HashMap<>();

	@Override
	public boolean add(Customer c, Item item) {
		carts.put(c, updateCart(c, item));
		return true;
	}

	@Override
	public Set<Item> contents(Customer c) {
		return carts.getOrDefault(c, new HashSet<Item>());
	}

}
