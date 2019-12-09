package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import model.Booking;

@SuppressWarnings("serial")
public class BookingPanel extends JPanel {

    private static JTextField tfName;
    private static JTextField tfAge;
    private static JTextField tfMobile;
    private static JTextField tfEmail;
    private static ButtonGroup bgGender;
    private static JRadioButton rbMale;
    private static JRadioButton rbFemale;
    private static JComboBox<String> cbBloodGroup;

    private static JComboBox<String> cbFlights;

    private static JButton btSubmit;
    private static JButton btDelete;

    private static GridBagLayout layout;
    private static GridBagConstraints gbc;

    private static Booking bookings;

    public BookingPanel() {
        super();
        bookings = new Booking();
        initComponents();
    }

    private void initComponents() {

        tfName = new JTextField(20);
        tfAge = new JTextField(5);
        tfMobile = new JTextField(10);
        tfEmail = new JTextField(20);

        bgGender = new ButtonGroup();
        rbMale = new JRadioButton("Male");
        rbFemale = new JRadioButton("Female");

        bgGender.add(rbMale);
        bgGender.add(rbFemale);

        String bloodGroups[] = {
            "O+", "O-", "A+", "A-", "B+", "B-", "AB+", "AB-"
        };

        String flights[] = {
            "Flight 1", "Flight 2", "Flight 3"
        };

        cbBloodGroup = new JComboBox<>(bloodGroups);
        cbFlights = new JComboBox<>(flights);

        btSubmit = new JButton("Submit");
        btDelete = new JButton("Delete");

        layout = new GridBagLayout();
        gbc = new GridBagConstraints();

        setLayout(layout);

        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;

        add(new JLabel("Select Flight"), gbc);

        gbc.gridy++;

        add(new JLabel("Name"), gbc);

        gbc.gridy++;

        add(new JLabel("Age"), gbc);

        gbc.gridy++;

        add(new JLabel("Mobile"), gbc);

        gbc.gridy++;

        add(new JLabel("Email"), gbc);

        gbc.gridy++;

        add(new JLabel("Blood Group"), gbc);

        gbc.gridy++;

        add(new JLabel("Gender"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;

        add(cbFlights, gbc);

        gbc.gridy++;

        add(tfName, gbc);

        gbc.gridy++;

        add(tfAge, gbc);

        gbc.gridy++;

        add(tfMobile, gbc);

        gbc.gridy++;

        add(tfEmail, gbc);

        gbc.gridy++;

        add(cbBloodGroup, gbc);

        gbc.gridy++;


        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new FlowLayout());
        radioPanel.add(rbMale, gbc);
        radioPanel.add(rbFemale, gbc);

        add(radioPanel, gbc);

        gbc.gridx = 1;

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 3;

        add(btSubmit, gbc);

        btSubmit.addActionListener(new SubmitListener());

        gbc.gridy++;
        gbc.gridwidth = 3;
        add(btDelete, gbc);

    }

    class SubmitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = tfName.getText();
            String email = tfEmail.getText();
            String mobile = tfMobile.getText();
            int age = Integer.parseInt(tfAge.getText());
            String bloodGroup = cbBloodGroup.getSelectedItem().toString();
            int flightNumber = cbFlights.getSelectedIndex();
            char gender = 'M';
            if(bgGender.getSelection() == rbFemale) {
                gender = 'F';
            }
            bookings.addBooking(flightNumber, name, gender, age, bloodGroup, mobile, email);
        }
    }
}