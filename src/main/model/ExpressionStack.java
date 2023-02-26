package model;

public class ExpressionStack extends ExpressionLinkedList {

    private int topIndex;
    private ExpressionNode top;

    public ExpressionStack() {
        this.topIndex = -1;
        this.top = head.getNext();

        head.setOperator('h');
        head.setOperand(-99);
    }


    //pushes an operand
    public void push(double operand) {
        super.insertAfterHead(operand);
        top = head.getNext();
        topIndex++;
    }


    //pushes an operator
    public void push(char operator) {
        super.insertAfterHead(operator);
        top = head.getNext();
        topIndex++;
    }

    //pops and returns the top node if specified as such
    public ExpressionNode pop(boolean shouldReturn) {
        topIndex--;
        top = head.getNext();
        return super.deleteAfterHead(shouldReturn);
    }

    //returns topIndex
    public int getTopIndex() {
        return topIndex;
    }

    //returns top
    public ExpressionNode getTop() {
        return top;
    }

    @Override
    public boolean isEmpty() {
        //return topIndex == -1;
        return head.getNext() == null;
    }

    //WARNING TO DELETE
    public void printStack() {
        ExpressionNode temp = head.getNext();

        if (this.isEmpty()) {
            System.out.println("The stack is empty");
            return;
        }

        while (temp != null) {
            System.out.printf("[%c, %f]\n", temp.getOperator(), temp.getOperand());

            temp = temp.getNext();
        }
    }










}
