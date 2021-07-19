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

    public static int findSumOfList(int[] list) {
        int size = list.length;
        int sum = 0;
        for(int i = 0; i< size; i++) {
            sum =sum + list[i];
        }
        return sum;
    }

    public static int findMaxOfList(int[] list) {
        int size = list.length;
        int max = list[0];
        for(int i = 1; i< size; i++) {
            if(max < list[i]) {
                max = list[i];
            }
        }
        return max;
    }

    public static int[] findCityAndSteps(int[] sums, int[] maxes) {
        int size = sums.length;
        int currentSum = 0;
        int returnArray[] = new int[2];

        for(int i = 0; i< size; i++) {
            if(currentSum < sums[i]) {
                if(maxes[i] + 2 < sums[i]) {
                    returnArray[0] = sums[i];
                    returnArray[1] = i;
                    break;
                }
            }
        }
        for(int i = 0; i< size; i++) {
            if(currentSum > sums[i]) {
                if(maxes[i] + 2 < sums[i]) {
                    returnArray[0] = sums[i];
                    returnArray[1] = i;
                    break;
                }
            }
        }
        return returnArray;
    }
}
