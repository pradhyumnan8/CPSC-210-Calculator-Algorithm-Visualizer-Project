package model;

public class ExpressionNode extends ListNode {

    private ExpressionNode next;
    private double operand;
    private char operator;


    @Override
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
