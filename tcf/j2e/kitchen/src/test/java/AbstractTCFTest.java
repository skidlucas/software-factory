import database.Database;
import entities.Customer;
import exceptions.AlreadyExistingCustomerException;
import interceptors.Logger;
import kitchen.components.KitchenBean;
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
				// Cart components
				// Interceptors
				.addPackage(Logger.class.getPackage())
				// Exceptions
				.addPackage(AlreadyExistingCustomerException.class.getPackage())
				.addPackage(KitchenBean.class.getPackage());
				// Components implementations
	}

}
