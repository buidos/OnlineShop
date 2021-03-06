package by.dmitrui98.service.dao;

import by.dmitrui98.entity.Product;

import java.util.List;

/**
 * Created by Администратор on 16.04.2017.
 */
public interface ProductService extends BaseService<Product, Long> {
    Product save(Product product);

    Product save(Product product, String imageDirectory, Long imageId);

    void setProductMaterials(Product product, String[] materialIds, String[] percents);
    List<Product> getElements(int from, int count);
    long getCount();
}
