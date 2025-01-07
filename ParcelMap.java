import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

class ParcelMap {
    private Map<String, Parcel> parcels = new HashMap<>();

    public void addParcel(String id, Parcel parcel) {
        parcels.put(id, parcel);
    }

    public Parcel findParcel(String id) {
        return parcels.get(id);
    }

    public void removeParcel(String id) {
        parcels.remove(id);
    }

    public Collection<Parcel> getParcels() {
        return parcels.values();
    }
}