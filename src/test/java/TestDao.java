import app.dao.ProductDAO;
import app.dao.ProductDAOImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionFactoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

//@RunWith(SpringJUnit4ClassRunner.class)
public class TestDao {

    private ProductDAO productDAO;

    @Before
    public void init() {
        productDAO = new ProductDAOImpl();
    }

    @Test
    public void testDao() {
        productDAO.getById(1);
    }
}