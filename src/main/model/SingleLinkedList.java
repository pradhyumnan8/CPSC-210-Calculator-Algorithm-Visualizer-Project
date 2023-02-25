package model;

import java.util.List;

public class SingleLinkedList<Thing> {

    protected ListNode<Thing> head;
    private int listCount;

    public SingleLinkedList() {
        this.head = new ListNode<Thing>();
        this.listCount = 0;
    }


    //inserts given node after head.
    public void insertAfterHead(ListNode<Thing> node) {
        head.setNext(node);
        listCount++;
    }

    //makes and inserts a node after head with the given data
    public void insertAfterHead(Thing data) {
        ListNode<Thing> node = new ListNode<>();
        node.setData(data);

        node.setNext(head.getNext());
        head.setNext(node);
        listCount++;
    }

    //inserts a node at the end
    public void insertAtEnd(ListNode<Thing> node) {
        ListNode<Thing> temp = head;

        while (temp.getNext() != null) {
            temp = temp.getNext();
        }

        temp.setNext(node);
        listCount++;
    }

    //inserts a new node at the end
    public void insertAtEnd(Thing data) {
        ListNode<Thing> node = new ListNode<>();
        ListNode<Thing> temp = head;

        node.setData(data);
        node.setNext(null);

        while (temp.getNext() != null) {
            temp = temp.getNext();
        }

        temp.setNext(node);
        listCount++;
    }

    //deletes the node after head
    public void deleteAfterHead() {
        head.setNext(head.getNext().getNext());
        listCount--;
    }

    //deletes and returns the node after head
    public ListNode<Thing> deleteAfterHead(boolean shouldReturn) {
        ListNode<Thing> temp = head.getNext();
        head.setNext(head.getNext().getNext());
        listCount--;

        return temp;
    }

    public int getListCount() {
        return listCount;
    }

    public ListNode<Thing> getHead() {
        return head;
    }

    public void setHead(ListNode<Thing> head) {
        this.head = head;
    }

    //returns true if the list is empty
    public boolean isEmpty() {
        if (head.getNext() == null) {
            return true;
        } else {
            return false;
        }
    }

    //WARNING TO DELETE
    //prints all the data in the list
    public void printList() {
        ListNode<Thing> temp = head.getNext();

        if (this.isEmpty()) {
            System.out.println("the list is empty");
        } else {
            while (temp != null) {
                System.out.print(temp.getData() + " ");

                temp = temp.getNext();
            }
        }

        System.out.print("\n");
    }
}

