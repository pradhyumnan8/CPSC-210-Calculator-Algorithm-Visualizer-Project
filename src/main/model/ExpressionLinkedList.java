package model;

public class ExpressionLinkedList {
    protected ExpressionNode head;
    protected int listCount;

    public ExpressionLinkedList() {
        this.head = new ExpressionNode();
        this.listCount = 0;
    }


    //makes and inserts a node after head with the given operand (number)
    public void insertAfterHead(double operand) {
        ExpressionNode node = new ExpressionNode();
        node.setOperand(operand);

        node.setNext(head.getNext());
        head.setNext(node);
        listCount++;
    }

    //makes and inserts a node after head with the given operator
    public void insertAfterHead(char operator) {
        ExpressionNode node = new ExpressionNode();
        node.setOperator(operator);

        node.setNext(head.getNext());
        head.setNext(node);
        listCount++;
    }

    //makes and inserts a node at the end with the given operand (number)
    public void insertAtEnd(double operand) {
        ExpressionNode node = new ExpressionNode();
        ExpressionNode temp = head;

        node.setOperand(operand);
        node.setNext(null);

        while (temp.getNext() != null) {
            temp = temp.getNext();
        }

        temp.setNext(node);
        listCount++;
    }

    //makes and inserts a node at the end with the given operator
    public void insertAtEnd(char operator) {
        ExpressionNode node = new ExpressionNode();
        ExpressionNode temp = head;

        node.setOperator(operator);
        node.setNext(null);

        while (temp.getNext() != null) {
            temp = temp.getNext();
        }

        temp.setNext(node);
        listCount++;
    }

    //deletes and returns the node after head if specified
    public ExpressionNode deleteAfterHead(boolean shouldReturn) {
        ExpressionNode temp = head.getNext();

        if (this.isEmpty()) {
            return null;
        }

        head.setNext(head.getNext().getNext());
        listCount--;

        if (shouldReturn) {
            return temp;
        } else {
            return null;
        }
    }

    //returns listCount
    public int getListCount() {
        return listCount;
    }

    //returns head
    public ExpressionNode getHead() {
        return head;
    }

    //sets head
    public void setHead(ExpressionNode head) {
        this.head = head;
    }


    //returns true if the list is empty
    public boolean isEmpty() {
        return head.getNext() == null;
    }

    //WARNING TO DELETE
    //prints all the data in the list
    public void printList() {
        ExpressionNode temp = head.getNext();

        if (this.isEmpty()) {
            System.out.println("the list is empty");
        } else {
            while (temp != null) {
                System.out.printf("[%c %f]  ", temp.getOperator(), temp.getOperand());

                temp = temp.getNext();
            }
        }

        System.out.print("\n");
    }



}
