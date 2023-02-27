package model;

import java.util.Stack;

// main model class to test validity of user expression and calculate answer (by converting to infix then postfix).
public class ExpressionEvaluator {

    private ExpressionLinkedList infixList = new ExpressionLinkedList();
    private ExpressionLinkedList postfixList = new ExpressionLinkedList();
    private CalculatorHistory history = new CalculatorHistory();
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
    @SuppressWarnings("methodlength")
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
                } else if (isOperator(prev) && (prev != ')') && (prev != '(')) {
                    return false;
                }
            } else if ((!isNumber(userExpression.charAt(i))) && (userExpression.charAt(i) != '.')) {
                return false;
            }
        }
        if (isOperator(prev) && (prev != ')')) {
            return false;
        } else {
            return leftBracketCount == rightBracketCount;
        }
    }

    //REQUIRES: assumes a proper and valid user expression
    //MODIFIES: this
    //EFFECTS: converts the user given string to an infix expression
    private void toInfix(String userExpression) {
        String num = "";
        int i = 0;
        boolean prevWasOperand = false;

        for (i = 0; (i < userExpression.length()); i++) {
            if (isOperator(userExpression.charAt(i))) {
               // if (prevWasOperand) {
                infixList.insertAtEnd(Double.parseDouble(num));
                num = "";
                prevWasOperand = false;
             //   }
                infixList.insertAtEnd(userExpression.charAt(i));
            } else if ((isNumber(userExpression.charAt(i))) || (userExpression.charAt(i) == '.')) {
                num += userExpression.charAt(i);
                prevWasOperand = true;
            }
        }
      /*  if (userExpression.charAt(userExpression.length() - 1) == ')') {
            return;
        } else {*/
        infixList.insertAtEnd(Double.parseDouble(num));
    }

//WARNING: REFACTOR METHOD TO BE SHORTER
    //REQUIRES: assumes a proper and valid infix list
    //MODIFIES: this
    //EFFECTS: converts an infix expression to a postfix expression
    private void toPostfix() {
        ExpressionNode temp = infixList.getHead().getNext();
        ExpressionStack stack = new ExpressionStack();
        for (; temp != null; temp = temp.getNext()) {
            if (temp.getOperator() == '$') {
                postfixList.insertAtEnd(temp.getOperand());
            //    temp = temp.getNext();
            } else if (isOperator(temp.getOperator())) {
                if (stack.isEmpty() || (stack.getTop().getOperator() == '(')
                        || (priority(temp.getOperator()) > priority(stack.getTop().getOperator()))) {
                    stack.push(temp.getOperator());
         //           temp = temp.getNext();
                } else {
                    while ((priority(temp.getOperator()) <= priority(stack.getTop().getOperator()))
                            && (!stack.isEmpty()) && (stack.getTop().getOperator() != '(')) {
                        postfixList.insertAtEnd(stack.pop(true).getOperator());
                    }
                    stack.push(temp.getOperator());
            //        temp = temp.getNext();
                }
            }
        }
        while (stack.getTopIndex() >= 0) {
            postfixList.insertAtEnd(stack.pop(true).getOperator());
        }
    }

    /*public void toPostfix() {
        ExpressionNode temp = infixList.getHead().getNext();
        ExpressionStack stack = new ExpressionStack();

        while (temp != null) {
            System.out.println("BREAKPOINT0");
            if (temp.getOperator() == '(') {
                stack.push(temp.getOperator());
            } else if (temp.getOperator() == ')') {
                while (stack.getTop().getOperator() != '(') {
                    postfixList.insertAtEnd(stack.pop(true).getOperator());
                    temp = temp.getNext();
                }
                if (stack.getTop().getOperator() == '(') {
                    stack.pop(false);
                }
            } else if (isOperator((temp.getOperator()))) {
                if (stack.isEmpty() || (priority(temp.getOperator()) > priority(stack.getTop().getOperator()))
                || (stack.getTop().getOperator() == '(')) {
                    postfixList.insertAtEnd(stack.pop(true).getOperator());
                } else if (priority(temp.getOperator()) <= priority(stack.getTop().getOperator())) {
                    while ((priority(temp.getOperator()) <= priority(stack.getTop().getOperator()))
                    && (!stack.isEmpty())
                    && (stack.getTop().getOperator() != '(')) {

                        if (stack.getTop().getOperator() == '(') {
                            stack.pop(false);
                        } else {
                            postfixList.insertAtEnd(stack.pop(true).getOperator());
                        }

                        temp = temp.getNext();
                    }
                    if (stack.getTop().getOperator() == '(') {
                        stack.pop(false);
                    }
                    postfixList.insertAtEnd(stack.pop(true).getOperator());
                }
            } else if (temp.getOperator() == '$') {
                while (!stack.isEmpty()) {
                    postfixList.insertAtEnd(stack.pop(true).getOperator());
                    temp = temp.getNext();
                }
                break;
            } else {
                postfixList.insertAtEnd(temp.getOperand());
                postfixList.printList();
                temp = temp.getNext();
            }
        }
    }*/


    //MODIFIES: this
    //EFFECTS: evaluates the postfix expression
    private void evaluate() {
        ExpressionNode temp = postfixList.head.getNext();
        double num1 = 0;
        double num2 = 0;
        ExpressionStack stack = new ExpressionStack();

        while (temp != null) {
            if (isOperator(temp.getOperator())) {
                num2 = stack.pop(true).getOperand();
                num1 = stack.pop(true).getOperand();

                res = decideOperation(temp.getOperator(), num1,num2);

                stack.push(res);
                temp = temp.getNext();
            } else {
                stack.push(temp.getOperand());
                temp = temp.getNext();
            }
        }
    }

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
    //EFFECTS: calculates result from user input
    public double calculate(String userExpression) {
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
