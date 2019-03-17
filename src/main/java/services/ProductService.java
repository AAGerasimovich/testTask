package services;

import dao.ProductDao;
import model.Product;


public class ProductService {

    private ProductDao productDao = new ProductDao();

    public void save(Product product) {
        productDao.create(product);
    }

    public Boolean contains(String product) {
        return productDao.contains(product);
    }
}
