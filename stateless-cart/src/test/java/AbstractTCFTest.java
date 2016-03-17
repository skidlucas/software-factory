

import cart.CartModifier;
import cart.components.CartBean;
import cart.stateless.components.CartStatelessBean;
import customer.registry.CustomerFinder;
import customer.registry.CustomerRegistration;
import customer.registry.components.CustomerRegistryBean;
import database.Database;
import entities.Customer;
import exceptions.AlreadyExistingCustomerException;
import interceptors.Logger;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import javax.ejb.EJB;

public abstract class AbstractTCFTest {


	@EJB
	protected Database memory;

	@Deployment
	public static WebArchive createDeployment() {
		// Building a Web ARchive (WAR) containing the following elements:
		return ShrinkWrap.create(WebArchive.class)
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				// Utils
				.addPackage(Database.class.getPackage())
				// Entities
				.addPackage(Customer.class.getPackage())
				// Components Interfaces
				.addPackage(CartModifier.class.getPackage())
				.addPackage(CartStatelessBean.class.getPackage())
				// Interceptors
				.addPackage(Logger.class.getPackage())
				// Exceptions
				.addPackage(AlreadyExistingCustomerException.class.getPackage())
				// Components implementations
				.addPackage(CartBean.class.getPackage())
				.addPackage(CustomerFinder.class.getPackage())
				.addPackage(CustomerRegistration.class.getPackage())
				.addPackage(CustomerRegistryBean.class.getPackage());
	}

}
