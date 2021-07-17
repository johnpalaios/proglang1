package com.round;

public class ZMethods {
    public static int[] findDistance(int[] carInCity, int[] oneFinalPosition) {
        int size = carInCity.length;
        int distanceArray[] = new int[size];
        for(int i = 0; i < size; i++) {
            int difference = oneFinalPosition[i] - carInCity[i];
            if(difference >= 0) {
                distanceArray[i] = difference;
            }
            else {
                distanceArray[i] = size + difference;
            }
        }
        return distanceArray;
    }
}
