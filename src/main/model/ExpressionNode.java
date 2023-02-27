package model;

//Represent each node in the linked list. This allows for both operators and operands to be contained in a single list.
public class ExpressionNode {

    private ExpressionNode next;
    private double operand;
    private char operator;


    //MODIFIES: this
    //EFFECTS: instantiates a new ExpressionNode with operator as '$' (indicates that this node is an operand node)
    public ExpressionNode() {
        this.operator = '$';
        this.operand = 0;
        this.next = null;
    }

    //EFFECTS: returns next.
    public ExpressionNode getNext() {
        return next;
    }

    //EFFECTS: returns operand.
    public double getOperand() {
        return operand;
    }

    //EFFECTS: returns operator.
    public char getOperator() {
        return operator;
    }

    //MODIFIES: this
    //EFFECTS: sets next reference to the given node
    public void setNext(ExpressionNode next) {
        this.next = next;
    }

    //MODIFIES: this
    //EFFECTS: sets operand to the given value
    public void setOperand(double operand) {
        this.operand = operand;
    }

    //MODIFIES: this
    //EFFECTS: sets operator to the given character
    public void setOperator(char operator) {
        this.operator = operator;
    }
}
