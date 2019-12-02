import java.util.*;

public class Stack
{
	private int arr[];
	private int top;
	private int capacity;

	// Constructor to initialize stack
	public Stack(int size)
	{
		arr = new int[size];
		capacity = size;
		top = -1;
	}

	// Utility function to add an element x in the stack
	public void push(int x)
	{
		if (isFull())
		{
			System.out.println("OverFlow\nProgram Terminated\n");
			System.exit(1);
		}

		System.out.println("Inserting " + x);
		arr[++top] = x;
	}

	// Utility function to pop top element from the stack
	public int pop()
	{
		// check for stack underflow
		if (isEmpty())
		{
			System.out.println("UnderFlow\nProgram Terminated");
			System.exit(1);
		}

		System.out.println("Removing " + peek());

		// decrease stack size by 1 and (optionally) return the popped element
		return arr[top--];
	}

	// Utility function to return top element in a stack
	public int peek()
	{
		if (!isEmpty())
			return arr[top];
		else
			System.exit(1);

		return -1;
	}

	// Utility function to return the size of the stack
	public int size()
	{
		return top + 1;
	}

	// Utility function to check if the stack is empty or not
	public boolean isEmpty()
	{
		return top == -1;	// or return size() == 0;
	}

	// Utility function to check if the stack is full or not
	public boolean isFull()
	{
		return top == capacity - 1;	// or return size() == capacity;
    }
    
    @Override
    public String toString() {
        String output = "Stack is: [ ";
        for(int i = 0; i < size(); i++) {
            output += arr[i] + " ";
        }
        output += "]\n";
        return output;
    }

	public static void main (String[] args)
	{
		Stack stack = new Stack(3);
        System.out.println();
        stack.push(1);
        System.out.println(stack);
        stack.push(2);
        System.out.println(stack);

        stack.pop();
        System.out.println(stack);
        stack.pop();
        System.out.println(stack);

        stack.push(3);
        System.out.println(stack);

		System.out.println("Top element is: " + stack.peek());
		System.out.println("Stack size is " + stack.size());

        stack.pop();
        System.out.println(stack);

		// check if stack is empty
		if (stack.isEmpty())
			System.out.println("Stack Is Empty\n");
		else
			System.out.println("Stack Is Not Empty\n");
	}
}