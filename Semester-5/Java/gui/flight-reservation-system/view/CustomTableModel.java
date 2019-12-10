package view;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

import controller.Controller;
import model.Passenger;

@SuppressWarnings("serial")
public class CustomTableModel extends AbstractTableModel {

    private static Controller controller;
    private static ArrayList<Passenger> passengers;

    private static final String[] columns = { "Name", "Gender", "Age", "Blood Group", "Mobile", "Email" };

    public CustomTableModel(Controller controller, int flightNumber) {
        super();
        CustomTableModel.controller = controller;
        passengers = controller.fetchAllBookings(flightNumber);
    }

    @Override
    public int getRowCount() {
        return passengers.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columns[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
        case 0:
            return passengers.get(rowIndex).getName();
        case 1:
            return String.valueOf(passengers.get(rowIndex).getGender());
        case 2:
            return String.valueOf(passengers.get(rowIndex).getAge());
        case 3:
            return passengers.get(rowIndex).getBloodGroup();
        case 4:
            return passengers.get(rowIndex).getMobileNumber();
        case 5:
            return passengers.get(rowIndex).getEmailAddress();
        default:
            return "";
        }
    }

}