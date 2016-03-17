package webservice;

import fr.unice.polytech.isa.tcf.entities.Item;
import fr.unice.polytech.isa.tcf.exceptions.PaymentException;
import fr.unice.polytech.isa.tcf.exceptions.UnknownCustomerException;
import fr.unice.polytech.isa.tcf.interceptors.ItemVerifier;

import javax.interceptor.Interceptors;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.Set;

@WebService
public interface CartWebService {

	@WebMethod
	@Interceptors({ItemVerifier.class})
	void addItemToCustomerCart(@WebParam(name = "customer_name") String customerName,
							   @WebParam(name = "item") Item it)
			throws UnknownCustomerException;

	@WebMethod
	@Interceptors({ItemVerifier.class})
	void removeItemToCustomerCart(@WebParam(name = "customer_name") String customerName,
								  @WebParam(name = "item") Item it)
			throws UnknownCustomerException;

	@WebMethod
	@WebResult(name = "cart_contents")
	Set<Item> getCustomerCartContents(@WebParam(name = "customer_name") String customerName)
			throws UnknownCustomerException;

	@WebMethod
	@WebResult(name = "order_id")
	String validate(@WebParam(name = "customer_name") String customerName)
			throws PaymentException, UnknownCustomerException;

}
