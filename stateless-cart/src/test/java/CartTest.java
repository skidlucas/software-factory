
import cart.CartModifier;
import customer.registry.CustomerFinder;
import customer.registry.CustomerRegistration;
import entities.Cookies;
import entities.Customer;
import entities.Item;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

// component test framework import
// java annotations
// static import to lighten test writing


@RunWith(Arquillian.class)
public class CartTest extends AbstractTCFTest {

	@EJB(name = "cart-stateless") private CartModifier cart;
	@EJB
	CustomerRegistration registry;
	@EJB
	CustomerFinder finder;

	private Customer john;

	@Before
	public void setUpContext() throws Exception {
		memory.flush();
		registry.register("John", "credit card number");
		john = finder.findByName("John").get();
	}

	@Test
	public void emptyCartByDefault() {
		Set<Item> data = cart.contents(john);
		assertArrayEquals(new Item[] {}, data.toArray());
	}

	@Test
	public void addItems() {
		cart.add(john, new Item(Cookies.CHOCOLALALA, 2));
		cart.add(john, new Item(Cookies.DARK_TEMPTATION, 3));
		Item[] oracle = new Item[] {new Item(Cookies.CHOCOLALALA, 2), new Item(Cookies.DARK_TEMPTATION, 3)  };
		assertEquals(new HashSet<>(Arrays.asList(oracle)), cart.contents(john));
	}

	@Test
	public void removeItems() {
		cart.add(john, new Item(Cookies.CHOCOLALALA, 2));
		cart.remove(john, new Item(Cookies.CHOCOLALALA, 2));
		assertArrayEquals(new Item[] {}, cart.contents(john).toArray());
		cart.add(john, new Item(Cookies.CHOCOLALALA, 6));
		cart.remove(john, new Item(Cookies.CHOCOLALALA, 5));
		assertArrayEquals(new Item[] {new Item(Cookies.CHOCOLALALA, 1)}, cart.contents(john).toArray());
	}

	@Test
	public void modifyQuantities() {
		cart.add(john, new Item(Cookies.CHOCOLALALA, 2));
		cart.add(john, new Item(Cookies.DARK_TEMPTATION, 3));
		cart.add(john, new Item(Cookies.CHOCOLALALA, 3));
		Item[] oracle = new Item[] {new Item(Cookies.CHOCOLALALA, 5), new Item(Cookies.DARK_TEMPTATION, 3)  };
		assertEquals(new HashSet<>(Arrays.asList(oracle)), cart.contents(john));
	}

}
