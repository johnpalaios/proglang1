package com.package6;
import java.lang.*;
import java.util.*;
public class Zpair {
    public  List<Integer> first = new ArrayList<Integer>();
    public  List<Integer> second = new ArrayList<Integer>();

    // Constructs a new pair with specified values
    public Zpair(List<Integer> first, List<Integer> second) {
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
    public Zpair(Zpair another) {
        this.first = another.first;
        this.second = another.second;
    }
}
