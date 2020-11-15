package app;

import app.dao.AddressDAO;
import app.dao.AddressDAOImpl;
import app.model.Address;
import app.service.AddressServiceImpl;
import app.service.UserService;
import app.service.UserServiceImpl;
import net.bytebuddy.dynamic.DynamicType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

    @InjectMocks
    private AddressServiceImpl addressService;
    @Mock
    private AddressDAOImpl addressDAO;
    @Mock
    private UserServiceImpl userService;

    private static Address address;

    @BeforeEach
    public void setUp() {
        address = new Address();
        address.setCountry("Russia");
        address.setCity("Moscow");
        address.setStreet("Street");
        address.setBuilding(100);
        address.setIndex(454643);
        address.setRoom(10);
        address.setUserId(1);
        address.setId(6);
    }

    @Test
    public void testFindAddress() {
        given(addressDAO.findAddressById(address.getId())).willReturn(address);
        Address expected = addressService.findAddressById(address.getId());
        assertNotNull(expected);
    }

    @Test
    public void testEditAddress() {
        given(addressDAO.edit(address)).willReturn(address);
        Address expected = addressService.edit(address);
        assertNotNull(expected);
        verify(addressDAO).edit(any(Address.class));
    }

    @Test
    public void testAddAddress() {
        given(addressDAO.addAddress(address)).willReturn(address.getId());
        Integer idExpected = addressService.addAddress(address);
        assertNotNull(idExpected);
        verify(addressDAO).addAddress(any(Address.class));
    }
}
