#### Date: 5/28/2025

## CS 300 Week 2 notes

#### Main Class Methods

- code in the main method tends to be long
- some code might be repreated
- good to add methods to main class for modularity
- method in main need to have static properties since they are not bound to any class

#### Parameter Passing

Pass by Value (call by value) 
- function gets a copy of the callers arguments. Changes to the copy to not effect the caller.
<br>
Java passes primitives by value

- this means a copy of the primitive is passed into a method
- only the copy of the primitive is changed in a method, the original is not

```
public class examples {

	public static void swap(int a, int b) {
		int temp = a;
		a = b;
		b = temp;
	}
	public static void main(String [] args){
		int a = 10; int b = 20;
		swap(a,b);
		System.out.print("b = "+b);
		// b is still 20 even after the change in value method is used. copy of b INSIDE the method is what is changed to 10
	}

}

```

output:
b = 20

<bf>

- A mthod can pass objects by reference
- a method can change the object that the parameter refers to. 
<br>
Lets say we have this example person, Tatum:
130 lbs, 5 6 etc
- This one will change the object:

```
	public static void changeObject(Person person) {
		person.setName("Sam");
		person.setWeight(125);
		person.setHeightFeet(5);
		person.setHeightInches(9);
		
	}
```
- This one will not change the object:
```
	public static void changeObject2(Person person) {
		person = new Person("Sam", 128,5,9);
	}
```
- this is becasue in the first one person and person1 are pointing to the same object address
- the second code block creates a new person object address

### More in depth example in the "labs/personnel" project

## File Input/Output

- a file is saved on disk/drive
- array from program is stored in memory
- file is permament, memory is temporary
- memory is faster access than disk
- output from one program can be input to another for file input/output

- to operate on a file, we need to create a File object (from java.io package)

``` File <variable name> = new File(<filename>);```

- File class is useful for getting information about a file
- **low level:** treat a file as a set of bytes
- **high level:** treat file as a set of data with primitive Data type
- **text file:** treat file as set of text (or String)

- text files are more human readable
- binary files are much faster, java binary files are exportable

- to read data from or write data to a file we must create one of the java **stream** objects attach it to the file

Low Level Data Output
- create a File object
- create a FileOutputStream object
- prepare the data
- write data to stream
- close the stream

To write data to a text file
- create file object
- create fileoutputstream object
- create a printerwriter object
- write lines
- close file

To Read data from a text file
create file object
create filereader object
create a bufferedreader object
create //grab from slides goddamn

Write File
- PrintWriter(filename) will open the file and erase the contents before writing new contest
- FileWriter and Files class provides options for appending instead of overwriting records
```FileWriter(``` **File**``` file, boolean append) ```

