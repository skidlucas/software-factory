package kitchen;

import entities.OrderStatus;
import exceptions.UnknownOrderId;

import javax.ejb.Local;

@Local
public interface Tracker {

	OrderStatus status(String orderId) throws UnknownOrderId;

}
