package service;

import enums.ProductStatus;
import exception.ProductException;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import model.Product;

import java.util.List;

@Dependent
public class ProductService {

    @Inject
    EntityManager em;

    @Transactional
    public Product createProduct(Product product) throws ProductException {

        List<Product> products = getAllProducts();

        if (products.contains(product)) {
            throw new ProductException(ProductStatus.EXISTS.getLabel());
        }

        return em.merge(product);
    }

    @Transactional
    public List<Product> getAllProducts() {
        return em.createNamedQuery(Product.GET_ALL_PRODUCTS, Product.class).getResultList();
    }
}
