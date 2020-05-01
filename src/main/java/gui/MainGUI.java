package main.java.gui;


import javax.swing.*;
import java.awt.*;

public class MainGUI extends JFrame{

    public MainGUI(){
        super("AgendaBuilder");
        setLayout(new FlowLayout());

        JLabel item = new JLabel("This is a test");

    }



    public static void main(String[] args){

        MainGUI gui = new MainGUI();
        gui.setSize(1024, 768);
        gui.setVisible(true);
    }
    





}
