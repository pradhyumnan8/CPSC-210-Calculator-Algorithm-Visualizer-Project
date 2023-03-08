package model;

import ui.CalculatorApp;

import java.io.FileNotFoundException;
import java.util.Stack;

// main model class to test validity of user expression and calculate answer (by converting to infix then postfix).
public class ExpressionEvaluator {

    private ExpressionLinkedList infixList = new ExpressionLinkedList();
    private ExpressionLinkedList postfixList = new ExpressionLinkedList();
    private CalculatorHistory history = new CalculatorHistory();
//    private InputOutput display = new InputOutput();
    private double res;
   // private ExpressionStack holdingStack = new ExpressionStack();


    //EFFECTS: returns history.
    public CalculatorHistory getHistory() {
        return history;
    }

    //EFFECTS: checks if given character is an operator
    private boolean isOperator(char character) {
        final char[] OPERATORS = {'+', '-', '*', '/', '^', '(', ')'};

        for (int i = 0; i <= 6; i++) {
            if (character == OPERATORS[i]) {
                return true;
            }
        }

        return false;
    }


    //EFFECTS: checks if given character is a number
    private boolean isNumber(char character) {
        final char[] NUMBERS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        for (int i = 0; i < 10; i++) {
            if (character == NUMBERS[i]) {
                return true;
            }
        }

        return false;
    }

    //REQUIRES: given character must be one of {'+', '-', '*', '/', '^', '('}
    //EFFECTS: returns the priority of the given operator. Higher the return value, greater the priority.
    public int priority(char operator) {
        int priority = 0;

        if (operator == '(') {
            priority = 4;
        } else if (operator == '^') {
            priority = 3;
        } else if ((operator == '*') || (operator == '/')) {
            priority = 2;
        } else {
            priority = 1;
        }

        return priority;
    }


    //NOTE: REFACTOR METHOD TO BE NEATER
    //EFFECTS: returns true if the expression is valid, false if not
    public boolean isValid(String userExpression) {
        int i = 0;
        int rightBracketCount = 0;
        int leftBracketCount = 0;
        char prev = '1';
        for (i = 0; i < userExpression.length(); prev = userExpression.charAt(i), i++) {
            if (isOperator(userExpression.charAt(0))) {
                return false;
            } else if (isOperator(userExpression.charAt(i))) {
                if (prev == '.') {
                    return false;
                } else if (userExpression.charAt(i) == '(') {
                    leftBracketCount++;
                } else if (userExpression.charAt(i) == ')') {
                    rightBracketCount++;
                } else if (isOperator(prev) && (prev != ')') /*&& (prev != '(')*/) {
                    return false;
                }
            } else if ((!isNumber(userExpression.charAt(i))) && (userExpression.charAt(i) != '.')) {
                return false;
            }
        }
        return isValidFinalDecision(prev, leftBracketCount, rightBracketCount);
    }

    //REQUIRES: appropriate values for parameters as per isValid() method.
    //EFFECTS: helper function for isValid method as it was over line limit. Takes the final decision past the loop.
    private boolean isValidFinalDecision(char prev, int leftBracketCount, int rightBracketCount) {
        if (isOperator(prev) && (prev != ')')) {
            return false;
        } else {
            return leftBracketCount == rightBracketCount;
        }
    }



    //REQUIRES: assumes a proper and valid user expression
    //MODIFIES: this
    //EFFECTS: converts the user given string to an infix expression
    @SuppressWarnings("methodlength")
    private void toInfix(String userExpression) {
        String num = "";
        int i = 0;
        boolean prevWasOperand = false;

        for (i = 0; (i < userExpression.length()); i++) {
            if (isOperator(userExpression.charAt(i))) {
                if (prevWasOperand) {
                    infixList.insertAtEnd(Double.parseDouble(num));
                    num = "";
                    prevWasOperand = false;
                }
                infixList.insertAtEnd(userExpression.charAt(i));
            } else if ((isNumber(userExpression.charAt(i)))) {
                num += userExpression.charAt(i);
                prevWasOperand = true;
            } else if ((userExpression.charAt(i) == '.')) {
                num += userExpression.charAt(i);
                prevWasOperand = true;
            }
        }
        if (userExpression.charAt(userExpression.length() - 1) == ')') {
            return;
        } else {
            infixList.insertAtEnd(Double.parseDouble(num));
        }
    }

//} else if ((isNumber(userExpression.charAt(i))) || (userExpression.charAt(i) == '.')) {
//        num += userExpression.charAt(i);
//        prevWasOperand = true;
//        }

    //REQUIRES: assumes a proper and valid infix list
    //MODIFIES: this
    //EFFECTS: converts an infix expression to a postfix expression
    @SuppressWarnings("methodlength")
    private void toPostfix() {
        ExpressionNode temp = infixList.head.getNext();
        Stack<ExpressionNode> stack = new Stack<>();

        for (; temp != null; temp = temp.getNext()) {
            if (temp.getOperator() == '$') {
                postfixList.insertAtEnd(temp.getOperand());
            } else {
                if ((stack.empty()) || (priority(temp.getOperator()) > priority(stack.peek().getOperator()))
                        || (stack.peek().getOperator() == '(')) {
                    stack.push(temp);
                } else if (temp.getOperator() == ')') {
                    while (stack.peek().getOperator() != '(') {
                        postfixList.insertAtEnd(stack.pop().getOperator());
                    }
                    stack.pop();
                } else {
                    while ((!stack.empty()) && (priority(temp.getOperator()) <= priority(stack.peek().getOperator()))) {
                        postfixList.insertAtEnd(stack.pop().getOperator());
                    }
                    stack.push(temp);
                }
            }
        }

        while (!stack.empty()) {
            postfixList.insertAtEnd(stack.pop().getOperator());
        }
    }


    //MODIFIES: this
    //EFFECTS: evaluates the postfix expression
    private void evaluate() throws FileNotFoundException {
        CalculatorApp display = new CalculatorApp();
        ExpressionNode temp = postfixList.head.getNext();
        double num1 = 0;
        double num2 = 0;
        ExpressionStack stack = new ExpressionStack();
       // System.out.printf("\nBefore starting evaluation process:\n");
       // display.printStack(stack, 2);

        while (temp != null) {
            if (isOperator(temp.getOperator())) {
                num2 = stack.pop(true).getOperand();
        //        display.printStack(stack, 2);

                num1 = stack.pop(true).getOperand();
        //        display.printStack(stack, 2);

                res = decideOperation(temp.getOperator(), num1,num2);

                stack.push(res);
        //        display.printStack(stack, 2);

                temp = temp.getNext();
            } else {
                stack.push(temp.getOperand());
        //        display.printStack(stack, 2);

                temp = temp.getNext();
            }
        }
      //  System.out.printf("\nAfter Finishing evaluation process:\n");
     //   display.printStack(stack, 2);
    }


    //REQUIRES: appropriate values for parameters as per evaluate() method
    //EFFECTS: helper function for evaluate() method to which arithmetic operation to use on the popped numbers.
    private double decideOperation(char operator, double num1, double num2) {
        if (operator == '+') {
            return num1 + num2;
        } else if (operator == '-') {
            return num1 - num2;
        } else if (operator == '*') {
            return num1 * num2;
        } else if (operator == '/') {
            return num1 / num2;
        } else {
            return Math.pow(num1, num2);
        }
    }



    //MODIFIES: this
    //EFFECTS: calculates result from user input and clears the infix/postfix lists to be ready for next calculation
    public double calculate(String userExpression) throws FileNotFoundException {
        this.isValid(userExpression);
        this.toInfix(userExpression);
        this.toPostfix();
        this.evaluate();

        Calculation thisCalculation = new Calculation(userExpression, res);

        history.addCalculation(thisCalculation);

        infixList.clearList();
        postfixList.clearList();

        return res;
    }
}
