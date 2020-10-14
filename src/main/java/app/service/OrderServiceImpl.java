package app.service;

import app.dao.OrderDAO;
import app.model.Order;
import app.model.cart.Cart;
import app.model.cart.OrderDTO;
import app.model.cart.OrderListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService{
    private OrderDAO orderDAO;

    @Autowired
    public void setOrderDAO(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Transactional
    @Override
    public Order save(Cart cart) {
        return orderDAO.save(cart);
    }

    @Override
    public Order getOrderById(int id) {
        return orderDAO.getOrderById(id);
    }

    @Override
    public OrderDTO getOrderDTOById(int id) {
        return orderDAO.getOrderDTOById(id);
    }

    @Override
    public List<OrderListDTO> listOrderDTO(int id) {
        return orderDAO.listOrderDTO(id);
    }
}
