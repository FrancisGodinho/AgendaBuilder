package main.java.gui;


import com.mindfusion.scheduling.Calendar;
import com.mindfusion.scheduling.CalendarView;
import com.mindfusion.scheduling.ThemeType;
import com.mindfusion.scheduling.awt.AwtCalendar;

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

    private AwtCalendar calendar;



    public MainGUI(){
        super("AgendaBuilder");


        Container container = this.getContentPane();
        SpringLayout springLayout = new SpringLayout();
        container.setLayout(springLayout);

        item = new JLabel("This is a test");
        item.setToolTipText("This is gonna show up on hover");
        container.add(item);

        item1 = new JTextField(10);
        container.add(item1);

        item2 = new JTextField("Enter text here");
        container.add(item2);

        item3 = new JTextField("uneditabel", 20);
        item3.setEditable(false);
        container.add(item3);

        passwordField = new JPasswordField("mypass");
        container.add(passwordField);

        checkBox = new JCheckBox("Check if youre cool");
        container.add(checkBox);

        choiceBox = new Choice();
        choiceBox.add("Cpen 311");
        choiceBox.add("Cpen 211");
        container.add(choiceBox);

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

        springLayout.putConstraint(SpringLayout.SOUTH, item, -5, SpringLayout.SOUTH, container);
        springLayout.putConstraint(SpringLayout.WEST, item, 5, SpringLayout.EAST, item);

        springLayout.putConstraint(SpringLayout.SOUTH, item1, -5, SpringLayout.SOUTH, container);
        springLayout.putConstraint(SpringLayout.WEST, item1, 5, SpringLayout.EAST, item1);

        springLayout.putConstraint(SpringLayout.SOUTH, item2, -5, SpringLayout.SOUTH, container);
        springLayout.putConstraint(SpringLayout.WEST, item2, 5, SpringLayout.EAST, item2);


        calendar = new AwtCalendar();
        calendar.beginInit();
        calendar.setCurrentView(CalendarView.Timetable);
        calendar.setTheme(ThemeType.Light);
        calendar.endInit();

        container.add(calendar);



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
