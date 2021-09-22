package ca.ubc.cs304.ui;

import ca.ubc.cs304.database.DatabaseConnectionHandler;

import javax.swing.*;
import java.awt.*;

public class ScheduleWindow extends JFrame {
    int int1;
    public ScheduleWindow(JFrame frame, int admin, DatabaseConnectionHandler db) {
        super();
        this.int1 = admin;
        setBackground(new Color(192,192,192));
        setVisible(true);
        setSize(1000,1000);
        Menu menu = new Menu(this, admin,db);
        add(menu);
    }
}
