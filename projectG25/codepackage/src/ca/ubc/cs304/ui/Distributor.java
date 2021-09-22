package ca.ubc.cs304.ui;


import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.delegates.LoginWindowDelegate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static java.awt.Color.black;
import static java.awt.Color.white;

public class Distributor extends JFrame {
    private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
    private JButton button1;
    private JLabel label1;
    private JButton button2;
    private JLabel label2;
    private JTextField text1;
    private JTextField text2;
    public int dis_id;
    public DatabaseConnectionHandler db;
    public Connection con;


    public Distributor(DatabaseConnectionHandler db,Connection con) {
        this.db = db;
        this.con = con;
        showframe(db);
    }
    public void showframe(DatabaseConnectionHandler db){

        setLayout(null);
        JPanel contentPane = new JPanel();
        this.setContentPane(contentPane);

        this.getContentPane().setBackground(new Color(176,196,222));

        label1 = new JLabel("Users name");
        Font f = new Font("Courier",Font.PLAIN,30);
        label1.setFont(f);
        label1.setForeground(black);
        label1.setBounds(300,250,400,50);
        contentPane.add(label1);

        text1 = new JTextField(15);
        text1.setFont(new Font("Courier", Font.BOLD,20));
        text1.setBounds(300,300,400,50);
        contentPane.add(text1);

        label2 = new JLabel("Password");
        Font h = new Font("Courier ITALIC",Font.PLAIN,30);
        label2.setFont(h);
        label2.setForeground(black);
        label2.setBounds(300,350,400,50);
        contentPane.add(label2);

        text2 = new JTextField(20);
        text2.setSize(90,30);
        text2.setFont(new Font("Courier", Font.BOLD,20));
        text2.setBounds(300,400,400,50);
        contentPane.add(text2);

        button1 = new JButton("login");
        button1.setFont(new Font("Courier", Font.BOLD, 20));
        button1.setBounds(400,500,100,40);
        contentPane.add(button1);
        button1.setBackground(white);
        //Actionevent e = new Actionevent();
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dis_id = Integer.parseInt( text1.getText());
                db.insertDis(dis_id,con);
                ScheduleWindow schedule = new ScheduleWindow(Distributor.this,dis_id,db);
                schedule.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                schedule.setSize(1000,1000);
                schedule.setVisible(true);
            }
        });


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000,1000);
        setVisible(true);
        setTitle("distributor");

//        ok.addActionListener(new ActionListener(){
//
//            @Override
//            public void actionPerformed(ActionEvent arg0) {
//                double x = Double.parseDouble(xField.getText());
//                double y = Double.parseDouble(yField.getText());
//
//                xField.setText(Double.toString(x));
//                xField.setText(Double.toString(y));
//
//                zField.setText(Double.toString(x*y));
//            });


    }
//    public class Actionevent implements ActionListener {
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            try {
//                Class.forName("oracle.jdbc.driver.OracleDriver");
//                Connection con = DriverManager.
//                        getConnection("jdbc:oracle:thin:@localhost:1522:stu"
//                                ,"ora_yangyuji","a82719170");
//
//                Statement stmt = con.createStatement();
//                System.out.println("Created DB Connection....");
//            } catch (ClassNotFoundException eee) {
//                // TODO Auto-generated catch block
//                eee.printStackTrace();
//            } catch (SQLException ee) {
//                // TODO Auto-generated catch block
//                ee.printStackTrace();
//            }
//
//            dis_id = Integer.parseInt( text1.getText());
//            db.insertDis(dis_id,con);
//            ScheduleWindow schedule = new ScheduleWindow(Distributor.this,dis_id,db);
//            schedule.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//            schedule.setSize(1000,1000);
//            schedule.setVisible(true);
//
//
//        }
//    }

    public static void main(String[] args) {
            DatabaseConnectionHandler db = new DatabaseConnectionHandler();
            try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            db.connection = DriverManager.
                    getConnection("jdbc:oracle:thin:@localhost:1522:stu"
                            ,"ora_yangyuji","a82719170");

            Statement stmt = db.connection.createStatement();
            System.out.println("Created DB Connection....");
                Distributor gui = new Distributor(db,db.connection);

                gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
                gui.setSize(1000,1000);
                gui.setVisible(true);
                gui.setTitle("distributor");

            } catch (ClassNotFoundException eee) {
            // TODO Auto-generated catch block
            eee.printStackTrace();
        } catch (SQLException ee) {
            // TODO Auto-generated catch block
            ee.printStackTrace();
        }



    }




}

