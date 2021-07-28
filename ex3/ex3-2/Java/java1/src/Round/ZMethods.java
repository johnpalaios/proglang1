package Round;

public class ZMethods {
    public static int[] findDistance(int[] carInCity, int[] oneFinalPosition
            ,int currentCity, int numberOfCities) {
        // for cities behind a car some things change
        int size = carInCity.length;
        int distanceArray[] = new int[size];
        int difference;
        for(int i = 0; i < size; i++) {
            if(carInCity[i] > currentCity) {
                difference = numberOfCities - carInCity[i] + oneFinalPosition[i];
            }
            else {
                difference = oneFinalPosition[i] - carInCity[i];
            }

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
        int returnArray[] = new int[2];
        returnArray[0] = 0;
        for(int i = 0; i< size; i++) {
            if(returnArray[0] < sums[i]) {
                if(2 * maxes[i] < sums[i] + 2) {
                    returnArray[0] = sums[i];
                    returnArray[1] = i;
                    break;
                }
            }
        }
        // 2 * maxes[i] - sums[i] < 2
        for(int i = 0; i< size; i++) {
            if(returnArray[0] > sums[i]) {
                if(2 * maxes[i] < sums[i] + 2) {
                    returnArray[0] = sums[i];
                    returnArray[1] = i;
                }
            }
        }
        return returnArray;
    }
}