package by.dmitrui98.dao.implementation;

import by.dmitrui98.dao.MaterialDao;
import by.dmitrui98.dao.ProductDao;
import by.dmitrui98.entity.Category;
import by.dmitrui98.entity.Material;
import by.dmitrui98.entity.Product;
import by.dmitrui98.entity.ProductMaterial;
import by.dmitrui98.util.SessionUtil;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Администратор on 29.04.2017.
 */
@Repository("materialDao")
public class MaterialDaoImpl implements MaterialDao {
    private static final Logger logger = Logger.getLogger(MaterialDaoImpl.class);

    @Autowired
    private SessionUtil sessionUtil;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    @Qualifier("productDao")
    @Lazy
    private ProductDao productDao;


    @Override
    public Material addOrUpdate(Material material) {
        sessionUtil.openTransactionSession();

        Session session = sessionUtil.getSession();
        session.saveOrUpdate(material);

        sessionUtil.closeTransactionSession();

        return material;
    }

    @Override
    public boolean delete(Integer id) {

        try {
            sessionUtil.openTransactionSession();
            Session session = sessionUtil.getSession();
            Material material = (Material) session.get(Material.class, id);

            if (material.getProductMaterials() != null) {
                Iterator<ProductMaterial> iterator = material.getProductMaterials().iterator();
                while (iterator.hasNext()) {
                    Product product = iterator.next().getProduct();
                    session.delete(product);
                    removeAssosiations(product);
                }
            }

            session.delete(material);
            sessionUtil.closeTransactionSession();

            return true;
        } catch (Exception ex) {
            logger.error("Can not delete material with id " + id, ex);
            return false;
        }
    }

    private void removeAssosiations(Product product) {
        Category category = product.getCategory();
        Set<Product> products = category.getProducts();
        products.remove(product);
    }

    @Override
    public List<Material> findAll() {
        sessionUtil.openTransactionSession();
        Session session = sessionUtil.getSession();
        List<Material> result = session.createQuery("from Material").list();


        sessionUtil.closeTransactionSession();

        return result;
    }



    @Override
    public Material getById(Integer id) {
        sessionUtil.openTransactionSession();
        Session session = sessionUtil.getSession();
        Material material = (Material) session.get(Material.class, id);

        if (material != null)
            Hibernate.initialize(material.getProductMaterials());

        sessionUtil.closeTransactionSession();

        return material;
    }

    @Override
    public Material getByName(String name) {
        sessionUtil.openTransactionSession();
        Session session = sessionUtil.getSession();
        Query query = session.createQuery("FROM Material m where m.name=:name", Material.class);
        query.setParameter("name", name);
        List<Material> materials = ((List<Material>) query.getResultList());
        sessionUtil.closeTransactionSession();

        Material material = null;
        if (materials.size() > 0) {
            material = materials.get(0);
        }

        return material;
    }
}
