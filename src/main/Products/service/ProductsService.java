package service;

import module.Products;
import org.springframework.stereotype.Service;
import repository.ProductDAO;

import java.util.List;

@Service
public class ProductsService {

    private final ProductDAO productDAO;

    public ProductsService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }


    @Override
    public List<Products> showId(Long id) {
        return productDAO.showId(id);
    }

    @Override
    public void deleteId(Long id) {
       productDAO.deleteID(id);
    }

    @Override
    public Products saveOrUpdate(Products product) {
        return productDAO.saveOrUpdate(products);
    }
}
