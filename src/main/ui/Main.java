package ui;

import model.ExpressionLinkedList;
import model.ExpressionStack;

public class Main {

    public static void main(String[] args) {
        ExpressionStack myStack = new ExpressionStack();

        myStack.printStack();

        myStack.push(10);
        myStack.push(20);
        myStack.push(30);

        myStack.printStack();


    }
}


//============================================================================
// NEW LINKED LIST TEST
/*ExpressionLinkedList myList = new ExpressionLinkedList();

        myList.printList();

       *//* myList.insertAfterHead(10);
        myList.insertAfterHead(20);
        myList.insertAfterHead(30);

        myList.deleteAfterHead(false);*//*

        myList.insertAtEnd(10);
        myList.insertAtEnd(20);
        myList.insertAtEnd(30);


        myList.printList();*/
//============================================================================
//SOME LINKED LIST GENERIC TESTING

        /*SingleLinkedList<ExpressionNode> infixTest = new SingleLinkedList<>();
        ExpressionNode temp = infixTest.getHead().getNext();

        temp.setOperand(4);
        temp.setOperator('+');

        System.out.println(((ExpressionNode) infixTest.getHead().getNext()).getOperand());
        System.out.println(((ExpressionNode) infixTest.getHead().getNext()).getOperator());

       *//* System.out.println(((ExpressionNode) infixTest.getHead().getNext()).getNext().getOperand());
        System.out.println(((ExpressionNode) infixTest.getHead().getNext()).getNext().getOperator());*/
//============================================================================
//TESTING EXPRESSION VALIDATION
        /*Input userInput = new Input();
       // ExpressionEvaluator calculator = new ExpressionEvaluator();
        String expression;

        expression = userInput.getExpression();*/
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