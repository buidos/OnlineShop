package by.dmitrui98.dao.implementation;

import by.dmitrui98.dao.BaseDaoImplTest;
import by.dmitrui98.dao.UserDao;
import by.dmitrui98.entity.User;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Администратор on 30.05.2017.
 */
@Ignore
public class UserDaoImplTest extends BaseDaoImplTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void addOrUpdate() throws Exception {
        User user = createTestUser();
        long expectedId = 1;

        long id = userDao.addOrUpdate(user);
        User result = userDao.getById(id);

        assertEquals(expectedId, result.getUserId());
        assertEquals(user.getLogin(), result.getLogin());
        assertEquals(user.getPassword(), result.getPassword());
        assertEquals(user.getEmail(), result.getEmail());
        assertEquals(user.getFname(), result.getFname());
    }

    @Test
    public void delete() throws Exception {
        User user = createTestUser();
        long id = userDao.addOrUpdate(user);
        userDao.delete(id);
        User result = userDao.getById(id);
        assertNull(result);
    }

    @Test
    public void findAll() throws Exception {
        User user = createTestUser();
        User user1 = new User("login", "email", "password", "ваня");
        user1.setCreatedAt(new Date());
        user1.setUpdatedAt(new Date());

        userDao.addOrUpdate(user);
        userDao.addOrUpdate(user1);

        List<User> resultList = userDao.findAll();

        assertEquals(2, resultList.size());
    }



    @Test
    public void getByName() throws Exception {
        User user = createTestUser();
        userDao.addOrUpdate(user);
        User result = userDao.getByName(user.getLogin());
        assertEquals(user.getLogin(), result.getLogin());
        assertEquals(user.getEmail(), result.getEmail());
    }

    @Test
    public void getByEmail() throws Exception {
        User user = createTestUser();
        userDao.addOrUpdate(user);
        User result = userDao.getByEmail(user.getEmail());
        assertEquals(user.getLogin(), result.getLogin());
        assertEquals(user.getEmail(), result.getEmail());
    }

    private User createTestUser() {
        User user = new User();
        user.setLogin("testUserLogin");
        user.setPassword("testUserPassword");
        user.setEmail("testUserEmail");
        user.setFname("петя");
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());

        return user;
    }

    @Before
    public void before() {
        System.out.println("**********BEFORE**********");
    }

    @After
    public void clearTable() {
        System.out.println("**********AFTER**********");
        sessionUtil.openTransactionSession();
        Session session = sessionUtil.getSession();

        session.createNativeQuery("DELETE FROM user").executeUpdate();
        session.createNativeQuery("ALTER TABLE user AUTO_INCREMENT=1").executeUpdate();

        sessionUtil.closeTransactionSession();
    }

}