import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

class QueueOfCustomers {
    private Queue<Customer> customers = new LinkedList<>();

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public Customer removeCustomer() {
        return customers.poll();
    }

    public boolean isEmpty() {
        return customers.isEmpty();
    }

    public Queue<Customer> getCustomers() {
        return customers;
    }
}
