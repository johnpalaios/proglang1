package com.round;

import java.io.*;
import java.util.*;

public class Round {

    public static void main(String[] args) {
        String nameOfFile = args[0];
        try {
            //FileReader myObj = new FileReader(nameOfFile);
            //BufferedReader myReader = new BufferedReader(myObj);
            File myObj = new File(nameOfFile);
            Scanner myReader = new Scanner(myObj); // file = myObj to be scanned


            int numberOfCities = myReader.nextInt(); // numberOfCities == N
            int numberOfCars = myReader.nextInt(); // numberOfCars == K
            //System.out.println(numberOfCities);
            //System.out.println(numberOfCars);

            //System.out.println(i);
            //System.out.println(firstLine);
            //System.out.println(secondLine);

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
                    differenceArray[i] = ZMethods.findDistance(carInCity, finalPositions[i]
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
                sumOfDifferenceArray[i] = ZMethods.findSumOfList(differenceArray[i]);
            }

            int maxOfDifferenceArray[] = new int[numberOfCities];
            for(int i = 0; i < numberOfCities; i++) {
                maxOfDifferenceArray[i] = ZMethods.findMaxOfList(differenceArray[i]);
            }
            /*
            System.out.println("This is the list of sums "
                              + Arrays.toString(sumOfDifferenceArray));
            System.out.println("This is the list of maxes "
                    + Arrays.toString(maxOfDifferenceArray));*/
            System.out.println("This is the result : "
                    + Arrays.toString(ZMethods.findCityAndSteps(sumOfDifferenceArray,maxOfDifferenceArray)));

            int result[] = ZMethods.findCityAndSteps(sumOfDifferenceArray,maxOfDifferenceArray);
            System.out.print(result[0]);
            System.out.print(" ");
            System.out.println(result[1]);


        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
