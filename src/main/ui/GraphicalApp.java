package ui;

import javax.swing.*;

public class GraphicalApp {
    private JFrame frame;
    private JTextArea display;

    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JButton button0;

    private JButton buttonAdd;
    private JButton buttonSubtract;
    private JButton buttonMultiply;
    private JButton buttonDivide;
    private JButton buttonPower;
    private JButton buttonLeftBracket;
    private JButton buttonRightBracket;
    private JButton buttonEqual;

    private JButton buttonViewHistory;
    private JButton buttonMean;
    private JButton buttonSave;
    private JButton buttonLoad;





    GraphicalApp() {
        final int WIDTH = 90;
        final int HEIGHT = 70;
        final int PADDING = 10;

        final int ROW1 = 120;
        final int ROW2 = ROW1 + HEIGHT + 20;
        final int ROW3 = ROW2 + HEIGHT + 20;

        final int COL1 = 10;
        final int COL2 = COL1 + 10;
        final int COL3 = COL2 + 10;

        button1 = new JButton("1");
        button2 = new JButton("2");
        button3 = new JButton("3");
        button4 = new JButton("4");
        button5 = new JButton("5");
        button6 = new JButton("6");
        button7 = new JButton("7");
        button8 = new JButton("8");
        button9 = new JButton("9");
        button0 = new JButton("0");

        buttonAdd = new JButton("+");
        buttonSubtract = new JButton("-");
        buttonMultiply = new JButton("*");
        buttonDivide = new JButton("/");
        buttonPower = new JButton("^");
        buttonLeftBracket = new JButton("(");
        buttonRightBracket = new JButton(")");
        buttonEqual = new JButton("=");

        buttonViewHistory = new JButton("History");
        buttonMean = new JButton("Mean");
        buttonSave = new JButton("Save");
        buttonLoad = new JButton("Load");


        button1.setBounds(10, ROW1, WIDTH, HEIGHT );
        button2.setBounds(button1.getX() + WIDTH + PADDING, ROW1, WIDTH, HEIGHT );
        button3.setBounds(button2.getX() + WIDTH + PADDING, ROW1, WIDTH, HEIGHT );

        button4.setBounds(10, ROW2, WIDTH, HEIGHT );
        button5.setBounds(button4.getX() + WIDTH + PADDING, ROW2, WIDTH, HEIGHT );
        button6.setBounds(button5.getX() + WIDTH + PADDING, ROW2, WIDTH, HEIGHT );


        button7.setBounds(10, ROW3, WIDTH, HEIGHT );
        button8.setBounds(button7.getX() + WIDTH + PADDING, ROW3, WIDTH, HEIGHT );
        button9.setBounds(button8.getX() + WIDTH + PADDING, ROW3, WIDTH, HEIGHT );




        frame = new JFrame("GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        frame.setLayout(null);

        display = new JTextArea();
        display.setBounds(10, 10, 480, 100);



        frame.add(display);

        frame.add(button0);
        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.add(button4);
        frame.add(button5);
        frame.add(button6);
        frame.add(button7);
        frame.add(button8);
        frame.add(button9);
        frame.add(button0);

        frame.add(buttonAdd);
        frame.add(buttonMultiply);
        frame.add(buttonDivide);

        frame.setVisible(true);
    }


}
