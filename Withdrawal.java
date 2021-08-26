import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.sql.*;

class Withdrawal extends JFrame implements ActionListener {
    JTextField wField;
    JButton wButton;
    JLabel wl2;
    static String user_name = LoginPage.userName;

    Withdrawal() {
        setLayout(null);

        JLabel wl1 = new JLabel("WITHDRAWAL");
        wl1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 47));
        wl1.setBounds(400, 15, 350, 75);
        wl1.setForeground(Color.white);

        JLabel wl = new JLabel("ENTER AMOUNT ");
        wl.setFont(new Font("Tahoma", Font.PLAIN, 22));
        wl.setBounds(300, 170, 200, 60);

        wField = new JTextField();
        wField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        wField.setBounds(481, 170, 281, 60);

        wButton = new JButton("SUBMIT");
        wButton.setBounds(481, 300, 100, 50);
        wButton.addActionListener(this);

        wl2 = new JLabel();
        wl2.setFont(new Font("Tahoma", Font.PLAIN, 18));
        wl2.setBounds(450, 400, 300, 50);

        add(wl1);
        add(wl);
        add(wField);
        add(wButton);
        add(wl2);

        setVisible(true);
        setTitle("WITHDRAWAL");
        setSize(1000, 600);
        setResizable(false);
        getContentPane().setBackground(Color.lightGray);
        setDefaultCloseOperation(3);

    }

    public void actionPerformed(ActionEvent ae) {
        try {
            int withdrawalam = Integer.parseInt(wField.getText());

            Class.forName("org.postgresql.Driver");
            Connection con = (Connection) DriverManager.getConnection(
                    "jdbc:postgresql://arjuna.db.elephantsql.com:5432/gbgesjnp", "gbgesjnp",
                    "oeoPIH_GSeWRcCC6CBB_vQEOGvEnt9Lx");
            // PreparedStatement st = (PreparedStatement) con
            // .prepareStatement("select amount from deposit where username = ?;");
            PreparedStatement st = (PreparedStatement) con.prepareStatement("select * from deposit");

            // st.setString(1, user_name);
            ResultSet rs = st.executeQuery();

            int befoream = 0;

            while (rs.next()) {
                befoream = rs.getInt("amount");
            }

            int wafteram = befoream - withdrawalam;

            if (wafteram < 0) {
                wl2.setText("INSUFFICIENT AMOUNT :( ");
            }

            else {

                PreparedStatement st2 = (PreparedStatement) con
                        .prepareStatement("update deposit set amount = ? where username = ?;");
                st2.setInt(1, wafteram);
                st2.setString(2, user_name);
                st2.executeUpdate();

                JOptionPane.showMessageDialog(wButton, "AVILABLE BALANCE : " + wafteram);
                dispose();
                new SecondPage();

            }
        } catch (Exception ex) {

        }
    }

    public static void main(String[] args) {
        new Withdrawal();
    }
}
