package model;

public class ExpressionLinkedList<ExpressionNode> extends SingleLinkedList{
    protected ExpressionNode head;

    public ExpressionLinkedList() {
        this.head = new ExpressionNode();
        this.listCount = 0;
    }

    @Override
    public ExpressionNode getHead() {
        return head;
    }

}
