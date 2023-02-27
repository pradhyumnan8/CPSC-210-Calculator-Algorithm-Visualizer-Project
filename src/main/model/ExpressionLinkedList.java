package model;

//Linked list of ExpressionNode
public class ExpressionLinkedList {
    protected ExpressionNode head;
    protected int listCount;

    public ExpressionLinkedList() {
        this.head = new ExpressionNode();
        this.listCount = 0;
    }


    //EFFECTS: makes and inserts a node after head with the given operand (number)
    public void insertAfterHead(double operand) {
        ExpressionNode node = new ExpressionNode();
        node.setOperand(operand);

        node.setNext(head.getNext());
        head.setNext(node);
        listCount++;
    }


    //EFFECTS: makes and inserts a node after head with the given operator
    public void insertAfterHead(char operator) {
        ExpressionNode node = new ExpressionNode();
        node.setOperator(operator);

        node.setNext(head.getNext());
        head.setNext(node);
        listCount++;
    }

    //EFFECTS: makes and inserts a node at the end with the given operand (number)
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

    //EFFECTS: makes and inserts a node at the end with the given operator
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

    //EFFECTS: deletes and returns the node after head if specified
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

    //EFFECTS: returns listCount
    public int getListCount() {
        return listCount;
    }

    //EFFECTS: returns head
    public ExpressionNode getHead() {
        return head;
    }

    //EFFECTS: sets head
    public void setHead(ExpressionNode head) {
        this.head = head;
    }

    //EFFECTS: returns true if the list is empty
    public boolean isEmpty() {
        return head.getNext() == null;
    }

    //EFFECTS: clears the list and makes the head point to null again.
    public void clearList() {
        head.setNext(null);
        listCount = 0;
    }





}
