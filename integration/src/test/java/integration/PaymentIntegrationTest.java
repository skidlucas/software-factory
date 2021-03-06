package integration;


import arquillian.AbstractTCFTest;
import cart.Payment;
import customer.registry.CustomerFinder;
import customer.registry.CustomerRegistration;
import entities.*;
import kitchen.Tracker;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class PaymentIntegrationTest extends AbstractTCFTest {

	@EJB private Payment cashier;
	@EJB private CustomerFinder finder;
	@EJB private CustomerRegistration registration;
	@EJB private Tracker tracker;

	private Set<Item> items;

	@Before
	public void setUpContext() {
		memory.flush();
		items = new HashSet<>();
		items.add(new Item(Cookies.CHOCOLALALA, 3));
		items.add(new Item(Cookies.DARK_TEMPTATION, 2));
	}

	@Test
	public void integrationBetweenCustomersAndOrders() throws Exception {
		registration.register("john", "1234-896983");
		Customer retrieved = finder.findByName("john").get();
		assertTrue(retrieved.getOrders().isEmpty());
		String id = cashier.payOrder(retrieved, items);
		Order order = memory.getOrders().get(id);
		assertTrue(retrieved.getOrders().contains(order));
	}

	@Test
	public void integrationBetweenStatusAndOrders() throws Exception {
		registration.register("paul", "1234-896983");
		Customer retrieved = finder.findByName("paul").get();
		String id = cashier.payOrder(retrieved, items);
		Assert.assertNotEquals(0, memory.getOrders().size());
		Assert.assertEquals(OrderStatus.IN_PROGRESS, tracker.status(id));
		registration.register("raul", "1234-896983");
		retrieved = finder.findByName("raul").get();
		id = cashier.payOrder(retrieved, items);
		Assert.assertEquals(OrderStatus.READY, tracker.status(id));
	}


}
