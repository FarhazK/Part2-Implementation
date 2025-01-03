import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

// Parcel Class
class Parcel {
    private String id;
    private String dimensions;
    private double weight;
    private int daysInDepot;
    private String status;

    public Parcel(String id, String dimensions, double weight, int daysInDepot, String status) {
        this.id = id;
        this.dimensions = dimensions;
        this.weight = weight;
        this.daysInDepot = daysInDepot;
        this.status = status;
    }

    public double calculateFee() {
        double fee = weight * 0.5 + daysInDepot * 0.2;
        if (id.startsWith("C")) fee *= 0.9;
        return fee;
    }

    public String getId() { return id; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String toString() {
        return id + ", " + dimensions + ", " + weight + "kg, Days: " + daysInDepot + ", Status: " + status;
    }
}