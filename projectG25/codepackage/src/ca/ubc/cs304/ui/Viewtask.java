package ca.ubc.cs304.ui;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.model.DrugModel;
import ca.ubc.cs304.model.Order;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Viewtask extends JPanel {
    JButton back;
    DatabaseConnectionHandler db;
    public Viewtask(JFrame jf, int admin,DatabaseConnectionHandler db) {
        setLayout(null);
        setSize(new Dimension(0,0));

        Order[] models = db.getorder();

        for (int i = 0; i < models.length; i++) {
            Order model = models[i];
            System.out.printf("%-10.10s", model.getOrder_date());
            System.out.printf("%-20.20s", model.getOrder_num());
            // simplified output formatting; truncation may occur


            System.out.println();
        }
        setSize(0, 0);
        this.setBackground(new Color(176,196,222));

        back = new JButton("Back");
        back.setBounds(400,100,200,100);
        back.setFont(new Font("Courier ITALIC", Font.PLAIN, 20));
        add(back);
        ViewBackEvent backi = new ViewBackEvent(jf,admin);
        back.addActionListener(backi);


    }



    public class ViewBackEvent implements ActionListener {
        JFrame jf;
        int admin;


        public ViewBackEvent(JFrame jf,int admin) {
            this.admin = admin;
            this.jf = jf;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

//            ScheduleWindow menuwindow = new ScheduleWindow(scheedulewindow,admin);
//            menuwindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//            menuwindow.setSize(1000,1000);
//            menuwindow.setVisible(true);
            Menu menu = new Menu(jf,admin,db);
            jf.setContentPane(menu);
            jf.validate();
        }
    }



}
