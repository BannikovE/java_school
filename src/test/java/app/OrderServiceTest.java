package app;

import app.dao.AddressDAOImpl;
import app.dao.OrderDAOImpl;
import app.dao.ProductDAOImpl;
import app.dao.UserDAOImpl;
import app.model.Product;
import app.model.Order;
import app.model.OrderList;
import app.model.Address;
import app.model.User;
import app.model.enums.PaymentMethod;
import app.model.enums.OrderStatus;
import app.model.enums.PaymentState;
import app.model.enums.UserStatus;
import app.model.enums.UserRole;
import app.service.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @InjectMocks
    private OrderServiceImpl orderService;
    @Mock
    private OrderDAOImpl orderDAO;
    @Mock
    private AddressDAOImpl addressDAO;
    @Mock
    private UserDAOImpl userDAO;
    @Mock
    private ProductDAOImpl productDAO;

    private static Order order;
    private static Order newOrder;
    private static List<Order> list;
    private static Address address;
    private static User user;

    @BeforeEach
    public void setUp() {
        OrderList orders = new OrderList(1, 1000, 2, 2000, order, 1);
        List<OrderList> orderList = new ArrayList<>();
        orderList.add(orders);

        user = User.newBuilder()
                .setId(1)
                .setDateOfBirth(new Date())
                .setEmail("mail@mail.com")
                .setFirstName("Egor")
                .setLastName("Bannikov")
                .setPassword("11111111")
                .setPasswordConfirm("11111111")
                .setRole(UserRole.USER)
                .setStatus(UserStatus.ACTIVE)
                .build();


        address = Address.newBuilder()
                .setId(1)
                .setCountry("Russia")
                .setCity("Moscow")
                .setIndex(456345)
                .setStreet("Street")
                .setBuilding(100)
                .setRoom(10)
                .setUser(user)
                .build();

        order = Order.newBuilder()
                .setId(71)
                .setOrderList(orderList)
                .setOrderNum(10)
                .setOrderStatus(OrderStatus.DELIVERED)
                .setAddress(address)
                .setAddressId(address.getId())
                .setAmount((double) 3000)
                .setDatetime(new Date())
                .setPaymentMethod(PaymentMethod.CARD)
                .setPaymentState(PaymentState.NOT_PAID)
                .setUserId(user.getId())
                .build();

        orders.setOrder(order);

        newOrder = Order.newBuilder()
                .setId(2)
                .setOrderList(orderList)
                .setOrderNum(12)
                .setOrderStatus(OrderStatus.DELIVERED)
                .setAddress(address)
                .setAddressId(address.getId())
                .setAmount((double) 3100)
                .setDatetime(new Date())
                .setPaymentMethod(PaymentMethod.CARD)
                .setPaymentState(PaymentState.NOT_PAID)
                .setUserId(user.getId())
                .build();

        list = new ArrayList<>();
        list.add(order);
        list.add(newOrder);
    }

    @Test
    public void testGetOrderById() {
        given(orderDAO.getOrderById(order.getId())).willReturn(order);
        Order expected = orderService.getOrderById(order.getId());
        assertNotNull(expected);
    }

    @Test
    public void testGetOrdersByUserId() {
        given(orderDAO.getOrdersByUserId(user.getId())).willReturn(list);
        List<Order> expected = orderService.getOrdersByUserId(user.getId());
        assertEquals(expected, list);
    }

    @Test
    public void testGetAllOrders() {
        given(orderDAO.getAllOrders()).willReturn(list);
        List<Order> expected = orderService.getAllOrders();
        assertEquals(expected, list);
    }

    @Test
    public void testGetMonthlyIncome() {
        Double expected = orderService.getMonthlyIncome();
        assertNotNull(expected);
    }

    @Test
    public void testGetDailyIncome() {
        Double expected = orderService.getDailyIncome();
        assertNotNull(expected);
    }

    @Test
    public void testGetTenProducts() {
        Map<Product, Integer> expected = orderService.getTenProducts();
        assertNotNull(expected);
    }

    @Test
    public void testGetTenUsers() {
        Map<User, Integer> expected = orderService.getTenUsers();
        assertNotNull(expected);
    }

}
