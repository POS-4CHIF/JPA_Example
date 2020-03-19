package db;

import java.util.List;
import model.Customer;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(OrderAnnotation.class)
public class CustomerDAOTest {
    private static CustomerDAO dao;

    @BeforeAll
    static void init() throws ClassNotFoundException {
        dao = CustomerDAO.getINSTANCE();
    }

    @AfterAll
    static void tearDown() {
        dao.close();
    }

    @Test
    @Order(1)
    void testInsertCustomer() {
        Customer c = new Customer("Otto", "Reichel", "otto.reichel@htlstp.ac.at");
        boolean result = dao.persist(c);
        assertTrue(result);
        assertEquals(1, c.getId().intValue());
    }

    @Test
    @Order(2)
    void testInsertCustomer_hasExistingId() {
        Customer c = new Customer("Max", "Mustermann", "max@gmx.com");
        boolean result = dao.persist(c);
        assertTrue(result);
        assertEquals(2, c.getId().intValue());
        result = dao.persist(c); // persist again, has id 2
        assertFalse(result);
    }

    @Test
    @Order(3)
    void getCustomerById_noId() {
        Optional<Customer> c = dao.getCustomerById(100);
        assertTrue(c.isEmpty());
    }

    @Test
    @Order(4)
    void findAll() {
        List<Customer> customers = dao.findAll();
        assertEquals(2, customers.size());
    }
}