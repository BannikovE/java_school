package app.dao;

import app.model.Order;
import app.model.OrderList;
import app.model.Product;
import app.model.User;
import app.model.cart.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDAOImpl implements OrderDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private int getMaxOrderNum(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(
                "Select max(o.orderNum) from " + Order.class.getName() + " o ");
        Integer value = (Integer) query.uniqueResult();
        if (value == null)
            return 0;
        return value;
    }

    @Override
    public Order save(Cart cart) {
        Session session = sessionFactory.getCurrentSession();
        Order order = new Order();
        order.setAmount(cart.getAmountTotal());
        UserDTO userDTO = cart.getUserDTO();
        order.setClientId(userDTO.getId());
        List<CartLine> cartLines = cart.getCartLines();
        for (CartLine cartLine : cartLines){
            OrderList list = new OrderList();
            list.setOrder(order);
            list.setAmount(cartLine.getAmount());
            list.setPrice(cartLine.getProductDTO().getPrice());
            list.setProductId(cartLine.getProductDTO().getId());
//            session.persist(list);
            order.getOrderList().add(list);
        }
        Integer orderId = (Integer) session.save(order);
        return this.getOrderById(orderId);
    }

    @Override
    public Order getOrderById(int id){
        Session session = sessionFactory.getCurrentSession();
        return session.get(Order.class, id);
    }

    @Override
    public OrderDTO getOrderDTOById(int id) {
        Order order = this.getOrderById(id);
        if (order == null)
            return null;
        return new OrderDTO(order.getId(), order.getAddressId(),
                order.getClientId(), order.getAmount(), order.getOrderNum());
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<OrderListDTO> listOrderDTO(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select new" + OrderListDTO.class.getName()
                + "d.id, d.product, d.quantity, d.price, d.amount)"
                + "from" + OrderList.class.getName() + " d "
                + "where d.order.id=:id");
        query.setParameter("id", id);
        return query.list();
    }
}