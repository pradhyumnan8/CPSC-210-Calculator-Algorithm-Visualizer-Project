package model;

public class ExpressionNode {

    private ExpressionNode next;
    private double operand;
    private char operator;



    public ExpressionNode() {
        this.operator = '$';
        this.operand = 0;
        this.next = null;
    }

    public ExpressionNode(double operand, char operator) {
        this.operator = operator;
        this.operand = operand;
        this.next = null;
    }

    public ExpressionNode getNext() {
        return next;
    }

    public double getOperand() {
        return operand;
    }

    public char getOperator() {
        return operator;
    }

    public void setNext(ExpressionNode next) {
        this.next = next;
    }

    public void setOperand(double operand) {
        this.operand = operand;
    }

    public void setOperator(char operator) {
        this.operator = operator;
    }
}