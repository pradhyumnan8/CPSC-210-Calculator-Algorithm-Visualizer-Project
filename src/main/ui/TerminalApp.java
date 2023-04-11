package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;


//Acts as the user interface by prompting the user for inputs and displaying outputs onto the screen.
public class TerminalApp {

    private static final String JSON_STORE = "./data/calculations.json";
    private Scanner input;
    private ExpressionEvaluator evaluator;
    private CalculatorHistory history;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private String expression;
    boolean isFirstRun;


    // EFFECTS: constructs a new TerminalApp() and runs application
    public TerminalApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        evaluator = new ExpressionEvaluator();
        history = new CalculatorHistory();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        isFirstRun = true;
    }


    //MODIFIES: this
    //EFFECTS: prompts the user for an expression until a valid one is entered. Returns the expression.
    public String scanExpression() {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("Enter Expression:");
            expression = input.nextLine();

            if (!evaluator.isValid(expression)) {
                System.out.println("Given expression is Invalid");
                System.out.println();
            } else {
                break;
            }
        }

        return expression;
    }

    //REQUIRES: history should not be null
    //EFFECTS: prints the history of all the user calculations onto the screen.
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


    //REQUIRES: choice must be 0, 1, or 2.
    //EFFECTS: prints the entire stack, or prints "stack is empty" if the stack is empty.
    //         if choice is 0, both fields are printed.
    //         if choice is 1, operators are printed.
    //         if choice is 2, operands are printed.
    public void printStack(ExpressionStack stack, int choice) {
        ExpressionNode temp = stack.getHead().getNext();

        for (; true; temp = temp.getNext()) {
            if (temp == null) {
                System.out.println("The stack is empty");
                System.out.println();
                //System.exit(1);
                break;
            }

            if (choice == 0) {
                System.out.printf("[%c  %f]\n", temp.getOperator(), temp.getOperand());
            } else if (choice == 1) {
                System.out.printf("[%c]\n", temp.getOperator());
            } else {
                System.out.printf("[%f]\n", temp.getOperand());
            }
        }
    }


    //REQUIRES: Input must be valid
    //EFFECTS: Provides the user with a menu to continue operating the program.
    @SuppressWarnings("methodlength")
    public void menu() throws FileNotFoundException {
        String selection = "";
        input = new Scanner(System.in);

        System.out.println("select from the following options:");
        System.out.println("1) view history");
        System.out.println("2) delete an entry from history");
        System.out.println("3) clear history");
        System.out.println("4) get mean of history");
        //System.out.println("5) get median of history");
        System.out.println("6) perform a calculation");
        //System.out.println("7) load ");

        selection = input.nextLine();

        if (Objects.equals(selection, "1")) {
            System.out.println();
            printHistory(evaluator.getHistory());
        } else if (Objects.equals(selection, "2")) {
            System.out.println();
            this.deleteHistory();
        } else if (Objects.equals(selection, "3")) {
            System.out.println();
            history.clearHistory();
        } else if (Objects.equals(selection, "4")) {
            System.out.println();
            System.out.println("The mean of your calculations is:  " + evaluator.getHistory().mean());
//        } else if (Objects.equals(selection, "5")) {
//            System.out.println();
//            System.out.println("The median of your calculations is:  " + evaluator.getHistory().mean());
        } else if (Objects.equals(selection, "6")) {
            System.out.println();
            this.calculator();
        } else {
            System.out.println();
            System.out.printf("Invalid Input, please select again: \n\n");
            this.menu();
        }
    }


    //NOTE: need to check properly for errors
    //EFFECTS: helper method for menu() to prompt and gather input to delete an entry from the history.
    private void deleteHistory() {
        input = new Scanner(System.in);
        String index = "";

        System.out.println("Enter the line number of the entry you would like to delete:");
        index = input.nextLine();
        System.out.println();

        history.delete(index.charAt(0) - '0');
    }

    //MODIFIES: this
    //EFFECTS: main method to run all the methods for the calculator from getting input to getting input in the menu.
    public void calculator() throws FileNotFoundException {
        String shouldQuit;

        input = new Scanner(System.in);

        decideLoad();

        isFirstRun = false;

        expression = scanExpression();
        double res = evaluator.calculate(expression);
        history = evaluator.getHistory();

        System.out.printf("The answer is: %f\n\n", res);

        while (true) {
            System.out.println("Do you want to quit the application [y/n]?");
            shouldQuit = input.nextLine();

            if (Objects.equals("y", shouldQuit)) {
                if (savePrompt()) {
                    this.save();
                }

                System.exit(1);
            } else if (Objects.equals("n", shouldQuit)) {
                System.out.println();
                this.menu();
                //return;
            } else {
                System.out.println("Invalid input");
            }
        }
    }

    //EFFECTS: helper method for calculator(). Decides whether this is the first time we launch the program and prompts
    //         user to load from save.
    private void decideLoad() {
        if (isFirstRun) {
            if (loadPrompt()) {
                this.load();
            }
        }
    }

    //EFFECTS: helper function for menu(). Returns true if user decides to save the application and false otherwise.
    private boolean savePrompt() {
        input = new Scanner(System.in);
        String shouldSave = "";

        System.out.println("Do you want to save the application before quitting? [y/n]?");
        shouldSave = input.nextLine();

        return shouldSave.equals("y");
    }

    //EFFECTS: helper function for menu(). Returns true if user decides to load the application and false otherwise.
    private boolean loadPrompt() {
        input = new Scanner(System.in);
        String shouldLoad = "";

        System.out.println("Do you want to load the application from previous save? [y/n]?");
        shouldLoad = input.nextLine();

        return shouldLoad.equals("y");
    }

    //EFFECTS: saves the application (history) to file
    private void save() {
        try {
            jsonWriter.open();
            jsonWriter.write(history);
            jsonWriter.close();
            System.out.println("History has been successfully saved at  " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    //MODIFIES: this
    //EFFECTS: loads the application (history) from file
    private void load() {
        try {
            //history = jsonReader.read();
            ArrayList<Calculation> newHistory = jsonReader.read();
            this.history.setCalculations(newHistory);
            this.evaluator.getHistory().setCalculations(newHistory);
            System.out.println("History has been successfully loaded from  " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    public void printLog(EventLog log) {
        for (Event event : log) {
            System.out.println(event.toString() + "\n");
        }
    }
}