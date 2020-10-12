import app.dao.ProductDAO;
import app.dao.ProductDAOImpl;
import org.junit.Before;
import org.junit.Test;

//@RunWith(SpringJUnit4ClassRunner.class)
public class TestDao {

    private ProductDAO productDAO;

    @Before
    public void init() {
        productDAO = new ProductDAOImpl();
    }

    @Test
    public void testDao() {
        productDAO.getProductById(1);
    }
}
