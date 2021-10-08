package com.example;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.Document;
import  java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
public class First {
    JTextField t1,t3;
    JPasswordField t2,t4;
    JFrame f;
    String s = "mongodb+srv://Bharath:bharath12345678@movie.jeait.mongodb.net/test?retryWrites=true&w=majority";
    First()
    {
        f = new JFrame();
        f.setTitle("Testing");
        f.setLayout(null);
        f.setBounds(0,0,1366,768);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.setVisible(true);
        JPanel p1 = new JPanel();
        p1.setBounds(117, 223, 320, 200);
        f.add(p1);
        p1.setLayout(null);
        JPanel p2 = new JPanel();
        p2.setBounds(838, 38, 442, 322);
        f.add(p2);
        p2.setLayout(null);
        p2.setVisible(false);
        JPanel p3 = new JPanel();
        p3.setBounds(838, 359, 442, 322);
        f.add(p3);
        p3.setLayout(null);
        p3.setVisible(false);
        JButton b1 = new JButton("LOGIN");
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                p2.setVisible(true);
                p3.setVisible(false);
            }
        });
        b1.setFont(new Font("Times New Roman", Font.PLAIN, 19));
        b1.setBounds(10, 11, 300, 52);
        p1.add(b1);

        JButton b2 = new JButton("REGISTER");
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                p2.setVisible(false);
                p3.setVisible(true);
            }
        });
        b2.setFont(new Font("Times New Roman", Font.PLAIN, 19));
        b2.setBounds(10, 138, 300, 52);
        p1.add(b2);

        t1 = new JTextField();
        t1.setFont(new Font("Times New Roman", Font.PLAIN, 19));
        t1.setBounds(148, 94, 284, 33);
        p2.add(t1);
        t1.setColumns(10);

        t2 = new JPasswordField();
        t2.setFont(new Font("Times New Roman", Font.PLAIN, 19));
        t2.setBounds(148, 193, 284, 33);
        p2.add(t2);

        JButton b4 = new JButton("LOGIN");
        b4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String pwd = new String(t2.getPassword());
                int p = 0;
                String s1 = t1.getText();
                FindIterable<Document> res;
                Logger.getLogger("org.mongodb.driver").setLevel(Level.WARNING   );
                try (MongoClient mongoClient = MongoClients.create(s)) {
                    res = new App().findlogin(mongoClient, s1,pwd);
                    Iterator it = res.iterator();
                    while (it.hasNext()) {
                        p = 1;
                        break;
                    }
                    if (p == 1) {
                        JOptionPane.showMessageDialog(null, "Login Success");
                        f.setVisible(false);
                        t3.setText("");
                        t4.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Login Failed or Check Registration");
                        t3.setText("");
                        t4.setText("");
                        p2.setVisible(false);
                        p3.setVisible(true);
                    }
                }
            }
        });
        b4.setFont(new Font("Times New Roman", Font.PLAIN, 19));
        b4.setBounds(289, 267, 143, 44);
        p2.add(b4);

        JLabel l1 = new JLabel("Login");
        l1.setFont(new Font("Times New Roman", Font.PLAIN, 19));
        l1.setHorizontalAlignment(SwingConstants.CENTER);
        l1.setBounds(10, 24, 422, 33);
        p2.add(l1);

        JLabel l3 = new JLabel("Email");
        l3.setFont(new Font("Times New Roman", Font.PLAIN, 19));
        l3.setBounds(20, 94, 86, 33);
        p2.add(l3);

        JLabel l2 = new JLabel("Password");
        l2.setFont(new Font("Times New Roman", Font.PLAIN, 19));
        l2.setBounds(20, 193, 86, 33);
        p2.add(l2);
        t3 = new JTextField();
        t3.setFont(new Font("Times New Roman", Font.PLAIN, 19));
        t3.setColumns(10);
        t3.setBounds(148, 82, 284, 33);
        p3.add(t3);

        t4 = new JPasswordField();
        t4.setFont(new Font("Times New Roman", Font.PLAIN, 19));
        t4.setBounds(148, 181, 284, 33);
        p3.add(t4);

        JButton b3 = new JButton("Register");
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int p=0;
                String pwd = new String(t4.getPassword());
                String s1=t3.getText();
                FindIterable<Document> res;
                Logger.getLogger("org.mongodb.driver").setLevel(Level.WARNING);
                try (MongoClient mongoClient = MongoClients.create(s)) {
                    res = new App().finduser(mongoClient,s1);
                    Iterator it = res.iterator();
                    while (it.hasNext()) {
                        p=1;
                        break;
                    }
                    if(p==0) {
                        new App().insertadmin(mongoClient, s1,pwd);
                        JOptionPane.showMessageDialog(null, "Registration Success");
                        p3.setVisible(false);
                        p2.setVisible(true);
                        t3.setText("");
                        t4.setText("");
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Registration Failed or Already Account Exists");
                        t3.setText("");
                        t4.setText("");
                    }
                }
            }
        });
        b3.setFont(new Font("Times New Roman", Font.PLAIN, 19));
        b3.setBounds(289, 267, 143, 44);
        p3.add(b3);

        JLabel l4 = new JLabel("Register");
        l4.setHorizontalAlignment(SwingConstants.CENTER);
        l4.setFont(new Font("Times New Roman", Font.PLAIN, 19));
        l4.setBounds(10, 11, 422, 33);
        p3.add(l4);

        JLabel l5 = new JLabel("Email");
        l5.setFont(new Font("Times New Roman", Font.PLAIN, 19));
        l5.setBounds(20, 82, 86, 33);
        p3.add(l5);

        JLabel l6 = new JLabel("Password");
        l6.setFont(new Font("Times New Roman", Font.PLAIN, 19));
        l6.setBounds(20, 181, 86, 33);
        p3.add(l6);



        JLabel l7 = new JLabel("");
        l7.setIcon(new ImageIcon("C:\\Users\\ADMIN\\eclipse-workspace\\Movie\\img\\ex.png"));
        l7.setBounds(10, 11, 1330, 707);
        f.add(l7);
    }
    public static void main(String a[])
    {

        new First();
    }
}
