package app.service;

import app.model.Order;
import app.model.cart.Cart;
import app.model.cart.OrderDTO;
import app.model.cart.OrderListDTO;

import java.util.List;

public interface OrderService {
    Order save(Cart cart);
    Order getOrderById(int id);
    OrderDTO getOrderDTOById(int id);
    List<OrderListDTO> listOrderDTO(int id);
}
