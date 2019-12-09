import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import model.Booking;
import view.BookingPanel;

public class App {

    private static JFrame frame;

    public App() {
        frame = new JFrame();
        frame.add(new BookingPanel());
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new App();
            }
        });
    }

}