package app;

import app.dao.AddressDAOImpl;
import app.dao.OrderDAOImpl;
import app.dao.ProductDAOImpl;
import app.dao.UserDAOImpl;
import app.model.*;
import app.model.cart.Cart;
import app.model.cart.CartLine;
import app.model.cart.ProductDTO;
import app.model.enums.*;
import app.service.OrderServiceImpl;
import app.util.AppUtils;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

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
    private static Cart cart;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @BeforeEach
    public void setUp() throws ParseException {
        OrderList orders = new OrderList(1, 1000, 2, 2000, order, 1);
        List<OrderList> orderList = new ArrayList<>();
        orderList.add(orders);
        user = new User();
        user.setId(1);
        user.setPassword("11111111");
        user.setEmail("mail@mail.com");
        user.setLastName("Bannikov");
        user.setFirstName("Egor");
        user.setPasswordConfirm("111111111");
        user.setStringDateOfBirth("1997-01-21");
        user.setDateOfBirth(sdf.parse(user.getStringDateOfBirth()));
        user.setRole(UserRole.USER);
        user.setStatus(UserStatus.ACTIVE);
        address = new Address();
        address.setCountry("Russia");
        address.setCity("Moscow");
        address.setStreet("Street");
        address.setBuilding(100);
        address.setIndex(454643);
        address.setRoom(10);
        address.setUserId(1);
        address.setId(6);
        order = new Order();
        order.setDatetime(new Date());
        order.setAddress(address);
        order.setAddressId(address.getId());
        order.setPaymentMethod(PaymentMethod.CARD);
        order.setDeliveryMethod(DeliveryMethod.DELIVERY);
        order.setPaymentState(PaymentState.NOT_PAID);
        order.setOrderStatus(OrderStatus.DELIVERED);
        order.setId(71);
        order.setUserId(user.getId());
        order.setAmount(3000);
        order.setOrderList(orderList);
        orders.setOrder(order);
        newOrder = new Order();
        newOrder.setDatetime(new Date());
        newOrder.setAddress(address);
        newOrder.setAddressId(address.getId());
        newOrder.setPaymentMethod(PaymentMethod.CARD);
        newOrder.setDeliveryMethod(DeliveryMethod.DELIVERY);
        newOrder.setPaymentState(PaymentState.NOT_PAID);
        newOrder.setOrderStatus(OrderStatus.DELIVERED);
        newOrder.setId(2);
        newOrder.setUserId(user.getId());
        newOrder.setAmount(3100);
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1);
        productDTO.setName("name");
        productDTO.setQuantityInStock(10);
        productDTO.setColor("blue");
        productDTO.setSize(34);
        productDTO.setPrice(1000);
        productDTO.setBrand("GJ");
        CartLine cartLine = new CartLine( productDTO, 1);
        List<CartLine> cartLines = new ArrayList<>();
        cartLines.add(cartLine);
        cart = new Cart();
        cart.setCartLines(cartLines);
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

//    @Test
//    public void testAddOrder() {
//        MockHttpServletRequest request = new MockHttpServletRequest();
//        MockHttpServletResponse response = new MockHttpServletResponse();
//        AppUtils.setUserIdInSession(request, user.getId());
//
//        given(orderDAO.save(order.getDeliveryMethod(), order.getPaymentMethod(), order.getAddress(), cart, request))
//                .willReturn(order);
//        Order expected = orderService.save(order.getDeliveryMethod(), order.getPaymentMethod(),
//                order.getAddressId(), request, response);
//        assertEquals(expected, order);
//    }

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
