package guru.springframework.services.reposervices;

import guru.springframework.commands.ProductForm;
import guru.springframework.conventers.ProductFormToProduct;
import guru.springframework.domain.Product;
import guru.springframework.repositories.ProductRepository;
import guru.springframework.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("springdatajpa")
public class ProductServiceRepoImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductFormToProduct productFormToProduct;

    @Override
    public List<?> listAll() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    @Override
    public Product getById(Integer id) {
        return productRepository.findOne(id);
    }

    @Override
    public Product saveOrUpdate(Product domainObject) {
        return productRepository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        productRepository.delete(id);
    }

    @Override
    public Product saveOrUpdate(ProductForm productForm) {
        Product newProduct = productFormToProduct.convert(productForm);
        return saveOrUpdate(newProduct);
    }
}
