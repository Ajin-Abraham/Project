import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Balance extends JFrame implements ActionListener {
    static String user_name = LoginPage.userName;

    JLabel bl3;
    JButton bb1;

    public Balance() {
        setLayout(null);

        JLabel bl1 = new JLabel("BALANCE");
        bl1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 47));
        bl1.setBounds(400, 15, 350, 75);
        bl1.setForeground(Color.white);

        JLabel bl2 = new JLabel("YOUR BALANCE ");
        bl2.setFont(new Font("Tahoma", Font.PLAIN, 22));
        bl2.setBounds(300, 170, 200, 60);

        bl3 = new JLabel();
        bl3.setFont(new Font("Tahoma", Font.PLAIN, 32));
        bl3.setBounds(481, 170, 281, 60);

        bb1 = new JButton("OK");
        bb1.setBounds(481, 300, 100, 50);
        bb1.addActionListener(this);

        setVisible(true);
        getContentPane().setBackground(Color.lightGray);

        add(bl1);
        add(bl2);
        add(bl3);
        add(bb1);

        setSize(1000, 600);
        setTitle("BALANACE");

        bb1.addActionListener(this);
        try {

            Class.forName("org.postgresql.Driver");
            Connection con = (Connection) DriverManager.getConnection(
                    "jdbc:postgresql://arjuna.db.elephantsql.com:5432/gbgesjnp", "gbgesjnp",
                    "oeoPIH_GSeWRcCC6CBB_vQEOGvEnt9Lx");
            PreparedStatement st = (PreparedStatement) con
                    .prepareStatement("select amount from deposit where username = ?;");

            st.setString(1, user_name);
            ResultSet rs = st.executeQuery();

            int amt = 0;

            while (rs.next()) {

                amt = rs.getInt("amount");
            }

            bl3.setText(String.valueOf(amt));

        } catch (Exception ex) {

        }
    }

    public void actionPerformed(ActionEvent ae) {
        dispose();
        new SecondPage();
    }

    public static void main(String[] args) {
        new Balance();

    }
}
