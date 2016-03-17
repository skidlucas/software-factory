package webservices;

import entities.Cookies;
import entities.OrderStatus;
import exceptions.AlreadyExistingCustomerException;
import exceptions.UnknownOrderId;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.Set;

@WebService
public interface CustomerCareService {

	@WebMethod
	void register(@WebParam(name="customer_name") String name,
				  @WebParam(name="credit_card_number") String creditCard)
			throws AlreadyExistingCustomerException, AlreadyExistingCustomerException;


	@WebMethod
	@WebResult(name = "status")
	OrderStatus track(@WebParam(name="order_id") String orderId)
		throws UnknownOrderId, UnknownOrderId;

	@WebMethod
	@WebResult(name = "recipes")
	Set<Cookies> listAllRecipes();

}
