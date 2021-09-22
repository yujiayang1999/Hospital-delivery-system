package ca.ubc.cs304.ui;

import ca.ubc.cs304.database.DatabaseConnectionHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Removetask extends JPanel{
    public JLabel label1;

    public JTextField text1;

    public JButton button1;
    public JButton button2;
    int order_num;

    DatabaseConnectionHandler db;


    public Removetask(JFrame jf, int admin, DatabaseConnectionHandler db) {
        setLayout(new GridLayout(0,1));
        setSize(0,0);

        label1 = new JLabel("order_num need to be removed");
        Font f = new Font("Courier",Font.PLAIN,20);
        label1.setFont(f);
        label1.setForeground(Color.black);
        add(label1);
        text1 = new JTextField(10);
        text1.setFont(f);
        text1.setForeground(Color.black);
        add(text1);




//        label4 = new JLabel("Is it a normal task?");
//        add(label4);
//        text4 = new JTextField(15);
//        add(text4);


        button1 = new JButton("Submit");
        button1.setFont(f);
        add(button1);
        Remove e = new Remove(jf,admin,db);
        button1.addActionListener(e);

        button2 = new JButton("Back");
        button2.setFont(f);
        add(button2);
        Back back = new Back(jf,admin,db);
        button2.addActionListener(back);



    }

    public class Back implements ActionListener {
        public int admin;
        public JFrame jf;
        public DatabaseConnectionHandler db;


        public Back(JFrame jf,int admin,DatabaseConnectionHandler db) {
            this.admin = admin;
            this.jf = jf;
            this.db = db;

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Menu menu = new Menu(jf,admin,db);
            jf.setContentPane(menu);
            jf.validate();
        }
    }

    public class Remove implements ActionListener {
        public int admin;
        public JFrame jf;
        public DatabaseConnectionHandler db;

        public Remove(JFrame jf, int admin,DatabaseConnectionHandler db) {
            this.admin = admin;
            this.jf = jf;
            this.db = db;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            order_num = Integer.parseInt( text1.getText());
            db.removeorder(order_num);
            Removetask a = new Removetask(jf,admin,db);
            jf.setContentPane(a);
            jf.validate();




        }

    }

}




