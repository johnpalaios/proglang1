package com.package1;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.*;

public class Queue {

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





/*
public class Queue {
    private static int front, rear, capacity;
    public static int queue[];

    public Queue(int size) {
        front = rear = 0;
        capacity = size;
        queue = new int[capacity];
    }

    // insert an element into the queue
    static void queueEnqueue(int item)  {
        // check if the queue is full
        if (capacity == rear) {
            System.out.printf("\nQueue is full\n");
            return;
        }

        // insert element at the rear
        else {
            queue[rear] = item;
            rear++;
        }
        return;
    }

    //remove an element from the queue
    static int queueDequeue()  {
        int initialFront = queue[0];
        // check if queue is empty
        if (front == rear) {
            System.out.printf("\nQueue is empty\n");
            return -40000000;
        }
        // shift elements to the right by one place uptil rear
        else {
            for (int i = 0; i < rear - 1; i++) {
                queue[i] = queue[i + 1];
            }


            // set queue[rear] to 0
            if (rear < capacity)
                queue[rear] = 0;

            // decrement rear
            rear--;
        }
        return initialFront;
    }

    // print queue elements
    static void queueDisplay()
    {
        int i;
        if (front == rear) {
            System.out.printf("Queue is Empty\n");
            return;
        }

        // traverse front to rear and print elements
        for (i = front; i < rear; i++) {
            System.out.printf("%d ", queue[i]);
        }
        System.out.print("\n");

    }

    // print front of queue
    static void queueFront()
    {
        if (front == rear) {
            System.out.printf("Queue is Empty\n");
            return;
        }
        System.out.printf("\nFront Element of the queue: %d", queue[front]);
        return;
    }
    public static boolean isSorted(Queue q) {
        int[] sortedq = q.queue.clone();
        Arrays.sort(sortedq);
        return Arrays.equals(sortedq,q.queue);
    }
    public static boolean isEmpty() {
        if(queue == null) {
            return true;
        }
        return false;
    }
}*/