package app.service;

import app.model.Order;
import app.model.Product;
import app.model.User;
import app.model.cart.Cart;
import app.model.cart.OrderDTO;
import app.model.cart.OrderListDTO;
import app.model.enums.DeliveryMethod;
import app.model.enums.PaymentMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderService {
    Order save(DeliveryMethod deliveryMethod, PaymentMethod paymentMethod, Integer addressId,
               HttpServletRequest request, HttpServletResponse response);
    Order getOrderById(Integer id);
    OrderDTO getOrderDTOById(Integer id);
    List<OrderListDTO> listOrderDTO(Integer id);
    List<Order> getOrdersByUserId(Integer userId);
    List<Order> getAllOrders();
    Order edit(Order order);
    Map<Product, Integer> getTenProducts();
    Map<User, Integer> getTenUsers();
    Double getMonthlyIncome();
    Double getDailyIncome();
    Date getOrderDateById(Integer id);
}