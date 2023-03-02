package ui;

import model.CalculatorHistory;
import model.ExpressionEvaluator;

import java.util.Objects;
import java.util.Scanner;


//Acts as the user interface by prompting the user for inputs and displaying outputs onto the screen.
public class InputOutput {

    private Scanner input = new Scanner(System.in);
    private ExpressionEvaluator evaluator = new ExpressionEvaluator();
    private CalculatorHistory history = new CalculatorHistory();
    private String expression;


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
        for (int i = 0; i < history.getCalculations().size(); i++) {
            System.out.printf("%d) Expression: %s      Result: %f\n",
                    i, history.getCalculations().get(i).getExpression(), history.getCalculations().get(i).getResult());
        }

    }

    //REQUIRES: Input must be valid
    //EFFECTS: Provides the user with a menu to continue operating the program.
    public void menu() {
        String selection = "";
        input = new Scanner(System.in);

        System.out.println("select from the following options:");
        System.out.println("1) view history");
        System.out.println("2) get mean of history");
        //System.out.println("3) get median of history");
        System.out.println("3) perform another calculation");

        selection = input.nextLine();

        if (Objects.equals(selection, "1")) {
            System.out.println();
            printHistory(evaluator.getHistory());
        } else if (Objects.equals(selection, "2")) {
            System.out.println();
            System.out.println("The mean of your calculations is:  " + evaluator.getHistory().mean());
        } else if (Objects.equals(selection, "3")) {
            System.out.println();
            this.calculator();
        } else {
            System.out.println();
            System.out.printf("Invalid Input, please select again: \n\n");
            this.menu();
        }
    }

    //MODIFIES: this
    //EFFECTS: main method to run all the methods for the calculator from getting input to getting input in the menu.
    public int calculator() {

        input = new Scanner(System.in);
        expression = scanExpression();
        double res = evaluator.calculate(expression);
        history = evaluator.getHistory();
        String quit;

        System.out.printf("The answer is: %f\n\n", res);

        while (true) {
            System.out.println("Do you want to quit the application [y/n]?");
            quit = input.nextLine();

            if (Objects.equals("y", quit)) {
                System.exit(1);
            } else if (Objects.equals("n", quit)) {
                System.out.println();
                this.menu();
            } else {
                System.out.println("Invalid input");
            }
        }
    }
}