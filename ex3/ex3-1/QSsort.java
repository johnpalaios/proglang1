package com.package6;

import java.io.*;
import java.util.*;

public class QSsort {
    public static void main(String[] args) {
        String nameOfFile = args[0];
        try {
            File myObj = new File(nameOfFile);
            Scanner myReader = new Scanner(myObj); // file = myObj to be scanned
            int size = myReader.nextInt();
            ZQueue q = new ZQueue();
            ZStack s = new ZStack();

            while (myReader.hasNextInt()) {
                int data = myReader.nextInt();
                ZQueue.enqueue(data);
            }
            if(ZQueue.isEmpty()) {
                System.out.println("Queue is empty");
                return;
            }
            if(ZMethods.isSorted(ZQueue.queueList)) {
                System.out.println("empty");
                System.exit(1);
            }
            HashSet<Zpair> visited = new HashSet<>(); // visited : HashSet of Pairs

            HashMap<String, Zpair> stores
                    = new HashMap<String, Zpair>();   // stores : HashMap of 'Path' : Pair

            //Queue.printQueue();
            //Stack.printStack();

            // tuple = ([17, 3, 42, 7], Empty)
            Zpair tuple = new Zpair(ZMethods.listCopyOfIntegers(ZQueue.queueList)
                    , ZMethods.listCopyOfIntegers(ZStack.stackList));
            List<Integer> sortedQueue = new ArrayList<Integer>(
                    ZMethods.returnSorted(ZQueue.queueList)
            );
            visited.add(tuple);
            stores.put("",tuple);

            //Methods.printHashSet(visited);
            ZMethods.moveQ1(ZQueue.queueList, ZStack.stackList);

            // tuple1 = ([3, 42, 7], [17])
            Zpair tuple1 = new Zpair(ZMethods.listCopyOfIntegers(ZQueue.queueList)
                    , ZMethods.listCopyOfIntegers(ZStack.stackList));

            stores.put("Q",tuple1);

            ZMethods.breadthFirstSearch (stores, visited, sortedQueue);

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}