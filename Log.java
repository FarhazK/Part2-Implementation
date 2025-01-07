import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

class Log {
    private static Log instance;
    private StringBuilder entries = new StringBuilder();

    private Log() {}

    public static Log getInstance() {
        if (instance == null) {
            instance = new Log();
        }
        return instance;
    }

    public void addEntry(String entry) {
        entries.append(entry).append("\n");
    }

    public void writeToFile(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(entries.toString());
        }
    }
}