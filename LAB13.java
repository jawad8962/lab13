import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;

public class LAB13 {
    JFrame f; // object of te jframe like panel here
    Connection con = null;
    PreparedStatement pr;

    LAB13() {
        f = new JFrame("Student Registration form");
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
        // buttons
        JButton b1 = new JButton("Print");
        JButton b2 = new JButton("Save");
        JButton b3 = new JButton("Clear");
        JButton b4 = new JButton("Database");
        JButton b5 = new JButton("Fetch data");

        JRadioButton male = new JRadioButton("Male");
        JRadioButton female = new JRadioButton("Female");
        JCheckBox c1 = new JCheckBox("Matric");
        JCheckBox c2 = new JCheckBox("Intermediate");
        JCheckBox c3 = new JCheckBox("Graduate");
        JCheckBox c4 = new JCheckBox("Post Graduate");
        JTextArea a1 = new JTextArea();
        // JList list = new JList();
        String[] country = { "Pakistan", "India", "China" };
        JComboBox cb = new JComboBox(country);
        ButtonGroup group = new ButtonGroup();

        l1.setBounds(150, 50, 100, 30);
        l2.setBounds(150, 100, 100, 30);
        l3.setBounds(150, 150, 100, 30);
        l4.setBounds(150, 200, 100, 30);
        l5.setBounds(150, 250, 100, 30);
        l6.setBounds(150, 300, 100, 30);
        l7.setBounds(150, 400, 100, 30);
        l8.setBounds(150, 480, 100, 30);
        t1.setBounds(250, 50, 150, 30);
        t2.setBounds(250, 100, 150, 30);
        t3.setBounds(250, 150, 150, 30);
        t4.setBounds(250, 200, 150, 30);
        male.setBounds(250, 250, 100, 30);
        female.setBounds(350, 250, 100, 30);
        c1.setBounds(250, 300, 100, 30);
        c2.setBounds(350, 300, 100, 30);
        c3.setBounds(250, 350, 100, 30);
        c4.setBounds(350, 350, 100, 30);
        a1.setBounds(250, 400, 200, 60);
        cb.setBounds(250, 480, 100, 30);

        b1.setBounds(150, 530, 100, 30);
        b2.setBounds(300, 530, 100, 30);
        b3.setBounds(450, 530, 100, 30);
        b4.setBounds(600, 530, 100, 30);
        b5.setBounds(750, 530, 100, 30);

        male.setActionCommand("Male");
        female.setActionCommand("Female");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // print dta here
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = t1.getText();
                String roll = t2.getText();
                String batch = t3.getText();
                String section = t4.getText();
                String Gender = group.getSelection().getActionCommand();
                String Qualification = c1.isSelected() ? "Matric" : "";
                Qualification += c2.isSelected() ? "Intermediate" : "";
                Qualification += c3.isSelected() ? "Graduate" : "";
                Qualification += c4.isSelected() ? "Post Graduate" : "";
                String Address = a1.getText();
                String Country = cb.getSelectedItem().toString();
                // print tha values on the another jframe

                new Infromation(name, roll, batch, section, Gender, Qualification, Address, Country);
            }
        });

        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String Gender = group.getSelection().getActionCommand();
                String Qualification = c1.isSelected() ? "Matric" : "";
                Qualification += c2.isSelected() ? "Intermediate" : "";
                Qualification += c3.isSelected() ? "Graduate" : "";
                Qualification += c4.isSelected() ? "Post Graduate" : "";
                try {
                    FileWriter fw = new FileWriter("./student.json");
                    JSONObject obj = new JSONObject();

                    obj.put("name", t1.getText());
                    obj.put("roll", t2.getText());
                    obj.put("batch", t3.getText());
                    obj.put("section", t4.getText());
                    obj.put("gender", Gender);
                    obj.put("qualification", Qualification);
                    obj.put("address", a1.getText());
                    obj.put("country", cb.getSelectedItem().toString());

                    fw.write(obj.toJSONString());
                    fw.close();
                } catch (Exception er) {
                    System.out.println(er);
                }
            }
        });

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                // code to clear fields
                t1.setText("");
                t2.setText("");
                t3.setText("");
                t4.setText("");
                male.setText("");
                female.setText("");
                c1.setText("");
                c2.setText("");
                c3.setText("");
                c4.setText("");
                a1.setText("");
                cb.setSelectedItem("");
            }

        });

        // add database button here
        b4.addActionListener((ActionListener) new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                // write the code here for database
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydata", "root", "");
                    // write query here
                    String query = "insert into studentinfo values(?,?,?,?,?,?,?,?)";
                    pr = con.prepareStatement(query);
                    pr.setString(1, t1.getText());
                    pr.setString(2, t2.getText());
                    pr.setString(3, t3.getText());
                    pr.setString(4, t4.getText());
                    if (male.isSelected()) {
                        pr.setString(5, male.getText());
                    } else {
                        pr.setString(5, female.getText());
                    }
                    if (c1.isSelected()) {
                        pr.setString(6, c1.getText());
                    }
                    if (c2.isSelected()) {
                        pr.setString(6, c2.getText());
                    }
                    if (c3.isSelected()) {
                        pr.setString(6, c3.getText());
                    }
                    if (c4.isSelected()) {
                        pr.setString(6, c4.getText());
                    }
                    pr.setString(7, a1.getText());
                    pr.setString(8, cb.getSelectedItem().toString());
                    // execute query here

                    int i = pr.executeUpdate();
                    JOptionPane.showMessageDialog(b4, i + " student added successfully");
                    pr.close();
                    con.close();

                } catch (Exception e1) {
                    // TODO: handle exception
                    e1.printStackTrace();
                }

            }

        }); // close the database button here

        // fetch data button here
        b5.addActionListener(new ActionListener() {
            // override method actionperformed(ActionEvent e)//
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                // write code here to fetch data
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydata", "root", "");
                    String sql = "Select * from studentinfo";
                    pr = con.prepareStatement(sql);
                    ResultSet rs = pr.executeQuery(sql);
                    while (rs.next()) {
                        // String name=t1.getText();
                        String name = rs.getString("Name");
                        String roll = rs.getString("RNo");
                        String batch = rs.getString("Batch");
                        String section = rs.getString("Section");
                        String Gender = rs.getString("gender");
                        String Qualification = rs.getString("qualification");
                        // Qualification += c2.isSelected() ? "Intermediate" : "";
                        // Qualification += c3.isSelected() ? "Graduate" : "";
                        // Qualification += c4.isSelected() ? "Post Graduate" : "";
                        String Address = rs.getString("addr");
                        String Country = rs.getString("country");
                        // constrictor
                        new Infromation(name, roll, batch, section, Gender, Qualification, Address, Country);
                    }

                } catch (ClassNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }

        });

        // add the component here
        group.add(male);
        group.add(female);
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
        f.add(male);
        f.add(female);
        f.add(c1);
        f.add(c2);
        f.add(c3);
        f.add(c4);
        f.add(a1);
        f.add(cb);
        f.add(b1);
        f.add(b2);
        f.add(b3);
        f.add(b4);
        f.add(b5);
        f.setSize(1000, 700);
        f.setLayout(null);
        f.getContentPane().setBackground(Color.YELLOW);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        new LAB13(); // calling the constructor here
    }
}