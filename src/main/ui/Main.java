package ui;

import model.ExpressionEvaluator;
import model.ListNode;
import model.SingleLinkedList;

public class Main {

    public static void main(String[] args) {
        Input userInput = new Input();
       // ExpressionEvaluator calculator = new ExpressionEvaluator();
        String expression;

        expression = userInput.getExpression();
    }
}


//============================================================================
//JAVA CHARACTER & STRING TEST
/*String testString = "123";
        char[] testCharArray = "123".toCharArray();

        System.out.println(testString.charAt(0));
        System.out.println(testCharArray[0]);

    //    System.out.println();

        System.out.println(testString.length());
        System.out.println(testCharArray.length);


        //System.out.println();

        for (int i = 0; i < testString.length(); i++) {
            System.out.print(testString.charAt(i));
        }

        System.out.println();

        for (int i = 0; i < testCharArray.length; i++) {
            System.out.print(testCharArray[i]);
        }

      //  System.out.println();*/
//============================================================================
     /* //TESTING ADDING TO THE END OF THE LINKED LIST
        SingleLinkedList<Integer> myList = new SingleLinkedList<>();
        SingleLinkedList<ListNode> nodeList = new SingleLinkedList<>();

        ListNode<Integer> n1 = new ListNode<>(1);
        ListNode<Integer> n2 = new ListNode<>(2);
        ListNode<Integer> n3 = new ListNode<>(3);

        myList.printList();

        myList.insertAtEnd(1);
        myList.insertAtEnd(2);
        myList.insertAtEnd(3);

        myList.printList();


        nodeList.insertAtEnd(n1);
        nodeList.insertAtEnd(n2);
        nodeList.insertAtEnd(n3);

        myList.printList();

        //nodeList.printList();

     //   System.out.println(myList.getHead().getNext().getNext().getNext().getData());*/

//============================================================================


/*
        //TESTING INPUT
        Input userInput = new Input();

        userInput.getExpression();*/
//============================================================================

//TESTING PUSH AND POP
 /* LinkedListStack<Integer> myStack = new LinkedListStack<Integer>();

        ListNode<Integer> n1 = new ListNode<Integer>(1);
        ListNode<Integer> n2 = new ListNode<Integer>(2);
        ListNode<Integer> n3 = new ListNode<Integer>(3);

        myStack.push(n1);
        myStack.push(n2);
        myStack.push(n3);

        myStack.push(4);
        myStack.push(5);
        myStack.push(6);

        System.out.println("Top Index =  " + myStack.getTopIndex());

        myStack.printStack();

        myStack.pop();
        myStack.pop();

        System.out.println("After Popping");

        System.out.println("Top Index =  " + myStack.getTopIndex());
        myStack.printStack();*/

//============================================================================
//MISC

 /*public void printStackInMain(LinkedListSta    ck stack) {
        while (stack.getTop() != null) {
            System.out.println(stack.getTop().getData());

            stack.setTop(stack.getTop().getNext());
        }
    }*/

//============================================================================