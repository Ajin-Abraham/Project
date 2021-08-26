import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Deposit extends JFrame implements ActionListener {

    JTextField dField;
    JButton dButton;
    JLabel dl2;
    static String user_name = LoginPage.userName;

    public Deposit() {
        setLayout(null);

        JLabel dl1 = new JLabel("DEPOSIT");
        dl1.setFont(new Font("Tahoma", Font.PLAIN, 47));
        dl1.setBounds(400, 15, 350, 75);
        dl1.setForeground(Color.BLACK);

        JLabel dl = new JLabel("ENTER AMOUNT TO DEPOSIT:");
        dl.setFont(new Font("Tahoma", Font.PLAIN, 22));
        dl.setBounds(150, 166, 350, 60);

        dField = new JTextField();
        dField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        dField.setBounds(481, 170, 281, 60);

        dButton = new JButton("SUBMIT");
        dButton.setBounds(450, 300, 100, 50);
        dButton.addActionListener(this);

        dl2 = new JLabel();
        dl2.setFont(new Font("Tahoma", Font.PLAIN, 18));
        dl2.setBounds(450, 400, 700, 50);
        dl2.setForeground(Color.yellow);

        add(dl1);
        add(dl);
        add(dField);
        add(dButton);
        add(dl2);

        setVisible(true);
        setSize(1000, 600);
        setTitle("DEPOSIT");
        getContentPane().setBackground(Color.lightGray);
        setResizable(false);
        setDefaultCloseOperation(3);

    }

    public void actionPerformed(ActionEvent ae) {
        try {
            int depositam = Integer.parseInt(dField.getText());

            Class.forName("org.postgresql.Driver");
            Connection con = (Connection) DriverManager.getConnection(
                    "jdbc:postgresql://arjuna.db.elephantsql.com:5432/gbgesjnp", "gbgesjnp",
                    "oeoPIH_GSeWRcCC6CBB_vQEOGvEnt9Lx");
            PreparedStatement st = (PreparedStatement) con
                    .prepareStatement("select amount from deposit where username = ?;");

            st.setString(1, user_name);
            ResultSet rs = st.executeQuery();

            int dbefoream = 0;

            while (rs.next()) {
                dbefoream = rs.getInt("Amount");
            }

            int dafteram = dbefoream + depositam;

            PreparedStatement st2 = (PreparedStatement) con
                    .prepareStatement("update deposit set amount = ? where username = ?;");
            st2.setInt(1, dafteram);
            st2.setString(2, user_name);
            st2.executeUpdate();

            JOptionPane.showMessageDialog(dButton, "AVILABLE BALANCE : " + dafteram);
            dispose();
            new SecondPage();
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        new Deposit();
    }
}
