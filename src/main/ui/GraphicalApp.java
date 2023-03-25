package ui;

import model.CalculatorHistory;
import model.ExpressionEvaluator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class GraphicalApp implements ActionListener {
    final String FILE_NOT_FOUND_EXCEPTION_MESSAGE = "Unable to run application: file not found";

    CalculatorHistory history;
    ExpressionEvaluator evaluator;

    private JFrame frame;
    private JTextArea display;

    private JButton[] numButton;
    private JButton[] oppButton;

    private JButton[] funcButton;


    private JButton buttonViewHistory;
    private JButton buttonMean;
    private JButton buttonSave;
    private JButton buttonLoad;


    GraphicalApp() {
        history = new CalculatorHistory();
        evaluator = new ExpressionEvaluator();

        final int WIDTH = 90;
        final int HEIGHT = 65;
        final int PADDING = 7;

        final int ROW1 = 120;
        final int ROW2 = ROW1 + HEIGHT + PADDING;
        final int ROW3 = ROW2 + HEIGHT + PADDING;
        final int ROW4 = ROW3 + HEIGHT + PADDING;
        final int ROW5 = ROW4 + HEIGHT + PADDING;

        final int COL1 = 10;
        final int COL2 = COL1 + WIDTH + PADDING;
        final int COL3 = COL2 + WIDTH + PADDING;
        final int COL4 = COL3 + WIDTH + PADDING;
        final int COL5 = COL4 + WIDTH + PADDING;

        String[] operations = {"+", "-", "*", "/", "^", "(", ")", "=", "."};
        //String[] functions = {"History", "Mean", "Save", "Load"};

        frame = new JFrame("Calculator");
        display = new JTextArea();
        numButton = new JButton[10];
        oppButton = new JButton[operations.length];

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);

        display.setBounds(10, 10, 480, 100);

        frame.add(display);

        buttonViewHistory = new JButton("View");
        buttonViewHistory.addActionListener(this);
        buttonViewHistory.setFocusable(false);
        frame.add(buttonViewHistory);


        for (int i = 0; i <= 9; i++) {
            numButton[i] = new JButton(String.valueOf(i));
            numButton[i].addActionListener(this);
            numButton[i].setFocusable(false);
            frame.add(numButton[i]);
        }

        for (int i = 0; i < operations.length; i++) {
            oppButton[i] = new JButton(operations[i]);
            oppButton[i].addActionListener(this);
            oppButton[i].setFocusable(false);
            frame.add(oppButton[i]);
        }


        //buttonViewHistory = new JButton("History");
        buttonMean = new JButton("Mean");
        buttonSave = new JButton("Save");
        buttonLoad = new JButton("Load");



        //LAYOUT OF FIRST NUM ROW
        numButton[1].setBounds(COL1, ROW1, WIDTH, HEIGHT);
        numButton[2].setBounds(COL2, ROW1, WIDTH, HEIGHT);
        numButton[3].setBounds(COL3, ROW1, WIDTH, HEIGHT);
        oppButton[0].setBounds(COL4, ROW1, WIDTH, HEIGHT);
        oppButton[1].setBounds(COL5, ROW1, WIDTH, HEIGHT);

        //LAYOUT OF SECOND NUM ROW
        numButton[4].setBounds(COL1, ROW2, WIDTH, HEIGHT);
        numButton[5].setBounds(COL2, ROW2, WIDTH, HEIGHT);
        numButton[6].setBounds(COL3, ROW2, WIDTH, HEIGHT);
        oppButton[2].setBounds(COL4, ROW2, WIDTH, HEIGHT);
        oppButton[3].setBounds(COL5, ROW2, WIDTH, HEIGHT);

        //LAYOUT OF THIRD NUM ROW
        numButton[7].setBounds(COL1, ROW3, WIDTH, HEIGHT);
        numButton[8].setBounds(COL2, ROW3, WIDTH, HEIGHT);
        numButton[9].setBounds(COL3, ROW3, WIDTH, HEIGHT);
        oppButton[5].setBounds(COL4, ROW3, WIDTH, HEIGHT);
        oppButton[6].setBounds(COL5, ROW3, WIDTH, HEIGHT);

        numButton[0].setBounds(COL1, ROW4, 2 * WIDTH + PADDING, HEIGHT);
        oppButton[7].setBounds(COL3, ROW4, WIDTH, HEIGHT);
        oppButton[8].setBounds(COL4, ROW4, WIDTH, HEIGHT);
        oppButton[4].setBounds(COL5, ROW4, WIDTH, HEIGHT);

        buttonViewHistory.setBounds(COL1, ROW5, WIDTH, HEIGHT);
    }


    @Override
    //MODIFIES: this
    //EFFECTS: whenever a button is pressed, performs the appropriate action (eg. displaying number)
    public void actionPerformed(ActionEvent event) {

        for (int i = 0; i <= 9; i++) {
            if (event.getSource() == numButton[i]) {
                display.append(numButton[i].getText());
            }
        }

        for (int i = 0; i < oppButton.length; i++) {
            if (event.getSource() == oppButton[i]) {
                if (oppButton[i].getText().equals("=")) {
                    try {
                        display.setText(String.valueOf(evaluator.calculate(display.getText())));
                        this.history = evaluator.getHistory();
                        break;
                    } catch (FileNotFoundException exception) {
                        display.setText(FILE_NOT_FOUND_EXCEPTION_MESSAGE);
                    }
                }

                display.append(oppButton[i].getText());
            }
        }

        if (event.getSource() == buttonViewHistory) {
            display.setText("History");
            display.append(historyToString(this.history));
        }

    }


    //EFFECTS: returns the history List inside passed history parameter as a String ready for printing.
    //         Helper to actionPerformed().
    public String historyToString(CalculatorHistory history) {
        String historyAsString = new String();

        if (history.getCalculations().size() == 0) {
            return "History is Empty";
        } else {
            for (int i = 0; i < history.getCalculations().size(); i++) {
                historyAsString += ( "\n" + (i + 1) + ") " + history.getCalculations().get(i).getExpression() + "  =  "
                                   + history.getCalculations().get(i).getResult());
            }
        }

        return historyAsString;
    }


    public void printHistory(CalculatorHistory history) {

        if (history.getCalculations().size() == 0) {
            System.out.println("There is nothing in your history");
        } else {
            for (int i = 0; i < history.getCalculations().size(); i++) {
                System.out.printf("%d) Expression: %s      Result: %f\n", i + 1,
                        history.getCalculations().get(i).getExpression(), history.getCalculations().get(i).getResult());
            }
        }
        System.out.println();
    }

}
