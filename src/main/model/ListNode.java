package model;

public class ListNode<Thing> {

    private Thing data;
    private ListNode<Thing> next;

    public ListNode() {
        this.next = null;
    }

    public ListNode(Thing data) {
        this.data = data;
        this.next = null;
    }

    public Thing getData() {
        return data;
    }

    public void setData(Thing data) {
        this.data = data;
    }

    public ListNode<Thing> getNext() {
        return next;
    }

    public void setNext(ListNode<Thing> next) {
        this.next = next;
    }
}
