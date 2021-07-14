package com.package6;
import java.util.ArrayList;
import java.util.List;

public class ZStack {
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