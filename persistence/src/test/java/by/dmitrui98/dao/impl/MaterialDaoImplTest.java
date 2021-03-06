package by.dmitrui98.dao.impl;

import by.dmitrui98.dao.AdminDao;
import by.dmitrui98.dao.BaseDaoImplTest;
import by.dmitrui98.dao.MaterialDao;
import by.dmitrui98.entity.Admin;
import by.dmitrui98.entity.Material;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by Администратор on 02.06.2017.
 */
public class MaterialDaoImplTest extends BaseDaoImplTest {
    @Autowired
    private MaterialDao materialDao;

    @Autowired
    private AdminDao adminDao;

    @Test
    public void addOrUpdate() throws Exception {
        Material material = createTestMaterial();
        Long expectedId = 1L;

        Material result = materialDao.addOrUpdate(material);

        assertEquals(expectedId, result.getMaterialId());
        assertEquals(material.getName(), result.getName());
    }

    @Test
    public void delete() throws Exception {
        Material material = createTestMaterial();
        Material result = materialDao.addOrUpdate(material);
        Long id = result.getMaterialId();
        materialDao.remove(id);
        result = materialDao.getById(id);
        assertNull(result);
    }

    @Test
    public void findAll() throws Exception {
        Material material = createTestMaterial();
        Material material1 = new Material("name", admin);

        materialDao.addOrUpdate(material);
        materialDao.addOrUpdate(material1);

        List<Material> resultList = materialDao.findAll();

        assertEquals(2, resultList.size());
    }



    @Test
    public void getByName() throws Exception {
        Material material = createTestMaterial();
        materialDao.addOrUpdate(material);
        Material result = materialDao.getByName(material.getName());
        assertEquals(material.getName(), result.getName());
    }

    private Material createTestMaterial() {
        Material material = new Material("testName", admin);
        return material;
    }

    private Admin admin;
    @Before
    public void before() {
        System.out.println("**********BEFORE**********");
        admin = adminDao.addOrUpdate(new Admin("login", "email", "password"));
    }

    @After
    public void clearTable() {
        System.out.println("**********AFTER**********");

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.createNativeQuery("DELETE FROM material").executeUpdate();
        session.createNativeQuery("ALTER TABLE material ALTER COLUMN material_id RESTART WITH 1").executeUpdate();

        session.createNativeQuery("DELETE FROM admin").executeUpdate();
        session.createNativeQuery("ALTER TABLE admin ALTER COLUMN admin_id RESTART WITH 1").executeUpdate();
        admin = null;

        session.getTransaction().commit();
        session.close();
    }

}