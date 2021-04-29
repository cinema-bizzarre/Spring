import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ArrayList;

@Component
public class ProductRepository {
    private List<Product> productsList;

    public void init(List<Product> productsList) {
        this.productsList = new ArrayList<Product>();
        productsList.add(new Product(557889L, "Dress", 3000));
        productsList.add(new Product(890853L, "Trousers", 1500));
        productsList.add(new Product(22345L, "Blouse", 2600));
    }

    public Product findProductById(int id) {
        for (Product product : productsList) {
            if (product.getId() == id){
                return product;
            }
        }
        return null;
    }

    public List<Product> findAll(){
        return productsList;
    }

    public Product addProduct(Product product){
        productsList.add(product);
        return product;
    }
}
