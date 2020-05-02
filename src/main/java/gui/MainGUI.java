package main.java.gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class MainGUI extends JFrame{



    private JLabel item;
    private JTextField item1;
    private JTextField item2;
    private JTextField item3;
    private JPasswordField passwordField;
    private JCheckBox checkBox;
    private Choice choiceBox;



    public MainGUI(){
        super("AgendaBuilder");
        setLayout(new FlowLayout());

        item = new JLabel("This is a test");
        item.setToolTipText("This is gonna show up on hover");
        add(item);

        item1 = new JTextField(10);
        add(item1);

        item2 = new JTextField("Enter text here");
        add(item2);

        item3 = new JTextField("uneditabel", 20);
        item3.setEditable(false);
        add(item3);

        passwordField = new JPasswordField("mypass");
        add(passwordField);

        checkBox = new JCheckBox("Check if youre cool");
        add(checkBox);

        choiceBox = new Choice();
        choiceBox.add("Cpen 311");
        choiceBox.add("Cpen 211");
        add(choiceBox);

        Handler handler = new Handler();
        ItemListener itemListener = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String string = new String();
                if(e.getSource() == choiceBox)
                    string = "omg";

                JOptionPane.showMessageDialog(null, string);

            }
        };

        item1.addActionListener(handler);
        item2.addActionListener(handler);
        item3.addActionListener(handler);
        passwordField.addActionListener(handler);
        checkBox.addActionListener(handler);
        choiceBox.addItemListener(itemListener);



    }

    private class Handler implements ActionListener{

        public void actionPerformed(ActionEvent event){

            String string = "";

            if(event.getSource() == item1)
                string = String.format("field 1: %s", event.getActionCommand());
            else if(event.getSource() == item2)
                string = String.format("field 2: %s", event.getActionCommand());
            else if(event.getSource() == item2)
                string = String.format("field 3: %s", event.getActionCommand());
            else if(event.getSource() == passwordField)
                string = String.format("password is: %s", event.getActionCommand());
            else if(event.getSource() == checkBox)
                string = "CHECK BOX IS CLICKED";


            JOptionPane.showMessageDialog(null, string);
        }

    }



    public static void main(String[] args){

        MainGUI gui = new MainGUI();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(1024, 768);
        gui.setVisible(true);
    }






}
