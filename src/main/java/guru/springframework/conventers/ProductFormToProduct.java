package guru.springframework.conventers;

import guru.springframework.commands.ProductForm;
import guru.springframework.domain.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductFormToProduct implements Converter<ProductForm, Product> {


    @Override
    public Product convert(ProductForm productForm) {

        Product product = new Product();
        product.setId(productForm.getProductId());
        product.setVersion(productForm.getVersion());
        product.setPrice(productForm.getPrice());
        product.setDescription(productForm.getDescription());
        product.setImageUrl(productForm.getImageUrl());

        return product;
    }
}
