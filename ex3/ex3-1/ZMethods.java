package com.package6;
import java.util.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class ZMethods {
    public static List<Integer> listCopyOfIntegers(List<Integer> copy) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < copy.size(); i++) {
            list.add(copy.get(i));
        }
        return list;
    }
   /* public static void listCopyOfStrings(List<String> copy, List<String> paste) {
        for (int i = 0; i < paste.size(); i++) {
            paste.remove(i);
        }
        for (int i = 0; i < copy.size(); i++) {
            paste.add(copy.get(i));
        }
    }*/
    public static void copyStringBuffer(List<String> copy, List<String> paste) {

        for(int i = 0; i < paste.size(); i++) {
            paste.remove(i);
        }
        for(int i = 0; i < copy.size(); i++) {
            paste.add(copy.get(i));
        }
    }

    public static List<Integer> returnSorted(List<Integer> list) {
        List<Integer> sortedlist = new ArrayList<Integer>(list);
        Collections.sort(sortedlist);
        return sortedlist;
    }

    public static boolean isSorted(List<Integer> list) {
        List<Integer> sortedlist = new ArrayList<Integer>(list);
        Collections.sort(sortedlist);
        return list.equals(sortedlist);
    }

    public static void printHashSet(HashSet<Zpair> hs) {
        System.out.print("{ ");
        for (Zpair zpair : hs) {
            zpair.printPair();
            System.out.print(", ");
        }
        System.out.println("}");
    }

    public static void printHashMap(HashMap<String, Zpair> stores) {
        System.out.println("{");
        for ( Map.Entry<String, Zpair> e : stores.entrySet()) {
            Zpair p = e.getValue();
            System.out.print( "'" + e.getKey() + "' : ");
            p.printPair();
            System.out.println();
        }
        System.out.println("}");
    }

    public static void printList(List<?> list) {
        if(list.size() > 0) {
            System.out.print("[");
            for (int i = 0; i < list.size() - 1; i++) {
                System.out.print(list.get(i) + ", ");
            }
            System.out.print(list.get(list.size() - 1));
            System.out.print("]");
            System.out.println();
        }
        else {
            System.out.println("Empty List");
        }
    }

    public static StringBuffer stringPop( List<String> q ) {
        StringBuffer returnSB = new StringBuffer(q.get(0));
        q.remove(0);
        return returnSB;
    }

    public static int stackPop(List<Integer> list) {

        int returnInteger = list.get(list.size()-1);
        list.remove(list.size()-1);
        return returnInteger;
    }

    public static int dequeue(List<Integer> list) {

        //int returnInteger = list.get(0);

        int returnInteger = list.remove(0); // error for some reason
        //list may be immutable but it isn't!!!
        return returnInteger;
    }

    public static boolean moveQ1 (List<Integer> q, List<Integer> s) {
        if(q.isEmpty()) {
            return false;
        }
        else {

            int dequeueValue = dequeue(q);
            s.add(dequeueValue);
            return true;
        }
    }

    public static boolean moveS1 (List<Integer> q, List<Integer> s) {
        if(s.isEmpty()) {
            return false;
        }
        else {
            q.add(stackPop(s));
            return true;
        }
    }

    //public static int executeCounter = 0;
    public static void execute(StringBuffer path, HashMap<String, Zpair> stores
            , HashSet<Zpair> visited, List<String> q111
            , List<Integer> sortedQueue) {

        //System.out.println("  " + executeCounter++);
        //System.out.println("This is the path : " + path);
        int size = path.length(); // size of current path
        StringBuffer oldPath = new StringBuffer(path.substring(0,size-1));

        Zpair tuple = stores.get(oldPath.toString()); // (q1,s1) = stores[oldPath]
        List<Integer> q = new ArrayList<Integer>(tuple.first); // q = q1.copy()
        List<Integer> s = new ArrayList<Integer>(tuple.second); // s = s1.copy()

        char qORs = path.charAt(size - 1);

        if (qORs == 'Q') {
            if (!moveQ1(q, s)) {
                return;
            }
        } else if (qORs == 'S') {
            if (!moveS1(q, s)) {
                return;
            }
        }
        if (size % 2 == 0) {
            if (q.equals(sortedQueue)) {
                //printList(q);
                //printList(s);
                System.out.println(path);
                System.exit(1);
            }
        }

        Zpair tuple1 = new Zpair(ZMethods.listCopyOfIntegers(q)
                , ZMethods.listCopyOfIntegers(s));

        stores.put(path.toString(), tuple1);
        if (!visited.contains(tuple1)) {
            visited.add(tuple1);
            if (q.size() != 0 && s.size() != 0) {
                if (q.get(0) == s.get(s.size() - 1)) {
                    q111.add(path + "Q");
                    return;
                }
            }
            if (q.size() != 0) {
                q111.add(path + "Q");
            }
            if (s.size() != 0) {
                q111.add(path + "S");
            }
            //printList(q111);
        }
    }


    public static void breadthFirstSearch (HashMap<String, Zpair> stores,
                                           HashSet<Zpair> visited,
                                           List<Integer> sortedQueue) {
        // maintain a queue of paths
        List<String> q = new ArrayList<String>();
        q.add("Q");
        // k=0
        //int k = 0;
        while(q.size() != 0) {
            //if(k == 21) {
            //    System.exit(20);
            // }
            List<StringBuffer> path = new ArrayList<StringBuffer>(); // this is the return string
            List<String> q1 = new ArrayList<String>();
            int size = q.size();
            //System.out.println(k);
            for(int i = 0; i < size; i++) {
                //printList(q);
                path.add(ZMethods.stringPop(q));
                execute(path.get(i), stores, visited, q1, sortedQueue);
            }
            copyStringBuffer(q1,q);
            //k++;
        }
    }
}
