package ui;

import model.CalculatorHistory;
import model.ExpressionEvaluator;

public class Main {

    public static void main(String[] args) {
        ExpressionEvaluator calculator = new ExpressionEvaluator();
        InputOutput userInput = new InputOutput();
        CalculatorHistory history = new CalculatorHistory();
        String expression;
        double result = 0;

        expression = userInput.scanExpression();

        result = calculator.calculate(expression);

        System.out.println(result);
    }
}

//        char test;
//
//        ExpressionLinkedList infix = new ExpressionLinkedList();
//        ExpressionLinkedList postfix = new ExpressionLinkedList();
//        ExpressionStack stack = new ExpressionStack();
//
//        //infix.printList();
//
//        infix.insertAtEnd(2);
//        infix.insertAtEnd('*');
//        infix.insertAtEnd('(');
//        infix.insertAtEnd(2);
//        infix.insertAtEnd('+');
//        infix.insertAtEnd(2);
//        infix.insertAtEnd(')');
//
//        ExpressionNode temp = infix.getHead().getNext();
//
//        infix.printList();
//
//       // System.out.println(temp.getOperator());
//       // System.out.println(temp.getOperand());
//
//        postfix.insertAtEnd(temp.getOperand());
//        temp = temp.getNext();
//
//        stack.push(temp.getOperator());
//        temp = temp.getNext();
//
//        stack.push(temp.getOperator());
//        temp = temp.getNext();
//
//        postfix.insertAtEnd(temp.getOperand());
//        temp = temp.getNext();
//
//        stack.push(temp.getOperator());
//        temp = temp.getNext();
//
//        postfix.insertAtEnd(temp.getOperand());
//        temp = temp.getNext();
//
//       // System.out.println(temp.getOperand() + "  " + temp.getOperator());
//        postfix.insertAtEnd(stack.pop(true).getOperator());
//        temp = temp.getNext();
//
//        System.out.println();
//        stack.printStack();
//
//        System.out.printf("%c, %f\n", stack.getTop().getOperator(), stack.getTop().getOperand());
//
//        while(stack.getTop().getOperator() != '(') {
//            //System.out.printf("%c, %f\n", stack.getTop().getOperator(), stack.getTop().getOperand());
//            if (stack.getTop().getOperator() == '(') {
//               // postfix.insertAtEnd(stack.pop(true).getOperator());
//                break;
//
//            }
//            postfix.insertAtEnd(stack.pop(true).getOperator());
//
//        }
//
//
//
//        postfix.insertAtEnd(stack.pop(true).getOperator());
//
//        System.out.println();
//        postfix.printList();
//        System.out.println();
//        stack.printStack();
//=================================================================================================================
//        char test;
//
//        ExpressionLinkedList infix = new ExpressionLinkedList();
//        LinkedList<ExpressionNode> postfix = new LinkedList<>();
//        Stack<ExpressionNode> stack = new Stack<>();
//        //infix.printList();
//
//        infix.insertAtEnd(2);
//        infix.insertAtEnd('*');
//        infix.insertAtEnd('(');
//        infix.insertAtEnd(2);
//        infix.insertAtEnd('+');
//        infix.insertAtEnd(2);
//        infix.insertAtEnd(')');
//
//        ExpressionNode temp = infix.getHead().getNext();
//
//        infix.printList();
//
//        // System.out.println(temp.getOperator());
//        // System.out.println(temp.getOperand());
//
//        postfix.add(temp);
//        temp = temp.getNext();
//
//        stack.push(temp);
//        temp = temp.getNext();
//
//        stack.push(temp);
//        temp = temp.getNext();
//
//        postfix.add(temp);
//        temp = temp.getNext();
//
//        stack.push(temp);
//        temp = temp.getNext();
//
//        postfix.add(temp);
//        temp = temp.getNext();
//
//        // System.out.println(temp.getOperand() + "  " + temp.getOperator());
//        postfix.add(stack.pop());
//        temp = temp.getNext();
//
//        //System.out.println();
//        //stack.printStack();
//
//      //  System.out.printf("%c, %f\n", stack.getTop().getOperator(), stack.getTop().getOperand());
//
//        while (stack.peek().getOperator() != '(') {
//            postfix.add(stack.pop());
//        }
//
///*        while(stack.getTop().getOperator() != '(') {
//
//            //System.out.printf("%c, %f\n", stack.getTop().getOperator(), stack.getTop().getOperand());
//            if (stack.getTop().getOperator() == '(') {
//                // postfix.insertAtEnd(stack.pop(true).getOperator());
//                break;
//
//            }
//            postfix.insertAtEnd(stack.pop(true).getOperator());
//
//        }*/
//
//        postfix.add(stack.pop());
//        postfix.add(stack.pop());
//
//        System.out.println(postfix.getLast().getOperator());
//
//        /*System.out.println();
//
//
//        Object post = postfix.toArray();
//        //int i = 0;
//        String[] postfixArray = postfix.toArray(new String[0]);
//
//        System.out.println("Contents of the array: \n"+Arrays.toString(postfixArray));*/
//
//        //ExpressionNode forPrint = postfixArray[0];
//        //String [] stringArray = new String[postfix.size()];
///*
//        for(int i =0; i < postfix.size(); i++) {
//            stringArray[i] = (String) postfixArray[i];
//        }
//
//        System.out.println("Contents of the array: \n"+Arrays.toString(stringArray));*/
//
//
//        /*while (i < postfix.size()) {
//            System.out.printf("[%c, %f]  ", postfixArray[i].getOperator(), forPrint.getOperand());
//            i++;
//            postfix = postfix.
//        }*/
//
//
//       /* postfix.
//        System.out.println(postfix.toString());*/
//
//        //postfix.printList();
//        //System.out.println();
//        //stack.printStack();
//



//=================================================================================================================




        /*while (true) {
            test = stack.getTop().getOperator();

            if (test == '(') {
                break;
            }

            postfix.insertAtEnd(stack.pop(true).getOperator());
        }*/

        //stack.printStack();
        //postfix.printList();


        //  temp = temp.getNext();

        //postfix.insertAtEnd(stack.pop(true).getOperator());




        /*ExpressionStack stack = new ExpressionStack();

        stack.push(2);
        stack.push('*');
        stack.push(3);

        stack.pop(false);
        stack.pop(false);
        stack.pop(false);

        stack.printStack();*/

        /*//*String str = "12.34";
        BigDecimal dec = new BigDecimal(str);

        System.out.println(dec);*/

        /*ExpressionLinkedList list = new ExpressionLinkedList();

        list.insertAtEnd(12);
        list.insertAtEnd('+');
        list.insertAtEnd(23);
        list.insertAtEnd('+');
        list.insertAtEnd(2);

        list.printList();*/
//    }
//}


//============================================================================
//DOUBLE AND STRING TEST
/*String snum = "12.34";

        double dnum = Double.parseDouble(snum);

        System.out.println(dnum);


        String test = "";
        char c = 'a';

        test = test + c;

        System.out.println(test);

        //test = test + "hello";
*//*
        System.out.println(test);

        test = test + " " + "world";

        System.out.println(test);*/

// NEW STACK TEST
/*ExpressionStack myStack = new ExpressionStack();

        myStack.printStack();

        myStack.push(10);
        myStack.push(20);
        myStack.push(30);

        myStack.printStack();*/

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