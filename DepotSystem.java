import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
public class DepotSystem extends JFrame {
    private Manager manager = new Manager();
    private JTextArea displayArea;

    public DepotSystem() throws IOException {
        manager.initializeData("Parcels.csv", "Custs.csv");
        setTitle("Depot System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        displayArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane, BorderLayout.CENTER);

        JButton processButton = new JButton("Process Next Customer");
        processButton.addActionListener(e -> processCustomer());
        add(processButton, BorderLayout.SOUTH);

        displayData();
    }

    private void processCustomer() {
        if (!manager.getCustomerQueue().isEmpty()) {
            Customer customer = manager.getCustomerQueue().removeCustomer();
            new Worker().processCustomer(customer, manager.getParcelMap());
            displayData();
        } else {
            JOptionPane.showMessageDialog(this, "No more customers to process.");
        }
    }

    private void displayData() {
        displayArea.setText("");
        for (Parcel parcel : manager.getParcelMap().getParcels()) {
            displayArea.append(parcel.toString() + "\n");
        }
    }

    public static void main(String[] args) throws IOException {
        SwingUtilities.invokeLater(() -> {
            try {
                new DepotSystem().setVisible(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
