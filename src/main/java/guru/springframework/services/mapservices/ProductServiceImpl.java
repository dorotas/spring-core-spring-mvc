package guru.springframework.services.mapservices;

import guru.springframework.commands.ProductForm;
import guru.springframework.conventers.ProductFormToProduct;
import guru.springframework.domain.DomainObject;
import guru.springframework.domain.Product;
import guru.springframework.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jt on 11/6/15.
 */
@Service
@Profile("map")
public class ProductServiceImpl extends AbstractMapService implements ProductService {

    @Autowired
    private ProductFormToProduct productFormToProduct;

    @Override
    public List<DomainObject> listAll() {
        return super.listAll();
    }

    @Override
    public Product getById(Integer id) {
        return (Product) super.getById(id);
    }

    @Override
    public Product saveOrUpdate(Product domainObject) {
        return (Product) super.saveOrUpdate(domainObject);
    }

    @Override
    public Product saveOrUpdate(ProductForm productForm) {
        Product newProduct = productFormToProduct.convert(productForm);
        return saveOrUpdate(newProduct);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }

   }
