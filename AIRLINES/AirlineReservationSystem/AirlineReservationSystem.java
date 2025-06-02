import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class Passenger {
    String name;
    String id;
    String flightNumber;
    String destination;

    public Passenger(String name, String id, String flightNumber, String destination) {
        this.name = name;
        this.id = id;
        this.flightNumber = flightNumber;
        this.destination = destination;
    }

    public String toString() {
        return "Passenger ID: " + id + ", Name: " + name + ", Flight No: " + flightNumber + ", Destination: " + destination;
    }
}

public class AirlineReservationSystem {
    JFrame frame;
    JTextField nameField, idField, flightField, destField;
    JTextArea outputArea;
    HashMap<String, Passenger> passengerMap = new HashMap<>();

    public AirlineReservationSystem() {
        frame = new JFrame("Airline Reservation System");
        frame.setSize(700, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));

        panel.add(new JLabel("Passenger Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Passenger ID:"));
        idField = new JTextField();
        panel.add(idField);

        panel.add(new JLabel("Flight Number:"));
        flightField = new JTextField();
        panel.add(flightField);

        panel.add(new JLabel("Destination:"));
        destField = new JTextField();
        panel.add(destField);

        JButton bookBtn = new JButton("Book Ticket");
        JButton viewBtn = new JButton("View All Reservations");
        JButton searchBtn = new JButton("Search by ID");

        panel.add(bookBtn);
        panel.add(viewBtn);
        panel.add(searchBtn);

        frame.add(panel, BorderLayout.NORTH);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        frame.add(new JScrollPane(outputArea), BorderLayout.CENTER);

        // Action Listeners
        bookBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String id = idField.getText().trim();
            String flight = flightField.getText().trim();
            String dest = destField.getText().trim();
            if (!name.isEmpty() && !id.isEmpty() && !flight.isEmpty() && !dest.isEmpty()) {
                Passenger p = new Passenger(name, id, flight, dest);
                passengerMap.put(id, p);
                outputArea.append("Ticket Booked: " + p + "\n");
            } else {
                outputArea.append("Please fill in all fields!\n");
            }
        });

        viewBtn.addActionListener(e -> {
            outputArea.setText("");
            for (Passenger p : passengerMap.values()) {
                outputArea.append(p.toString() + "\n");
            }
        });

        searchBtn.addActionListener(e -> {
            String id = idField.getText().trim();
            if (passengerMap.containsKey(id)) {
                outputArea.setText(passengerMap.get(id).toString());
            } else {
                outputArea.setText("Passenger not found.\n");
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AirlineReservationSystem::new);
    }
}

