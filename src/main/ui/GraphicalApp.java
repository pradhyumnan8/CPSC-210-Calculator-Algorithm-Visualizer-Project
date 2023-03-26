package ui;

import model.Calculation;
import model.CalculatorHistory;
import model.ExpressionEvaluator;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class GraphicalApp implements ActionListener {
    private static final String JSON_STORE = "./data/calculations.json";
    final String FILE_NOT_FOUND_EXCEPTION_MESSAGE = "Unable to run application: file not found";

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    CalculatorHistory history;
    ExpressionEvaluator evaluator;

    private JFrame frame;
    private JTextArea display;
    private JPopupMenu historyMenu;



    private JButton[] numButton;
    private JButton[] oppButton;


    private JButton buttonViewHistory;
    private JButton buttonClearHistory;
    private JButton buttonDeleteHistoryEntry;
    private JButton buttonMean;

    private JButton buttonHistory;
    private JButton buttonClearScreen;
    private JButton buttonBackspace;
    private JButton buttonSave;
    private JButton buttonLoad;

    private boolean deleteEntryFlag = false;

    GraphicalApp() {
        history = new CalculatorHistory();
        evaluator = new ExpressionEvaluator();

        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);

        final int WIDTH = 90;
        final int HEIGHT = 55;
        final int PADDING = 7;

        final int ROW1 = 165;
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

        frame = new JFrame("Calculator");
        display = new JTextArea();
        historyMenu = new JPopupMenu();

        numButton = new JButton[10];
        oppButton = new JButton[operations.length];

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);

        display.setBounds(10, 10, 480, 150);
        frame.add(display);


        buttonViewHistory = new JButton("View");
        buttonClearHistory = new JButton("Clear");
        buttonDeleteHistoryEntry = new JButton("Delete");
        buttonMean = new JButton("Mean");

        buttonHistory = new JButton("History");
        buttonSave = new JButton("Save");
        buttonLoad = new JButton("Load");
        buttonClearScreen = new JButton("AC");
        buttonBackspace = new JButton("C");


        JButton[] historyFunctions = {buttonViewHistory, buttonClearHistory, buttonDeleteHistoryEntry, buttonMean};
        JButton[] bottomRow = {buttonHistory, buttonSave, buttonLoad, buttonClearScreen, buttonBackspace};

        buttonHistory.setFocusable(false);
        buttonHistory.addActionListener(this);
        frame.add(buttonHistory);

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

        for (int i = 0; i < historyFunctions.length; i++) {
            historyFunctions[i].addActionListener(this);
            historyFunctions[i].setFocusable(false);

            historyMenu.add(historyFunctions[i]);
        }

        for (int i = 0; i < bottomRow.length; i++) {
            bottomRow[i].addActionListener(this);
            bottomRow[i].setFocusable(false);
            frame.add(bottomRow[i]);
        }

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

        buttonHistory.setBounds(COL1, ROW5, WIDTH, HEIGHT);
        buttonSave.setBounds(COL2, ROW5, WIDTH, HEIGHT);
        buttonLoad.setBounds(COL3, ROW5, WIDTH, HEIGHT);
        buttonBackspace.setBounds(COL4, ROW5, WIDTH, HEIGHT);
        buttonClearScreen.setBounds(COL5, ROW5, WIDTH, HEIGHT);
    }


    @Override
    //MODIFIES: this
    //EFFECTS: whenever a button is pressed, performs the appropriate action (eg. displaying number)
    public void actionPerformed(ActionEvent event) {

        for (int i = 0; i <= 9; i++) {
            if (event.getSource() == numButton[i]) {
                display.append(numButton[i].getText());

                if (deleteEntryFlag) {
                    history.delete(numButton[i].getText().charAt(0) - '0');
                    display.setText("Chosen entry has been deleted");
                    deleteEntryFlag = false;
                }
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

        if (event.getSource() == buttonHistory) {
            historyMenu.setLocation(buttonHistory.getX(), buttonHistory.getY());
            historyMenu.setVisible(true);
        }

        if (event.getSource() == buttonViewHistory) {
            display.setText("History");
            display.append(historyToString(this.history));
            historyMenu.setVisible(false);
        }

        if (event.getSource() == buttonClearHistory) {
            display.setText("History Cleared");
            this.evaluator = new ExpressionEvaluator();
            this.history = evaluator.getHistory();
            historyMenu.setVisible(false);
        }

        if (event.getSource() == buttonDeleteHistoryEntry) {
            historyMenu.setVisible(false);

            display.setText("History");
            display.append(historyToString(this.history));

            display.append("\n\n Enter the line number of the entry you want to delete\n");
            deleteEntryFlag = true;
        }

        if (event.getSource() == buttonMean) {
            display.setText("The Mean of your Calculations is:  " + evaluator.getHistory().mean());
            historyMenu.setVisible(false);
        }

        if (event.getSource() == buttonSave) {
            this.save();
        }

        if (event.getSource() == buttonLoad) {
            this.load();
        }

        if (event.getSource() == buttonBackspace) {
            if (display.getText() == "") {
                display.setText("");
            } else {
                display.setText(display.getText().substring(0, display.getText().length() - 1));
            }
        }

        if (event.getSource() == buttonClearScreen) {
            display.setText("");
        }
    }


    //EFFECTS: returns the history List inside passed history parameter as a String ready for printing.
    //         Helper to actionPerformed().
    private String historyToString(CalculatorHistory history) {
        String historyAsString = new String();

        if (history.getCalculations().size() == 0) {
            return " is Empty";
        } else {
            for (int i = 0; i < history.getCalculations().size(); i++) {
                historyAsString += ( "\n" + (i + 1) + ") " + history.getCalculations().get(i).getExpression() + "  =  "
                                   + history.getCalculations().get(i).getResult());
            }
        }

        return historyAsString;
    }

    //EFFECTS: saves the application (history) to file
    private void save() {
        try {
            jsonWriter.open();
            jsonWriter.write(history);
            jsonWriter.close();
            display.setText("History has been successfully saved at  " + JSON_STORE);
        } catch (FileNotFoundException e) {
            display.setText("Unable to write to file: " + JSON_STORE);
        }
    }


    //MODIFIES: this
    //EFFECTS: loads the application (history) from file
    private void load() {
        try {
            ArrayList<Calculation> newHistory = jsonReader.read();
            this.history.setCalculations(newHistory);
            this.evaluator.getHistory().setCalculations(newHistory);
            display.setText("History has been successfully loaded from  " + JSON_STORE);
        } catch (IOException e) {
            display.setText("Unable to read from file: " + JSON_STORE);
        }
    }
}
