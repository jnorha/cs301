#### Date: 05/21/25

## Week 1 Notes
- homework due wednesday midnight of next week

#### Class Structure
- class is two basic concepts:
    - Object oriented programming in java
    - Data structure and algorithms

``` 
goofy
    jdisn
```

### Getting into it
- ``` main() ``` is the entrance of a Java application
- has to be all lowercase for the initial application call
- ```println()``` is a method that print ou the string and changes a line
- java statements always end in ";" (except for while or for loop...)
- ``` System.out ``` is a PrintStream object
- two main methods:
    - print (prints without changing the line)
    - println (prints text and change a line)
- ``` System.in ``` is an input object
- scanner class provides input facility to accommodate various input routine
- could also use fileobject or network object as system in 
- we use the ``` next ``` method to collect a **single** word which is returned after an **ENTER**
- (400 level class for user interface)
- ``` nextline ``` method:
    - do multiple ``` .next() ``` in consecutive lines
- additional data types:
    - ```nextInt```
    - ```nextDouble```
- DecimalFOrmat object lets you define the number of decimal accuracy
- formatting in Table
    - ```System.out.printf```
    - takes collection of arguments to specify the formatting

### Objects & Class
- a "Class" refers to a template or blueprint
    - Ex: Vehicle
        - wheels and engine
- Object is an instance of a class
    - Sedan, truck, suv etc

- **Attributes**: data variables which dtermine the status of the class or object
- **Methods**: executable code used to manipulate / change the status of an object or access the value of the data member
    - similar to *functions*

private string setColor 
<br>
(modifier, type, method)
<br>
(Look at slides :/)

- visibility modifiers
    - **public** and **private** designate the accessiblity of data members and methods
        - **private** members or methods cannot be accessed by client classes
        - declare teh attributes private
        - declare the methods public so we can access the attributes\
        - sometimes we use private methods to limit access
            - benefits on encapsulation and security
        
- a special return type is **void**
    - can return nothing

- parameter is a local variable in the called method to hold the value of the passed argument
    - its a placeholder
- an argument is a value we pass to a method
<br>

N below is an argument

<br>

``` int combination = factorial (N) / (factorial(K)*factorial(N-K)); ```
<br>

**constructor** is a special method that is executed when a new instance of the class is created (this.color = myColor, etc)

<br>

``` 
public Pencil (String myColor, double width) {
    this.color = myColor
    this.diameter = width
}
```

<br>

In constructor there **is no return type** : no void, no string, no int

<br>

- if you forget to create a constructor, the system will make one for you
- Dont set things you return from methods as initial attributes
- usually inputs are done in your main class
- print or final return usually its own method
