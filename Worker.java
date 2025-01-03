import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
class Worker {
    public void processCustomer(Customer customer, ParcelMap parcelMap) {
        Parcel parcel = parcelMap.findParcel(customer.getParcelId());
        if (parcel != null) {
            double fee = parcel.calculateFee();
            parcel.setStatus("Collected");
            Log.getInstance().addEntry("Processed: " + customer.getName() + " collected parcel " + parcel.getId() + " for fee: $" + fee);
        }
    }
}
