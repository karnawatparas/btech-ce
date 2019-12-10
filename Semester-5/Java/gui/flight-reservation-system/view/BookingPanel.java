package view;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import java.awt.*;
import java.awt.event.*;

import controller.Controller;
import view.CustomTableModel;

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

    private static JLabel count;

    private static JComboBox<String> cbFlights;

    private static JButton btSubmit;
    private static JButton btDelete;

    private static JTable table;

    private static BorderLayout mainLayout;
    private static GridBagLayout layout;
    private static GridBagConstraints gbc;

    private static Controller bookings;

    public BookingPanel() {
        super();
        bookings = new Controller();
        initComponents();
    }

    private void initComponents() {

        tfName = new JTextField(20);
        tfAge = new JTextField(5);
        tfMobile = new JTextField(10);
        tfEmail = new JTextField(20);

        count = new JLabel("");

        bgGender = new ButtonGroup();
        rbMale = new JRadioButton("Male");
        rbFemale = new JRadioButton("Female");
        rbMale.setActionCommand("Male");
        rbFemale.setActionCommand("Female");

        bgGender.add(rbMale);
        bgGender.add(rbFemale);

        String bloodGroups[] = { "O+", "O-", "A+", "A-", "B+", "B-", "AB+", "AB-" };

        String flights[] = { "Flight 1", "Flight 2", "Flight 3" };

        cbBloodGroup = new JComboBox<>(bloodGroups);
        cbFlights = new JComboBox<>(flights);

        btSubmit = new JButton("Submit");
        btDelete = new JButton("Delete");

        layout = new GridBagLayout();
        gbc = new GridBagConstraints();

        table = new JTable();
        table.addMouseListener(new TableListener());

        mainLayout = new BorderLayout();

        setLayout(mainLayout);

        JPanel mainPanel = new JPanel(layout);

        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;

        mainPanel.add(new JLabel("Select Flight"), gbc);

        gbc.gridy++;

        mainPanel.add(new JLabel("Name"), gbc);

        gbc.gridy++;

        mainPanel.add(new JLabel("Age"), gbc);

        gbc.gridy++;

        mainPanel.add(new JLabel("Mobile"), gbc);

        gbc.gridy++;

        mainPanel.add(new JLabel("Email"), gbc);

        gbc.gridy++;

        mainPanel.add(new JLabel("Blood Group"), gbc);

        gbc.gridy++;

        mainPanel.add(new JLabel("Gender"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;

        JPanel flightsPanel = new JPanel();
        flightsPanel.setLayout(new FlowLayout());
        flightsPanel.add(cbFlights);
        flightsPanel.add(count);

        count.setText(String.valueOf(bookings.getCountOfPassengers(cbFlights.getSelectedIndex())));

        table.setModel((TableModel) new CustomTableModel(bookings, cbFlights.getSelectedIndex()));

        mainPanel.add(flightsPanel, gbc);

        cbFlights.addItemListener(new FlightListener());

        gbc.gridy++;

        mainPanel.add(tfName, gbc);

        gbc.gridy++;

        mainPanel.add(tfAge, gbc);

        gbc.gridy++;

        mainPanel.add(tfMobile, gbc);

        gbc.gridy++;

        mainPanel.add(tfEmail, gbc);

        gbc.gridy++;

        mainPanel.add(cbBloodGroup, gbc);

        gbc.gridy++;

        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new FlowLayout());
        radioPanel.add(rbMale, gbc);
        radioPanel.add(rbFemale, gbc);
        rbMale.setSelected(true);

        mainPanel.add(radioPanel, gbc);

        gbc.gridx = 1;

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 3;

        mainPanel.add(btSubmit, gbc);

        btSubmit.addActionListener(new SubmitListener());

        gbc.gridy++;
        gbc.gridwidth = 3;
        mainPanel.add(btDelete, gbc);

        btDelete.setEnabled(false);
        btDelete.addActionListener(new DeleteListener());

        add(mainPanel, BorderLayout.CENTER);

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(layout);

        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        TableColumnModel model = table.getColumnModel();
        model.getColumn(0).setPreferredWidth(120);
        model.getColumn(1).setPreferredWidth(51);
        model.getColumn(2).setPreferredWidth(48);
        model.getColumn(3).setPreferredWidth(90);
        model.getColumn(4).setPreferredWidth(100);
        model.getColumn(5).setPreferredWidth(191);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 300));

        tablePanel.add(scrollPane, gbc);

        add(tablePanel, BorderLayout.EAST);
    }

    class SubmitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = tfName.getText();
            String email = tfEmail.getText();
            String mobile = tfMobile.getText();

            int age;
            try {
                age = Integer.parseInt(tfAge.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Enter a valid age", "Error!", 0);
                tfAge.requestFocus();
                return;
            }

            if (cbBloodGroup.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(null, "Please enter a blood group", "Error!", 0);
                cbBloodGroup.requestFocus();
                return;
            }

            String bloodGroup = cbBloodGroup.getSelectedItem().toString();
            int flightNumber = cbFlights.getSelectedIndex();
            char gender = 'M';
            if (bgGender.getSelection().getActionCommand().equals("Male")) {
                gender = 'M';
            } else {
                gender = 'F';
            }
            bookings.addBooking(flightNumber, name, gender, age, bloodGroup, mobile, email);
            count.setText(String.valueOf(bookings.getCountOfPassengers(cbFlights.getSelectedIndex())));
            table.setModel((TableModel) new CustomTableModel(bookings, cbFlights.getSelectedIndex()));
            tfName.setText("");
            tfEmail.setText("");
            tfMobile.setText("");
            tfAge.setText("");
            rbMale.setSelected(true);
            cbBloodGroup.setSelectedIndex(-1);
        }
    }

    class FlightListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            count.setText(String.valueOf(bookings.getCountOfPassengers(cbFlights.getSelectedIndex())));
            table.setModel((TableModel) new CustomTableModel(bookings, cbFlights.getSelectedIndex()));
        }
    }

    class TableListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            int selectedRow = table.getSelectedRow();
            tfName.setText((String) table.getValueAt(selectedRow, 0));
            if (table.getValueAt(selectedRow, 1).toString().equals("M")) {
                rbMale.setSelected(true);
            } else {
                rbFemale.setSelected(true);
            }
            tfAge.setText(String.valueOf(table.getValueAt(selectedRow, 2)));
            cbBloodGroup.setSelectedItem(table.getValueAt(selectedRow, 3));
            tfMobile.setText((String) table.getValueAt(selectedRow, 4));
            tfEmail.setText((String) table.getValueAt(selectedRow, 5));
            btSubmit.setEnabled(false);
            btDelete.setEnabled(true);
        }
    }

    class DeleteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = table.getSelectedRow();
            int flightNumber = cbFlights.getSelectedIndex();
            bookings.removeBooking(flightNumber, selectedRow);
            table.setModel((TableModel) new CustomTableModel(bookings, flightNumber));
            count.setText(String.valueOf(bookings.getCountOfPassengers(cbFlights.getSelectedIndex())));
            btSubmit.setEnabled(true);
            btDelete.setEnabled(false);
            table.clearSelection();
            tfName.setText("");
            tfEmail.setText("");
            tfMobile.setText("");
            tfAge.setText("");
            rbMale.setSelected(true);
            cbBloodGroup.setSelectedIndex(-1);
        }
    }

}