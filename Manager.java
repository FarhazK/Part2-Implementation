import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

class Manager {
    private QueueOfCustomers customerQueue = new QueueOfCustomers();
    private ParcelMap parcelMap = new ParcelMap();

    public void initializeData(String parcelFile, String customerFile) throws IOException {
        // Load parcels
        try (BufferedReader br = new BufferedReader(new FileReader(parcelFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Parcel parcel = new Parcel(data[0], data[3], Double.parseDouble(data[2]), Integer.parseInt(data[1]), "Waiting");
                parcelMap.addParcel(data[0], parcel);
            }
        }

        // Load customers - UPDATED CODE
        try (BufferedReader br = new BufferedReader(new FileReader(customerFile))) {
            String line;
            int seqNo = 1; // Auto-generate sequence number starting from 1
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                // Check if data has 2 columns (name and parcel ID)
                if (data.length == 2) {
                    String name = data[0]; // Customer name
                    String parcelID = data[1]; // Parcel ID

                    // Create customer with auto-generated sequence number
                    Customer customer = new Customer(seqNo++, name, parcelID);
                    customerQueue.addCustomer(customer);
                } else {
                    // Log or print error for invalid data
                    System.err.println("Invalid customer data: " + line);
                }
            }
        }
    }

    public void generateReport(String reportFile) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(reportFile))) {
            for (Parcel parcel : parcelMap.getParcels()) {
                writer.write(parcel.toString());
                writer.newLine();
            }
        }
    }

    public void processCustomers() {
        Worker worker = new Worker();
        while (!customerQueue.isEmpty()) {
            Customer customer = customerQueue.removeCustomer();
            worker.processCustomer(customer, parcelMap);
        }
    }

    public ParcelMap getParcelMap() { return parcelMap; }
    public QueueOfCustomers getCustomerQueue() { return customerQueue; }
}
