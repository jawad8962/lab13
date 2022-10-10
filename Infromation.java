import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Infromation {
    JFrame f;

    Infromation(String name, String roll, String batch, String section, String Gender, String Qualification,
            String Address, String Country) {
        f = new JFrame();
        // labels and textfields
        JLabel l1 = new JLabel("Name");
        JLabel l2 = new JLabel("Roll no");
        JLabel l3 = new JLabel("Batch");
        JLabel l4 = new JLabel("Section");
        JLabel l5 = new JLabel("Gender");
        JLabel l6 = new JLabel("Qualification");
        JLabel l7 = new JLabel("Address");
        JLabel l8 = new JLabel("Country");
        JTextField t1 = new JTextField();
        JTextField t2 = new JTextField();
        JTextField t3 = new JTextField();
        JTextField t4 = new JTextField();
        JTextField t5 = new JTextField();
        JTextField t6 = new JTextField();
        JTextField t7 = new JTextField();
        JTextField t8 = new JTextField();

        // textfiled here
        // set the vlaues here
        t1.setText(name);
        t2.setText(roll);
        t3.setText(batch);
        t4.setText(section);
        t5.setText(Gender);
        t6.setText(Qualification);
        t7.setText(Address);
        t8.setText(Country);
        t1.setEditable(false); // unable to write in fields
        t2.setEditable(false);
        t3.setEditable(false);
        t4.setEditable(false);
        t5.setEditable(false);
        t6.setEditable(false);
        t7.setEditable(false);
        t8.setEditable(false);
        l1.setBounds(150, 50, 100, 30);
        l2.setBounds(150, 100, 100, 30);
        l3.setBounds(150, 150, 100, 30);
        l4.setBounds(150, 200, 100, 30);
        l5.setBounds(150, 250, 100, 30);
        l6.setBounds(150, 300, 100, 30);
        l7.setBounds(150, 400, 100, 30);
        l8.setBounds(150, 480, 100, 30);
        t1.setBounds(300, 50, 150, 30);
        t2.setBounds(300, 100, 150, 30);
        t3.setBounds(300, 150, 150, 30);
        t4.setBounds(300, 200, 150, 30);
        t5.setBounds(300, 250, 150, 30);
        t6.setBounds(300, 300, 150, 30);
        t7.setBounds(300, 400, 150, 30);
        t8.setBounds(300, 480, 150, 30);
        f.add(l1);
        f.add(l2);
        f.add(l3);
        f.add(l4);
        f.add(l5);
        f.add(l6);
        f.add(l7);
        f.add(l8);
        f.add(t1);
        f.add(t2);
        f.add(t3);
        f.add(t4);
        f.add(t5);
        f.add(t6);
        f.add(t7);
        f.add(t8);

        f.setSize(500, 600);
        f.getContentPane().setBackground(Color.RED);
        f.setLayout(null);
        f.setVisible(true);

    }
}
