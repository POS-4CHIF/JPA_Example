package app;

import db.CustomerDAO;
import model.Customer;


/**
 * @author Michael König
 */
public class App {

    public static void main(String[] args) {
        try (CustomerDAO dao = CustomerDAO.getINSTANCE()) {
            Customer c = new Customer("Otto", "Reichel", "otto.reichel@htlstp.ac.at");
            System.out.println("persist 1: " + dao.persist(c));
            System.out.println("getById 1:" + dao.getCustomerById(1));
            System.out.println("getById 2:" + dao.getCustomerById(2));
            System.out.println("persist 2: " + dao.persist(new Customer("Max", "Mustermann", "max@gmx.at")));
            dao.findAll().forEach(System.out::println);
        }
    }
}