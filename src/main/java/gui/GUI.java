package main.java.gui;


import main.java.timetable.AgendaBuilder;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame{

    public GUI(){
        super("AgendaBuilder");
        setLayout(new FlowLayout());

        JLabel item = new JLabel("This is a test");

    }



    public static void main(String[] args){

        GUI gui = new GUI();
        gui.setSize(1024, 768);
        gui.setVisible(true);





    }





}
