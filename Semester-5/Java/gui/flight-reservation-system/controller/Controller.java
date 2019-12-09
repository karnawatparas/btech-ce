package controller;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;
import java.io.FileNotFoundException;
import javax.swing.JOptionPane;
import java.util.ArrayList;

import model.Passenger;

public class Controller {

    private static String fileNames[];
    private static int count[];

    public Controller() {

        fileNames = new String[3];
        count = new int[3];
        for (int i = 0; i < 3; i++) {
            fileNames[i] = "Flight-" + String.valueOf(i);
            count[i] = 0;

            File f = new File(fileNames[i]);

            try {
                BufferedReader br = new BufferedReader(new FileReader(f));
                String line = br.readLine();
                while (line != null) {
                    count[i]++;
                    line = br.readLine();
                }
                br.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void addBooking(int flightNumber, String n, char g, int a, String b, String m, String e) {

        if (count[flightNumber] == 10) {
            JOptionPane.showMessageDialog(null, "Bookings full!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {

            File output = new File(fileNames[flightNumber]);
            BufferedWriter writer = new BufferedWriter(new FileWriter(output, true));

            String line = n + "|" + String.valueOf(g) + "|" + String.valueOf(a) + "|";
            line = line + b + "|" + m + "|" + e;

            writer.write(line);
            writer.newLine();

            writer.close();

            count[flightNumber]++;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public ArrayList<Passenger> fetchAllBookings(int flightNumber) {

        ArrayList<Passenger> passengers = null;

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileNames[flightNumber]));
            passengers = new ArrayList<>();
            String line = br.readLine();

            while (line != null) {
                Passenger passenger = new Passenger();
                Vector<String> v = new Vector<>();
                StringTokenizer tokenizer = new StringTokenizer(line, "|");
                while (tokenizer.hasMoreTokens()) {
                    v.add(tokenizer.nextToken());
                }

                passenger.setName(v.get(0));
                passenger.setGender(v.get(1).charAt(0));
                passenger.setAge(Integer.parseInt(v.get(2)));
                passenger.setBloodGroup(v.get(3));
                passenger.setMobileNumber(v.get(4));
                passenger.setEmailAddress(v.get(5));

                passengers.add(passenger);

                line = br.readLine();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return passengers;
    }

    public void removeBooking(int flightNumber, int passengerNumber) {

        try {

            BufferedReader reader = new BufferedReader(new FileReader(new File(fileNames[flightNumber])));
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("temp.txt")));

            int counter = 0;
            String line;

            while (counter < passengerNumber) {
                line = reader.readLine();
                writer.write(line);
                writer.newLine();
                counter++;
            }

            line = reader.readLine();

            line = reader.readLine();
            while (line != null) {
                writer.write(line);
                writer.newLine();
                line = reader.readLine();
            }

            reader.close();
            writer.close();

            File original = new File(fileNames[flightNumber]);
            File temporary = new File("temp.txt");

            original.delete();
            temporary.renameTo(original);

            count[flightNumber]--;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public int getCountOfPassengers(int flight) {
        return count[flight];
    }

}