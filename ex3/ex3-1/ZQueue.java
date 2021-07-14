package com.package6;
import java.util.*;
public class ZQueue {
    public static List<Integer> queueList = new ArrayList<Integer>();
    private static int size = queueList.size();

    public static void enqueue(int item) {
        queueList.add(item);
        return;
    }

    public static int dequeue() {
        int firstValue = queueList.get(0);
        queueList.remove(0);
        return firstValue;
    }

    public static void printQueue() {
        if(queueList.size() > 0) {
            System.out.print("[");
            for (int i = 0; i < queueList.size() - 1; i++) {
                System.out.print(queueList.get(i) + ", ");
            }
            System.out.print(queueList.get(queueList.size() - 1));
            System.out.print("]");
            System.out.println();
        }
        else {
            System.out.println("Empty Queue");
        }
    }

    public static String queueString() {
        /*
        String resultString = "[";
        for (int i = 0; i < size-1; i++) {
            resultString =+ queueList.get(i) + ", ";
        }
        resultString += queueList.get(size-1) ;
        resultString += "]" ;
        return resultString;
        */
        return queueList.toString();
    }
    public static boolean isEmpty() {
        if(queueList == null) {
            return true;
        }
        return false;
    }
}
