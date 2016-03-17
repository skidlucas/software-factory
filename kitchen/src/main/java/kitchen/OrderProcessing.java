package kitchen;

import entities.Order;

import javax.ejb.Local;

@Local
public interface OrderProcessing {

	void process(Order order);

}
