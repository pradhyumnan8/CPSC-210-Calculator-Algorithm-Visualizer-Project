package model;

//class that builds a stack based on the ExpressionLinkedList class
public class ExpressionStack extends ExpressionLinkedList {

    private int topIndex;
    private ExpressionNode top;

    //EFFECTS: instantiates a new ExpressionStack with.
    public ExpressionStack() {
        this.topIndex = -1;
        this.top = head.getNext();
    }

    //MODIFIES: this
    //EFFECTS: creates and pushes a new node with the given operand into the stack.
    public void push(double operand) {
        super.insertAfterHead(operand);
        top = head.getNext();
        topIndex++;
    }


    //MODIFIES: this
    //EFFECTS: creates and pushes a new node with the given operator into the stack.
    public void push(char operator) {
        super.insertAfterHead(operator);
        top = head.getNext();
        topIndex++;
    }

    //MODIFIES: this
    //EFFECTS: pops and returns the top node if specified as such
    public ExpressionNode pop(boolean shouldReturn) {
        ExpressionNode temp = new ExpressionNode();

        temp = super.deleteAfterHead(shouldReturn);

        topIndex--;
        top = head.getNext();

        return temp;
    }


    //EFFECTS: returns topIndex
    public int getTopIndex() {
        return topIndex;
    }

    //EFFECTS: returns the top node of the stack.
    public ExpressionNode getTop() {
        return top;
    }


    @Override
    //EFFECTS: returns true if the stack is empty.
    public boolean isEmpty() {
        //return topIndex == -1;
        return head.getNext() == null;
    }
}
