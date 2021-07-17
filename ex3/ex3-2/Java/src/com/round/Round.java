package com.round;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Round {

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
                    differenceArray[i] = ZMethods.findDistance(carInCity, finalPositions[i]);
            }
            //differenceArray contains the difference of FinalCityNumber - StartingCarInCityNumber
            System.out.println(Arrays.deepToString(differenceArray));

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
