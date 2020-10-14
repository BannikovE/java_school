package app.dao;

import app.model.Order;
import app.model.OrderList;
import app.model.Product;
import app.model.cart.Cart;
import app.model.cart.OrderDTO;
import app.model.cart.OrderListDTO;

import java.util.List;

public interface OrderDAO {
    Order save(Cart cart);
    Order getOrderById(int id);
    OrderDTO getOrderDTOById(int id);
    List<OrderListDTO> listOrderDTO(int id);
}
