package com.package1;
import java.io.*;
import java.util.Scanner;
import java.io.File;
import java.util.HashMap;
import java.util.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        String nameOfFile = "qs1.txt";
        try {
            File myObj = new File(nameOfFile);
            Scanner myReader = new Scanner(myObj); // file = myObj to be scanned
            int size = myReader.nextInt();
            Queue q = new Queue();
            Stack s = new Stack();

            while (myReader.hasNextInt()) {
                int data = myReader.nextInt();
                Queue.enqueue(data);
            }
            if(Queue.isEmpty()) {
                System.out.println("Queue is empty");
                return;
            }
            if(Methods.isSorted(Queue.queueList)) {
                System.out.println("empty");
            }
            HashSet<Pair> visited = new HashSet<>(); // visited : HashSet of Pairs

            HashMap<String, Pair> stores
                    = new HashMap<String, Pair>();   // stores : HashMap of 'Path' : Pair

            //Queue.printQueue();
            //Stack.printStack();

            // tuple = ([17, 3, 42, 7], Empty)
            Pair tuple = new Pair(List.copyOf(Queue.queueList)
                                 , List.copyOf(Stack.stackList));
            List<Integer> sortedQueue = new ArrayList<Integer>(
                    Methods.returnSorted(Queue.queueList)
            );
            visited.add(tuple);
            stores.put("",tuple);

            //Methods.printHashSet(visited);
            Methods.moveQ1(Queue.queueList,Stack.stackList);

            // tuple1 = ([3, 42, 7], [17])
            Pair tuple1 = new Pair(List.copyOf(Queue.queueList)
                    , List.copyOf(Stack.stackList));

            stores.put("Q",tuple1);

            Methods.breadthFirstSearch (stores, visited, sortedQueue);

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
