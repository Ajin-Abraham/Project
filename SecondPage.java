import javax.swing.*;

import java.awt.Color;
import java.awt.event.*;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

public class SecondPage extends JFrame implements ActionListener {
    JButton sb1, sb2, sb3, sb4;

    SecondPage() {

        sb1 = new JButton("WITHDRAWAL");
        sb1.setBounds(150, 100, 150, 100);
        sb2 = new JButton("DEPOSIT");
        sb2.setBounds(600, 100, 150, 100);
        sb3 = new JButton("BALANCE");
        sb3.setBounds(150, 400, 150, 100);
        sb4 = new JButton("EXIT");
        sb4.setBounds(600, 400, 150, 100);

        add(sb1);
        add(sb2);
        add(sb3);
        add(sb4);

        sb1.addActionListener(this);
        sb2.addActionListener(this);
        sb3.addActionListener(this);
        sb4.addActionListener(this);

        setLayout(null);
        setTitle("OPTIONS");
        setVisible(true);
        setSize(850, 1240);
        setResizable(false);
        setDefaultCloseOperation(3);

    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == sb1) {
            dispose();
            new Withdrawal();

        } else if (ae.getSource() == sb2) {
            dispose();
            new Deposit();
        }

        else if (ae.getSource() == sb3) {
            dispose();
            new Balance();
        } else if (ae.getSource() == sb4) {
            dispose();
            JOptionPane.showMessageDialog(sb4, "THANK YOU :)");
        }

    }

    public static void main(String[] args) {
        new SecondPage();
    }
}
