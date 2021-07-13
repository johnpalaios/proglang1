package com.package1;
import java.util.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Methods {
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
    public static void printHashSet(HashSet<Pair> hs) {
        System.out.print("{ ");
        for (Pair pair : hs) {
            pair.printPair();
            System.out.print(", ");
        }
        System.out.println("}");
    }

    public static void printHashMap(HashMap<String, Pair> stores) {
        System.out.println("{");
        for ( Map.Entry<String, Pair> e : stores.entrySet()) {
            Pair p = e.getValue();
            System.out.print( "'" + e.getKey() + "' : ");
            p.printPair();
            System.out.println();
        }
        System.out.println("}");
    }

    public static boolean moveQ (Queue q, Stack s) {
        if(q.isEmpty()) {
            return false;
        }
        else {
            //s.push(q.queueDequeue());
            s.push(q.dequeue());
            return true;
        }
    }

    public static void printList(List<Integer> list) {
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

    public static boolean moveS (Queue q, Stack s) {
        if(s.isEmpty()) {
            return false;
        }
        else {
            q.enqueue(s.pop());
            return true;
        }
    }

    public static char stringPop(StringBuffer stringBuffer) {
        char returnCharacter = stringBuffer.charAt(0);
        stringBuffer.delete(0, 1);
        return returnCharacter;
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

    static int dequeueCounter = 0;
    public static boolean moveQ1 (List<Integer> q, List<Integer> s) {
        if(q.isEmpty()) {
            return false;
        }
        else {
            //s.push(q.queueDequeue());
            System.out.println("This is the counter : " + dequeueCounter++);
            //System.out.print("This is q : " );
            //printList(q);
            //System.out.print("This is s : " );
            //printList(s);
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
            System.out.println("This is the counter : " + dequeueCounter++);
            //System.out.print("This is q : " );
            //printList(q);
            //System.out.print("This is s : " );
            //printList(s);
            q.add(stackPop(s));
            return true;
        }
    }

    public static void execute(StringBuffer path, HashMap<String, Pair> stores
                               , HashSet<Pair> visited, StringBuffer q111
                               , List<Integer> sortedQueue) {

        int size = path.length(); // size of current path
        StringBuffer oldPath = new StringBuffer(path.substring(0,size-1));

        Pair tuple = stores.get(oldPath.toString()); // (q1,s1) = stores[oldPath]
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
                  printList(q);
                  printList(s);
                  System.out.println(path);
                  System.exit(1);
            }
        }

        Pair tuple1 = new Pair(List.copyOf(q)
                , List.copyOf(s));
        stores.put(path.toString(), tuple1);
        if (!visited.contains(tuple1)) {
            visited.add(tuple1);
            if (!q.isEmpty() && !s.isEmpty()) {
                if (q.get(0) == s.get(s.size() - 1)) {
                    q111.append(path + "Q");
                    return;
                }
            }
           if (!q.isEmpty()) {
               q111.append(path + "Q");
           }
           if (!s.isEmpty()) {
                q111.append(path + "S");
            }
        }
    }

    public static void breadthFirstSearch (HashMap<String, Pair> stores,
                                           HashSet<Pair> visited,
                                           List<Integer> sortedQueue) {
        // maintain a queue of paths
        StringBuffer q = new StringBuffer("Q");
        // k=0
        while(!q.isEmpty()) {
            int size = q.length();
            StringBuffer path = new StringBuffer(); // this is the return string
            StringBuffer q1 = new StringBuffer();
            for(int i = 0; i < size; i++) {
                path.append(Methods.stringPop(q));
                execute(path, stores, visited, q1, sortedQueue);
            }
            q = q1;
        }

    }
}
