package model;

import java.util.ArrayList;
import javax.swing.JOptionPane;

import model.Passenger;

public class Flight {

    private static final String source = "Pune";
    private static final String destination = "Delhi";

    private static final int maxBookings = 10;

    private ArrayList<Passenger> passengers;

    public Flight() {
        passengers = new ArrayList<>();
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public void addPassenger(Passenger p) {

        if(passengers.size() < maxBookings) {
            passengers.add(p);
        } else {
            JOptionPane.showMessageDialog(null, "Bookings full!", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void removePassenger(int passengerNumber) {

        if(passengers.size() > 0) {
            passengers.remove(passengerNumber);
        } else {
            JOptionPane.showMessageDialog(null, "No passengers!", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

}