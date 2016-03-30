package customer.registry.components;

import customer.registry.CustomerFinder;
import customer.registry.CustomerRegistration;
import database.Database;
import entities.Customer;
import exceptions.AlreadyExistingCustomerException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Optional;


@Stateless
public class CustomerRegistryBean
		implements CustomerRegistration, CustomerFinder {

	@EJB
	private Database memory;

	/******************************************
	 ** Customer Registration implementation **
	 ******************************************/

	@Override
	public void register(String name, String creditCard)
			throws AlreadyExistingCustomerException {
//	 	if(findByName(name).isPresent())
//			throw new AlreadyExistingCustomerException(name);
//		memory.getCustomers().put(name, new Customer(name, creditCard));

		//broken on purpose
	}


	/************************************
	 ** Customer Finder implementation **
	 ************************************/

	@Override
	public Optional<Customer> findByName(String name) {
		if (memory.getCustomers().containsKey(name))
			return Optional.of(memory.getCustomers().get(name));
		else
			return Optional.empty();
	}

}

