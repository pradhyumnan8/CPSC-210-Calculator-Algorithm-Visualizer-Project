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
- A toggleable stack visualizer that shows data flow through the stack at each point.  
  

Possible list of extra features:
- algebraic solver
- bitwise solver
- tree-based algorithm visualization. 

## User Stories:

- As a user, I want to add my current calculation to a history of calculations.
- As a user, I want to edit my history or clear it all-together. 
- As a user, I want to calculate the mean of my history of calculations. 
- As a user, I want to calculate expressions of any length using the five basic functions (+, -, /, *, ^) and brackets.
- As a user, I want to perform as many calculations as I want without having to rerun the program.
- As a user, I want to be prompted to save my history of calculations before quitting the application if I so wish to do so.
- As a user, I want to be prompted to re-load my history of calculations from my previous save if I wish to do so.


CITATIONS:
- JSON saving and loading file implementations were heavily inspired by the example "JsonSerializationDemo" program. 
