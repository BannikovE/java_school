package app.dao;

import app.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Repository
public class ScoreboardDAOImpl implements ScoreboardDAO{
    private SessionFactory sessionFactory;
    private ProductDAO productDAO;

//    private Map<Product, Integer> mapToScoreboard = new HashMap<>();

    @Autowired
    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Product> getMapToScoreboard() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createNativeQuery("select id from products " +
                " order by price desc limit 10");
        List<Integer> ids = query.getResultList();
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++){
            products.add(productDAO.getProductById(ids.get(i)));
        }
        return products;
    }

//    @Override
//    public void setMapToScoreboard(Map<Product, Integer> map) {
//        mapToScoreboard = map;
//    }

//    @SuppressWarnings("unchecked")
//    @Override
//    public Map<Product, Integer> getTopToScoreboard() {
//        Session session = sessionFactory.getCurrentSession();
//        Map<Product, Integer> tenProducts = new HashMap<>();
//        List<Integer> productIds;
//        List<Integer> amounts;
//        Query queryToFindProductIds = session.createNativeQuery("select id from products " +
//                " order by price desc limit 10");
//        Query queryToFindAmounts = session.createNativeQuery("select price from products " +
//                " order by price desc limit 10");
//        productIds = queryToFindProductIds.getResultList();
//        amounts = queryToFindAmounts.getResultList();
//        for (int i = 0; i < productIds.size(); i++){
//            tenProducts.put(productDAO.getProductById(productIds.get(i)), amounts.get(i));
//        }
//        return tenProducts;
//    }

}
