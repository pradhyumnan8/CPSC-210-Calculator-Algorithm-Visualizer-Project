package model;

public class SingleLinkedListStack<Thing> extends SingleLinkedList<Thing> {

    private int topIndex;
    private ListNode<Thing> top;

    public SingleLinkedListStack() {
        this.topIndex = -1;
        this.top = head;
    }

    public void push(ListNode<Thing> element) {
        super.insertAfterHead(element);
        topIndex++;
    }

    public void push(Thing data) {
        super.insertAfterHead(data);
        topIndex++;
    }

    public void pop() {
         super.deleteAfterHead();
         topIndex--;
    }

    public ListNode<Thing> pop(boolean shouldReturn) {
        topIndex--;
        return super.deleteAfterHead(shouldReturn);
    }

    public int getTopIndex() {
        return topIndex;
    }

    public ListNode<Thing> getTop() {
        return top;
    }

    public void setTop(ListNode<Thing> top) {
        this.top = top;
    }


    //WARNING TO DELETE
    public void printStack() {
        top = head;

        while (top.getNext() != null) {
            System.out.println(top.getNext().getData());

            top = top.getNext();
        }
    }
}
