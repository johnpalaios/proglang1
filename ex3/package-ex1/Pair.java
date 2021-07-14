package com.package1;
import java.util.Arrays;
import java.io.*;
import java.lang.*;
import java.util.*;


public class Pair {

    public  List<Integer> first = new ArrayList<Integer>();
    public  List<Integer> second = new ArrayList<Integer>();

    // Constructs a new pair with specified values
    public Pair(List<Integer> first, List<Integer> second) {
        this.first = first;
        this.second = second;
    }
    public void printPair() {
        System.out.print("(");
        if(first.size() > 0) {
            System.out.print("[");
            for (int i = 0; i < first.size() - 1; i++) {
                System.out.print(first.get(i) + ", ");
            }
            System.out.print(first.get(first.size() - 1));
            System.out.print("]");
        }
        else {
            System.out.print("Empty");
        }
        System.out.print(", ");
        if(second.size() > 0) {
            System.out.print("[");
            for (int i = 0; i < second.size() - 1; i++) {
                System.out.print(second.get(i) + ", ");
            }
            System.out.print(second.get(second.size() - 1));
            System.out.print("]");
        }
        else {
            System.out.print("Empty");
        }
        System.out.print(")");
    }
    public Pair(Pair another) {
        this.first = another.first;
        this.second = another.second;
    }
}
    //@Override
    // Checks specified object is "equal to" the current object or not
    /*
    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Pair<?, ?> pair = (Pair<?, ?>) o;

        // call `equals()` method of the underlying objects
        if (!first.equals(pair.first)) {
            return false;
        }
        return second.equals(pair.second);
    }
    */
    //@Override
    // Computes hash code for an object to support hash tables
   /* public int hashCode()
    {
        // use hash codes of the underlying objects
        return 31 * first.hashCode() + second.hashCode();
    }

    public String toString() {
        String firstStr,secondStr;
        // itr = first.iterator();
        firstStr = first.toString();
        secondStr = second.toString();

        return "(" + firstStr + ", " + secondStr + ")";
    }

    // Factory method for creating a typed Pair immutable instance
    /*
    public static <U, V> Pair <U, V> of(U a, V b)
    {
        // calls private constructor
        return new Pair<>(a, b);
    }*/
   /* public static void printPair() {
        System.out.println(Arrays.toString(intArray) );
    }*/

