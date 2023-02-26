package ui;

import model.ExpressionEvaluator;

import java.util.Scanner;


//prompts for and accepts input from user
public class InputOutput {

    public String scanExpression() {
        Scanner input = new Scanner(System.in);
        ExpressionEvaluator expressionValidator = new ExpressionEvaluator();
        String expression;

        while (true) {
            System.out.println("Enter Expression:");
            expression = input.nextLine();

            if (!expressionValidator.isValid(expression)) {
                System.out.println("Given expression is Invalid. Please enter a new expression:");
            } else {
                break;
            }
        }

        return expression;
    }

    private void printHistory () {

    }

    public void Menu() {
        int i = 0;
        System.out.println("select from the ");
    }


}
