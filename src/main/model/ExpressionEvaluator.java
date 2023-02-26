package model;

public class ExpressionEvaluator {

    private ExpressionLinkedList infixList = new ExpressionLinkedList();

    //checks if given character is an operator
    public boolean isOperator(char character) {
        final char[] OPERATORS = {'+', '-', '*', '/', '^', '(', ')'};

        for (int i = 0; i <= 6; i++) {
            if (character == OPERATORS[i]) {
                return true;
            }
        }

        return false;
    }

    //checks if given character is a number
    public boolean isNumber(char character) {
        final char[] NUMBERS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        for (int i = 0; i <= 10; i++) {
            if (character == NUMBERS[i]) {
                return true;
            }
        }

        return false;
    }


    //returns true if the expression is valid, false if not
    public boolean isValid(String userExpression) {

        int i;
        int rightBracketCount = 0;
        int leftBracketCount = 0;
        char prev = '1';

        for (i = 0; i < userExpression.length(); prev = userExpression.charAt((++i) - 1)) {
            if (isOperator(userExpression.charAt(i))) {
                if (userExpression.charAt(i) == '(') {
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

        return leftBracketCount == rightBracketCount;
    }


   /* public void toInfix(String userExpression) {
        ExpressionNode temp = (ExpressionNode) infixList.getHead().getNext();
    }*/


}
