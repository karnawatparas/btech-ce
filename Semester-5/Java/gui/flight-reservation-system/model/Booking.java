package model;

import model.Flight;
import model.Passenger;

public class Booking {

    private Flight flights[];

    public Booking() {
        flights = new Flight[3];
        for(int i = 0; i < 3; i++) {
            flights[i] = new Flight();
        }
    }

    public void addBooking(int flightNumber, String n, char g, int a, String b, String m, String e) {
        Passenger p = new Passenger();
        p.setName(n);
        p.setGender(g);
        p.setAge(a);
        p.setBloodGroup(b);
        p.setMobileNumber(m);
        p.setEmailAddress(e);
        flights[flightNumber].addPassenger(p);
    }

    public void removeBooking(int flightNumber, int rowNumber) {
        flights[flightNumber].removePassenger(rowNumber);
    }

}