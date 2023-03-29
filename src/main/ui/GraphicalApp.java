package ui;

import model.Calculation;
import model.CalculatorHistory;
import model.ExpressionEvaluator;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class GraphicalApp implements ActionListener {
    private static final String JSON_STORE = "./data/calculations.json";
    private static final String FILE_NOT_FOUND_EXCEPTION_MESSAGE = "Unable to run application: file not found";

    private static final int WIDTH = 90;
    private static final int HEIGHT = 55;
    private static final int PADDING = 7;

    private int[] row;
    private int[] column;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    CalculatorHistory history;
    ExpressionEvaluator evaluator;

    private JFrame frame;
    private JTextArea display;
    private JPopupMenu historyMenu;
    private JPanel panel;



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

    //@SuppressWarnings("methodlength")
    GraphicalApp() {
        history = new CalculatorHistory();
        evaluator = new ExpressionEvaluator();

        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);

        String[] operations = {"+", "-", "*", "/", "^", "(", ")", "=", "."};

        row = new int[5];
        column = new int[5];


        frame = new JFrame("My Calculator");
        panel = new JPanel();
        display = new JTextArea();
        historyMenu = new JPopupMenu();

        numButton = new JButton[10];
        oppButton = new JButton[operations.length];

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setBackground(Color.BLUE);

        display.setBounds(10, 10, 480, 150);
        display.setBackground(Color.PINK);

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


        buttonHistory.setFocusable(false);
        buttonHistory.addActionListener(this);
        frame.add(buttonHistory);

        initializeRows(165, HEIGHT + PADDING);
        initializeColumns(10, WIDTH + PADDING);

        initializeButtons(operations);
        layoutButtons();
        renderSplashScreen();
    }

    private void initializeButtons(String[] operations) {
        initializeNumberButtons();
        initializeOperationButtons(operations);
        initializeHistoryButtons();
        initializeBottomRowButtons();
    }

    private void initializeRows(int first, int difference) {
        row[0] = first;

        for (int i = 1; i < row.length; i++) {
            row[i] = row[i - 1] + difference;
        }
    }

    private void initializeColumns(int first, int difference) {
        column[0] = first;

        for (int i = 1; i < column.length; i++) {
            column[i] = column[i - 1] + difference;
        }
    }

        private void initializeNumberButtons() {
        for (int i = 0; i <= 9; i++) {
            numButton[i] = new JButton(String.valueOf(i));
            numButton[i].addActionListener(this);
            numButton[i].setFocusable(false);
            numButton[i].setBackground(Color.ORANGE);
            numButton[i].setOpaque(true);
            frame.add(numButton[i]);
        }
    }

    private void initializeOperationButtons(String[] operations) {
        for (int i = 0; i < operations.length; i++) {
            oppButton[i] = new JButton(operations[i]);
            oppButton[i].addActionListener(this);
            oppButton[i].setFocusable(false);
            oppButton[i].setBackground(Color.ORANGE);
            oppButton[i].setOpaque(true);
            frame.add(oppButton[i]);
        }
    }

    private void initializeHistoryButtons() {
        JButton[] historyFunctions = {buttonViewHistory, buttonClearHistory, buttonDeleteHistoryEntry, buttonMean};

        for (int i = 0; i < historyFunctions.length; i++) {
            historyFunctions[i].addActionListener(this);
            historyFunctions[i].setFocusable(false);

            historyMenu.add(historyFunctions[i]);
        }
    }

    private void initializeBottomRowButtons() {
        JButton[] bottomRow = {buttonHistory, buttonSave, buttonLoad, buttonClearScreen, buttonBackspace};

        for (int i = 0; i < bottomRow.length; i++) {
            bottomRow[i].addActionListener(this);
            bottomRow[i].setFocusable(false);
            bottomRow[i].setBackground(Color.ORANGE);
            bottomRow[i].setOpaque(true);
            frame.add(bottomRow[i]);
        }
    }

    private void layoutButtons() {
        layoutRow1(row[0], column);
        layoutRow2(row[1], column);
        layoutRow3(row[2], column);
        layoutRow4(row[3], column);
        layoutRow5(row[4], column);
    }

    private void layoutRow1(int row, int[] col) {
        numButton[1].setBounds(col[0], row, WIDTH, HEIGHT);
        numButton[2].setBounds(col[1], row, WIDTH, HEIGHT);
        numButton[3].setBounds(col[2], row, WIDTH, HEIGHT);
        oppButton[0].setBounds(col[3], row, WIDTH, HEIGHT);
        oppButton[1].setBounds(col[4], row, WIDTH, HEIGHT);
    }

    private void layoutRow2(int row, int[] col) {
        numButton[4].setBounds(col[0], row, WIDTH, HEIGHT);
        numButton[5].setBounds(col[1], row, WIDTH, HEIGHT);
        numButton[6].setBounds(col[2], row, WIDTH, HEIGHT);
        oppButton[2].setBounds(col[3], row, WIDTH, HEIGHT);
        oppButton[3].setBounds(col[4], row, WIDTH, HEIGHT);
    }

    private void layoutRow3(int row, int[] col) {
        numButton[7].setBounds(col[0], row, WIDTH, HEIGHT);
        numButton[8].setBounds(col[1], row, WIDTH, HEIGHT);
        numButton[9].setBounds(col[2], row, WIDTH, HEIGHT);
        oppButton[5].setBounds(col[3], row, WIDTH, HEIGHT);
        oppButton[6].setBounds(col[4], row, WIDTH, HEIGHT);
    }

    private void layoutRow4(int row, int[] col) {
        numButton[0].setBounds(col[0], row, 2 * WIDTH + PADDING, HEIGHT);
        oppButton[7].setBounds(col[2], row, WIDTH, HEIGHT);
        oppButton[8].setBounds(col[3], row, WIDTH, HEIGHT);
        oppButton[4].setBounds(col[4], row, WIDTH, HEIGHT);
    }

    private void layoutRow5(int row, int[] col) {
        buttonHistory.setBounds(col[0], row, WIDTH, HEIGHT);
        buttonSave.setBounds(col[1], row, WIDTH, HEIGHT);
        buttonLoad.setBounds(col[2], row, WIDTH, HEIGHT);
        buttonBackspace.setBounds(col[3], row, WIDTH, HEIGHT);
        buttonClearScreen.setBounds(col[4], row, WIDTH, HEIGHT);
    }



    //MODIFIES: this
    //EFFECTS: Helper for constructor, renders start up splash screen.
    @SuppressWarnings("methodlength")
    private void renderSplashScreen() {
        panel.setBounds(display.getBounds());
        panel.setVisible(true);

        ImageIcon splash = new ImageIcon("/Users/pradhyumnan/Documents/UBC/CPSC 210/project_d0m5r/data/splashResized.jpeg");
        JLabel imageLabel = new JLabel(splash);
        imageLabel.setBounds(display.getBounds());
        imageLabel.setVisible(true);

        JLabel textLabel = new JLabel("Loading.");
        textLabel.setBounds(imageLabel.getX(), imageLabel.getY() + 40, 100, 100);
        textLabel.setFont(new Font("Sans-Serif", Font.BOLD, 20));
        textLabel.setVisible(true);

        this.display.setVisible(false);

        frame.add(panel);
        panel.add(textLabel);
        panel.add(imageLabel);

        try {
            Thread.sleep(1000);
            textLabel.setText("Loading..");
        } catch (InterruptedException e) {
            //Should not be caught
        }

        try {
            Thread.sleep(1000);
            textLabel.setText("Loading...");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            //Should not be caught
        }

        textLabel.setVisible(false);
        imageLabel.setVisible(false);
        panel.setVisible(false);

        this.display.setVisible(true);
    }


    @Override
    @SuppressWarnings("methodlength")
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
                historyAsString += ("\n" + (i + 1) + ") " + history.getCalculations().get(i).getExpression() + "  =  "
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
