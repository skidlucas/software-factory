package webservices;


import catalogue.CatalogueExploration;
import customer.registry.CustomerRegistration;
import entities.Cookies;
import entities.OrderStatus;
import exceptions.AlreadyExistingCustomerException;
import exceptions.UnknownOrderId;
import kitchen.Tracker;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import java.util.Set;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/tcf/customer-care")
@Stateless(name = "CustomerCareWS")
public class CustomerCareServiceImpl implements CustomerCareService {

	@EJB private CustomerRegistration registry;
	@EJB private CatalogueExploration catalogue;
	@EJB private Tracker tracker;

	@Override
	public void register(String name, String creditCard) throws AlreadyExistingCustomerException {
		registry.register(name, creditCard);
	}

	@Override
	public OrderStatus track(String orderId) throws UnknownOrderId {
		return tracker.status(orderId);
	}

	@Override
	public Set<Cookies> listAllRecipes() {
		return catalogue.listPreMadeRecipes() ;
	}

}
