package ui;

import model.ExpressionEvaluator;

import java.util.Scanner;


//prompts for and accepts input from user
public class Input {

    public String getExpression() {
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
}
