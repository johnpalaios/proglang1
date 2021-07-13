package com.package1;

import java.util.ArrayList;
import java.util.List;

public class Stack {
    public static List<Integer> stackList = new ArrayList<Integer>();
    private static int size = stackList.size();

    public static void push(int item) {
        stackList.add(item);
        return;
    }

    public static int pop() {
        int lastValue = stackList.get(stackList.size()-1);
        stackList.remove(stackList.size()-1);
        return lastValue;
    }

    public static void printStack() {
        if (stackList.size() > 0) {
            System.out.print("[");
            for (int i = 0; i < stackList.size() - 1; i++) {
                System.out.print(stackList.get(i) + ", ");
            }
            System.out.print(stackList.get(stackList.size() - 1));
            System.out.print("]");
            System.out.println();
        }
        else {
            System.out.println("Empty Stack");
        }
    }

    public static String stackString() {
        /*
        String resultString = "[";
        for (int i = 0; i < size-1; i++) {
            resultString =+ queueList.get(i) + ", ";
        }
        resultString += queueList.get(size-1) ;
        resultString += "]" ;
        return resultString;
        */
        return stackList.toString();
    }
    public static boolean isEmpty() {
        if(stackList == null) {
            return true;
        }
        return false;
    }
}



/*public class Stack {
    // store elements of stack
    public static int arr[];
    // represent top of stack
    private int top;
    // total capacity of the stack
    private int capacity;

    // Creating a stack
    Stack(int size) {
        // initialize the array
        // initialize the stack variables
        arr = new int[size];
        capacity = size;
        top = -1;
    }

    // push elements to the top of stack
    public void push(int x) {
        if (isFull()) {
            System.out.println("Stack OverFlow");

            // terminates the program
            System.exit(1);
        }

        // insert element on top of stack
        System.out.println("Inserting " + x);
        arr[++top] = x;
    }

    // pop elements from top of stack
    public int pop() {

        // if stack is empty
        // no element to pop
        if (isEmpty()) {
            System.out.println("STACK EMPTY");
            // terminates the program
            System.exit(1);
        }

        // pop element from top of stack
        return arr[top--];
    }

    // return size of the stack
    public int getSize() {
        return top + 1;
    }

    // check if the stack is empty
    public Boolean isEmpty() {
        return top == -1;
    }

    // check if the stack is full
    public Boolean isFull() {
        return top == capacity - 1;
    }

    // display elements of stack
    public void printStack() {
        for (int i = 0; i <= top; i++) {
            System.out.print(arr[i] + ", ");
        }
    }
}*/
