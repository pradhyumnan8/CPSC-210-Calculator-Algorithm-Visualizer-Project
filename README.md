# Expression Evaluator
## An expression evaluator with stack visualizer.   

I am designing an expression evaluator that will be used to visualize the flow of data through data structures 
such as a linked list   
and a stack. As the program converts from the user inputted string to an infix list to a postfix list and finally to a solved result, the user  
will be able to visualize all the data flow (in the final version of the application). 

I started this project because as I was exploring the world of data structure I found that they were not always   
represented clearly and certainly could not be visualized with user-inputted data. I intend to expand this project  
in the future to cover new algorithms as I learn them and build a cohesive algorithm/data structure visualizer. 

These plans may not be fully realized in the context of this course, and certainly not in the early phases of  
the project but progress will be evident in the later phases of the project.



**A list of features the application will include:**   
- Expression solver with support for basic functions (+, -, /, *, ^). Single number calculations are not supported right 
now (eg asking what is the result of "2" or "-2").
- A history function that will allow you to view and select previous calculations 
- Statistical functions (mean, median etc.) that can be run on your history.
  

**Possible list of extra features:**
- algebraic solver
- bitwise solver
- A toggleable stack visualizer that shows data flow through the stack at each point.
- tree-based algorithm visualization. 

## User Stories:

- As a user, I want to add my current calculation to a history of calculations.
- As a user, I want to edit my history or clear it all-together. 
- As a user, I want to calculate the mean of my history of calculations. 
- As a user, I want to calculate expressions of any length using the five basic functions (+, -, /, *, ^) and brackets.
- As a user, I want to perform as many calculations as I want without having to rerun the program.
- As a user, I want to be able to save and load the state of the application.


## Instructions to use GUI:
1. Click Play
2. Wait for start up screen to finish loading. 
3. Use the calculator as you would any other calculator 
4. Press the history button for history actions such as view, delete, clear, calculating mean etc. 
   1. Press one of the buttons on the pop-up to complete that action, following prompts on screen.
5. You can press the "save" or "load" buttons anytime to save/load your history.
6. NOTE: Must press AC after any message (eg. "invalid expression", "history cleared" etc.)


## Phase 4 Task 3 Design Refactoring

There are a few things that I would refactor if given extra time. The first thing that comes to mind is the choice of
ArrayLists, LinkedLists, and Stacks used throughout the program. The choices are very inconsistent. When I started phase 1, I wanted  
to learn how to create my own data structures starting from simple ones such as linked lists and stacks. I started by creating  
the ExpressionNode class and wanted to implement this in a generic singly linked list (again, created by me) and extend that  
off to a stack. The resulting stack and linked list were to be used anywhere appropriate, removing the need for Java's built in  
ArrayList, LinkedList, or Stack functionalities. However, as I experimented with the idea, a lot of it did not work (especially the  
generic functionality) so I have had to end up using built in Java functionality in many places (although my classes are used as   
well.) I now know how to fully implement my original idea and would do so if given time. Of course, from a purely design   
perspective, using the built in functionality is better but this refactoring is to fulfill my original vision for this project. 

The second refactoring I might do is related to the implementation of printing the log to the console when the user exits the  
application. To implement this functionality, I have currently made my GUI class implement WindowListener. However, this   
method currently requires a bunch of empty method implementations from the WindowListener Interface as I only make use of   
the windowDeactivated() method. The refactoring I would do is to declare my current GUI class as abstract, then I would only have  
to implement the windowDeactivated() method there. I would then create another GUI class which would extend my current   
(made abstract) class. The new class would be practically empty and just be used for instantiation in  Main.main().




## Attributions:
- JSON saving and loading file implementations were heavily inspired by the example "JsonSerializationDemo" program.
- Splash Screen Picture link: https://www.shutterstock.com/image-illustration/welcome-screen-illustration-260nw-1639480804.jpg
- Video used to learn Java Swing: https://www.youtube.com/watch?v=dfhmTyRTCSQ&t=1367s
- Discussion used to learn how to scroll a JTextArea: https://stackoverflow.com/questions/8849063/adding-a-scrollable-jtextarea-java
- Discussion used to learn how to introduce a delay: https://stackoverflow.com/questions/24104313/how-do-i-make-a-delay-in-java
- Used Event, EventLog classes and printLog method from given alarm system example. 
- Learnt WindowListener working from given website (Oracle). 
