package app;

import app.dao.UserDAOImpl;
import app.model.User;
import app.model.enums.UserRole;
import app.model.enums.UserStatus;
import app.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserDAOImpl userDAO;

    private static User user;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @BeforeEach
    public void setUp() throws ParseException {
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
    }

    @Test
    public void testFindUserById() {
        given(userDAO.findById(user.getId())).willReturn(user);
        User expected = userService.findById(user.getId());
        assertNotNull(expected);
    }

    @Test
    public void testFindUserByEmail() {
        given(userDAO.findByEmail(user.getEmail())).willReturn(user);
        User expected = userService.findByEmail(user.getEmail());
        assertNotNull(expected);
    }

    @Test
    public void testGetAllUsers() throws ParseException {
        List<User> list = new ArrayList<>();
        User newUser = new User();
        newUser.setId(2);
        newUser.setPassword("11111111");
        newUser.setEmail("viktor@mail.com");
        newUser.setLastName("Ivanov");
        newUser.setFirstName("Viktor");
        newUser.setPasswordConfirm("111111111");
        newUser.setStringDateOfBirth("1995-01-21");
        newUser.setDateOfBirth(sdf.parse(user.getStringDateOfBirth()));
        newUser.setRole(UserRole.USER);
        newUser.setStatus(UserStatus.ACTIVE);
        list.add(user);
        list.add(newUser);
        given(userDAO.findAll()).willReturn(list);
        List<User> expected = userService.allUsers();
        assertEquals(expected, list);
    }

    @Test
    public void testEditUser() {
        given(userDAO.edit(user)).willReturn(user);
        User expected = userService.edit(user);
        assertNotNull(expected);
        verify(userDAO).edit(any(User.class));
    }

}
