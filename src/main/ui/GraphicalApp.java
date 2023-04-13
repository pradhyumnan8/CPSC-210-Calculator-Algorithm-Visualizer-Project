package ui;

import model.Calculation;
import model.CalculatorHistory;
import model.Event;
import model.EventLog;
import model.ExpressionEvaluator;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

//Graphical User Interface for the calculator.
public class GraphicalApp implements ActionListener, WindowListener {
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


    //MODIFIES: this
    //EFFECTS: constructs a new instance of GraphicalApp
    GraphicalApp() {
        history = new CalculatorHistory();
        evaluator = new ExpressionEvaluator();

        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);

        initializeRows(165, HEIGHT + PADDING);
        initializeColumns(10, WIDTH + PADDING);

        initializeBasicElements();
        initializeButtons();
        layoutButtons();
        renderSplashScreen();

        setupScrollPane();
    }


    //MODIFIES: this
    //EFFECTS: instantiates and sets up the JFrame, JPanel, JTextArea, and JPopupArea. Helper for constructor
    private void initializeBasicElements() {
        frame = new JFrame("My Calculator");
        panel = new JPanel();
        display = new JTextArea();
        historyMenu = new JPopupMenu();

        setupFrame();
        //setupScrollPane();
        setupDisplay();
    }

    //MODIFIES: this
    //EFFECTS: sets up the display. Helper for initializeBasicElements()
    private void setupDisplay() {
        display.setBounds(10, 10, 480, 150);
        display.setBackground(Color.PINK);
        frame.add(display);
    }

    //MODIFIES: this
    //EFFECTS: sets up the frame. Helper for initializeBasicElements()
    private void setupFrame() {
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(this);
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setBackground(Color.BLUE);
    }


    //MODIFIES: this
    //EFFECTS: sets up the scroll pane. Helper for initializeBasicElements()
    private void setupScrollPane() {
        JScrollPane scrollPane = new JScrollPane(display);
        scrollPane.setBounds(display.getBounds());
        frame.getContentPane().add(scrollPane);
    }

    //REQUIRES: instantiated row[] array
    //MODIFIES: this
    //EFFECTS: initializes the row array. helper for constructor.
    private void initializeRows(int first, int difference) {
        row = new int[5];

        row[0] = first;

        for (int i = 1; i < row.length; i++) {
            row[i] = row[i - 1] + difference;
        }
    }

    //REQUIRES: instantiated col[] array
    //MODIFIES: this
    //EFFECTS: initializes the column array. helper for constructor.
    private void initializeColumns(int first, int difference) {
        column = new int[5];

        column[0] = first;

        for (int i = 1; i < column.length; i++) {
            column[i] = column[i - 1] + difference;
        }
    }

    //MODIFIES: this
    //EFFECTS: instantiates and sets up all the buttons. Helper for constructor.
    private void initializeButtons() {
        String[] operations = {"+", "-", "*", "/", "^", "(", ")", "=", "."};
        numButton = new JButton[10];
        oppButton = new JButton[operations.length];

        buttonViewHistory = new JButton("View");
        buttonClearHistory = new JButton("Clear");
        buttonDeleteHistoryEntry = new JButton("Delete");
        buttonMean = new JButton("Mean");

        buttonSave = new JButton("Save");
        buttonLoad = new JButton("Load");
        buttonClearScreen = new JButton("AC");
        buttonBackspace = new JButton("C");

        buttonHistory = new JButton("History");

        initializeNumberButtons();
        initializeOperationButtons(operations);
        initializeHistoryButtons();
        initializeBottomRowButtons();
    }

    //REQUIRES: instantiated numButton[] array
    //MODIFIES: this
    //EFFECTS: sets up the number buttons. Helper for initializeButtons()
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

    //REQUIRES: instantiated oppButton[] array
    //MODIFIES: this
    //EFFECTS: sets up the operations buttons. Helper for initializeButtons()
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

    //REQUIRES: instantiated History Buttons
    //MODIFIES: this
    //EFFECTS: sets up the History buttons. Helper for initializeButtons()
    private void initializeHistoryButtons() {
        JButton[] historyFunctions = {buttonViewHistory, buttonClearHistory, buttonDeleteHistoryEntry, buttonMean};

        buttonHistory.setFocusable(false);
        buttonHistory.addActionListener(this);
        frame.add(buttonHistory);

        for (int i = 0; i < historyFunctions.length; i++) {
            historyFunctions[i].addActionListener(this);
            historyFunctions[i].setFocusable(false);

            historyMenu.add(historyFunctions[i]);
        }
    }

    //REQUIRES: instantiated Bottom Row Buttons
    //MODIFIES: this
    //EFFECTS: sets up the Bottom Row buttons. Helper for initializeButtons()
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

    //MODIFIES: this
    //EFFECTS: lays all the buttons in a nice grid.
    private void layoutButtons() {
        layoutRow1(row[0], column);
        layoutRow2(row[1], column);
        layoutRow3(row[2], column);
        layoutRow4(row[3], column);
        layoutRow5(row[4], column);
    }

    //REQUIRES: instantiated buttons
    //MODIFIES: this
    //EFFECTS: lays out the first row
    private void layoutRow1(int row, int[] col) {
        numButton[1].setBounds(col[0], row, WIDTH, HEIGHT);
        numButton[2].setBounds(col[1], row, WIDTH, HEIGHT);
        numButton[3].setBounds(col[2], row, WIDTH, HEIGHT);
        oppButton[0].setBounds(col[3], row, WIDTH, HEIGHT);
        oppButton[1].setBounds(col[4], row, WIDTH, HEIGHT);
    }

    //REQUIRES: instantiated buttons
    //MODIFIES: this
    //EFFECTS: lays out the second row
    private void layoutRow2(int row, int[] col) {
        numButton[4].setBounds(col[0], row, WIDTH, HEIGHT);
        numButton[5].setBounds(col[1], row, WIDTH, HEIGHT);
        numButton[6].setBounds(col[2], row, WIDTH, HEIGHT);
        oppButton[2].setBounds(col[3], row, WIDTH, HEIGHT);
        oppButton[3].setBounds(col[4], row, WIDTH, HEIGHT);
    }

    //REQUIRES: instantiated buttons
    //MODIFIES: this
    //EFFECTS: lays out the third row
    private void layoutRow3(int row, int[] col) {
        numButton[7].setBounds(col[0], row, WIDTH, HEIGHT);
        numButton[8].setBounds(col[1], row, WIDTH, HEIGHT);
        numButton[9].setBounds(col[2], row, WIDTH, HEIGHT);
        oppButton[5].setBounds(col[3], row, WIDTH, HEIGHT);
        oppButton[6].setBounds(col[4], row, WIDTH, HEIGHT);
    }

    //REQUIRES: instantiated buttons
    //MODIFIES: this
    //EFFECTS: lays out the fourth row
    private void layoutRow4(int row, int[] col) {
        numButton[0].setBounds(col[0], row, 2 * WIDTH + PADDING, HEIGHT);
        oppButton[7].setBounds(col[2], row, WIDTH, HEIGHT);
        oppButton[8].setBounds(col[3], row, WIDTH, HEIGHT);
        oppButton[4].setBounds(col[4], row, WIDTH, HEIGHT);
    }

    //REQUIRES: instantiated buttons
    //MODIFIES: this
    //EFFECTS: lays out the fifth row
    private void layoutRow5(int row, int[] col) {
        buttonHistory.setBounds(col[0], row, WIDTH, HEIGHT);
        buttonSave.setBounds(col[1], row, WIDTH, HEIGHT);
        buttonLoad.setBounds(col[2], row, WIDTH, HEIGHT);
        buttonBackspace.setBounds(col[3], row, WIDTH, HEIGHT);
        buttonClearScreen.setBounds(col[4], row, WIDTH, HEIGHT);
    }



    //MODIFIES: this
    //EFFECTS: Helper for constructor, renders start up splash screen.
    private void renderSplashScreen() {
        panel.setBounds(display.getBounds());
        panel.setVisible(true);

        ImageIcon splash = new ImageIcon("data/splashResized.jpeg");
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

        renderLoadingTimeout(textLabel);

        textLabel.setVisible(false);
        imageLabel.setVisible(false);
        panel.setVisible(false);

        this.display.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: Renders Loading.. and Loading... Helper for renderSplashScreen()
    private void renderLoadingTimeout(JLabel textLabel) {
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
    }

    @Override
    //MODIFIES: this
    //EFFECTS: whenever a button is pressed, performs the appropriate action (eg. displaying number)
    public void actionPerformed(ActionEvent event) {

        processNumberInput(event);

        processOperationInput(event);

        processHistoryInput(event);

        processPersistenceInputs(event);

        processNavigationInput(event);
    }


    //MODIFIES: this
    //EFFECTS: checks if a number button was pressed and completes the appropriate action. Helper for actionPerformed()
    private void processNumberInput(ActionEvent event) {
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
    }


    //MODIFIES: this
    //EFFECTS: if an operation button was pressed, completes the appropriate action. Helper for actionPerformed()
    private void processOperationInput(ActionEvent event) {
        for (int i = 0; i < oppButton.length; i++) {
            if (event.getSource() == oppButton[i]) {
                if (oppButton[i].getText().equals("=")) {
                    if (!evaluator.isValid(display.getText())) {
                        display.setText("Invalid Expression, Please Try again");
                    } else {
                        try {
                            display.setText(String.valueOf(evaluator.calculate(display.getText())));
                            this.history = evaluator.getHistory();
                            break;
                        } catch (FileNotFoundException exception) {
                            display.setText(FILE_NOT_FOUND_EXCEPTION_MESSAGE);
                        }
                    }
                }

                if (oppButton[i].getText() != "=") {
                    display.append(oppButton[i].getText());
                }
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: checks if a History button was pressed and completes the appropriate action. Helper for actionPerformed()
    private void processHistoryInput(ActionEvent event) {
        if (event.getSource() == buttonHistory) {
            historyMenu.setLocation(buttonHistory.getX(), buttonHistory.getY());
            historyMenu.setVisible(true);
        }

        processViewHistory(event);

        processClearHistory(event);

        processDeleteHistory(event);

        processMean(event);
    }


    //MODIFIES: this
    //EFFECTS: checks if view button was pressed and displays history on the display. helper to processHistoryInput()
    private void processViewHistory(ActionEvent event) {
        if (event.getSource() == buttonViewHistory) {
            display.setText("History");
            display.append(historyToString(this.history));
            historyMenu.setVisible(false);
        }
    }

    //MODIFIES: this
    //EFFECTS: checks if Clear history button was pressed and clears the history. helper to processHistoryInput()
    private void processClearHistory(ActionEvent event) {
        if (event.getSource() == buttonClearHistory) {
            display.setText("History Cleared");
//            this.evaluator = new ExpressionEvaluator();
//            this.history = evaluator.getHistory();
            history.clearHistory();
            historyMenu.setVisible(false);
        }
    }

    //MODIFIES: this
    //EFFECTS: checks if Delete History button was pressed and deletes entry corresponding to the specified line number
    //         Helper to processHistoryInput()
    private void processDeleteHistory(ActionEvent event) {
        if (event.getSource() == buttonDeleteHistoryEntry) {
            historyMenu.setVisible(false);

            display.setText("History");
            display.append(historyToString(this.history));

            display.append("\n\n Enter the line number of the entry you want to delete\n");
            deleteEntryFlag = true;
        }
    }

    //MODIFIES: this
    //EFFECTS: checks if Mean button was pressed and displays Mean on the display. helper to processHistoryInput()
    private void processMean(ActionEvent event) {
        if (event.getSource() == buttonMean) {
            display.setText("The Mean of your Calculations is:  " + evaluator.getHistory().mean());
            historyMenu.setVisible(false);
        }
    }

    //MODIFIES: this
    //EFFECTS: saves or loads the application. Helper to actionPerformed()
    private void processPersistenceInputs(ActionEvent event) {
        if (event.getSource() == buttonSave) {
            this.save();
        }

        if (event.getSource() == buttonLoad) {
            this.load();
        }
    }


    //MODIFIES: this
    //EFFECTS: clears one character or all of the screen depending on input. Helper to actionPerformed()
    private void processNavigationInput(ActionEvent event) {
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

    //EFFECTS: prints the log onto console
    public void printLog(EventLog log) {
        System.out.println();

        for (Event event : log) {
            System.out.println(event.toString() + "\n");
        }
    }


    @Override
    //EFFECTS: prints the log onto console and exits the application
    public void windowDeactivated(WindowEvent e) {
        printLog(EventLog.getInstance());
        System.exit(0);
    }

    @Override
    //EFFECTS: Nothing, dummy implementation as every method from WindowListener interface has to be implemented.
    public void windowOpened(WindowEvent e) {
        //nothing
    }

    @Override
    //EFFECTS: Nothing, dummy implementation as every method from WindowListener interface has to be implemented.
    public void windowClosing(WindowEvent e) {
        //nothing
    }

    @Override
    //EFFECTS: Nothing, dummy implementation as every method from WindowListener interface has to be implemented.
    public void windowClosed(WindowEvent e) {
        //nothing
    }

    @Override
    //EFFECTS: Nothing, dummy implementation as every method from WindowListener interface has to be implemented.
    public void windowIconified(WindowEvent e) {
        //nothing
    }

    @Override
    //EFFECTS: Nothing, dummy implementation as every method from WindowListener interface has to be implemented.
    public void windowDeiconified(WindowEvent e) {
        //nothing
    }

    @Override
    //EFFECTS: Nothing, dummy implementation as every method from WindowListener interface has to be implemented.
    public void windowActivated(WindowEvent e) {
        //nothing
    }
}
