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


    public String scanExpression() {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("Enter Expression:");
            expression = input.nextLine();

            if (!evaluator.isValid(expression)) {
                System.out.println("Given expression is Invalid. Please enter a new expression:");
            } else {
                break;
            }
        }

        return expression;
    }

    public void printHistory (CalculatorHistory history) {
        for (int i = 0; i < history.getCalculations().size(); i++) {
            System.out.printf("%d) Expression: %s      Result: %f\n", i, history.getCalculations().get(i).getExpression(), history.getCalculations().get(i).getResult());
        }

    }

    //REQUIRES: Input must be valid
    //EFFECTS: Provides the user with a menu to continue operating the program.
    public void menu() {
        int selection = 0;
        System.out.println("select from the following options:");
        System.out.println("1) view history");
        System.out.println("2) get mean of history");
        System.out.println("3) get median of history");
        System.out.println("4) perform another calculation");

        selection = input.nextInt();

        switch (selection) {
            case 1:
                printHistory(evaluator.getHistory());
                break;
            case 2:
                System.out.println(evaluator.getHistory().mean());
                break;
            case 3:
                System.out.println(evaluator.getHistory().median());
                break;
            case 4:
                this.calculator();
                break;
        }
    }

    //MODIFIES: this
    //EFFECTS: main method to run all the methods for the calculator.
    public void calculator() {

        //expression = "";
        expression = scanExpression();
        double res = evaluator.calculate(expression);
        history = evaluator.getHistory();

        System.out.println("The answer is:  " + res);
        System.out.println();

        System.out.println("Do you want to quit the application [y/n]?");
        this.input = new Scanner(System.in);
        String quit = input.next();

        while (!Objects.equals(quit, "y")) {
            this.menu();

            System.out.println("Do you want to quit the application [y/n]?");
            quit = input.next();
        }
    }
}