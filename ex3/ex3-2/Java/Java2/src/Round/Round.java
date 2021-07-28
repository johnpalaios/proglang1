package Round;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Round {
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
    public static void main(String[] args) {
        String nameOfFile = args[0];
        try {
            File myObj = new File(nameOfFile);
            Scanner myReader = new Scanner(myObj); // file = myObj to be scanned
            int numberOfCities = myReader.nextInt(); // numberOfCities == N
            int numberOfCars = myReader.nextInt(); // numberOfCars == K
            int carInCity[] = new int[numberOfCars]; // carInCity[Car Number]
            // = City in Which the Car is Located
            for(int i = 0; i < numberOfCars; i++) {
                carInCity[i] = myReader.nextInt();
                if(carInCity[i] > numberOfCities) {
                    System.out.println("You entered an invalid City number");
                }
            }
            int finalPositions[][] = new int[numberOfCities][numberOfCars];
            for(int i = 0; i < numberOfCities; i++) {
                for(int j = 0; j < numberOfCars; j++) {
                    finalPositions[i][j] = i;
                }
            }
            int differenceArray[][] = new int[numberOfCities][numberOfCars];
            for(int i = 0; i < numberOfCities; i++) {
                differenceArray[i] = findDistance(carInCity, finalPositions[i]
                        ,i, numberOfCities);
            }
            //differenceArray contains the difference of FinalCityNumber - StartingCarInCityNumber
            //System.out.println(Arrays.deepToString(differenceArray));
            //check if differenceArray is correct

            //1.All that is left is to find the max & sum
            //for every pair(finalPositions - carInCity)
            //2.Find which max & sum pairs are accepted
            //3.Find min and the accepted sum & it's index
            //4.Return that solution

            int sumOfDifferenceArray[] = new int[numberOfCities];
            for(int i = 0; i < numberOfCities; i++) {
                sumOfDifferenceArray[i] = findSumOfList(differenceArray[i]);
            }

            int maxOfDifferenceArray[] = new int[numberOfCities];
            for(int i = 0; i < numberOfCities; i++) {
                maxOfDifferenceArray[i] = findMaxOfList(differenceArray[i]);
            }
            /*
            System.out.println("This is the list of sums "
                              + Arrays.toString(sumOfDifferenceArray));
            System.out.println("This is the list of maxes "
                    + Arrays.toString(maxOfDifferenceArray));
            System.out.println("This is the result : "
                    + Arrays.toString(ZMethods.findCityAndSteps(sumOfDifferenceArray,maxOfDifferenceArray)));
            */
            int result[] = findCityAndSteps(sumOfDifferenceArray,maxOfDifferenceArray);
            System.out.print(result[0]);
            System.out.print(" ");
            System.out.println(result[1]);

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}