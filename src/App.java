/*
 *  Title: Assignment 2 - Band Manager
 *  Created by: Robert Norlander
 *  Email: shamotar@csp.edu
 *  Date: 2025-01-19
 *  Class: CSC 420 - Data Structures and Algorithms
 *  Professor: Susan Furtney
 */

import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Created and submitted by: Robert Norlander - shamotar@csp.edu");
        System.out.println("I certify that this is my own work");

        // Generate a Band Manager and load data from file
        BandManager bandManager = new BandManager();
        bandManager.loadFromFile("src\\static\\bandinfo.txt");

        // Initialize scanners
        Scanner searchTypeScanner = new Scanner(System.in);
        Scanner bandNameScanner = new Scanner(System.in);
        Scanner setTimeScanner = new Scanner(System.in);

        System.out.println("Welcome to the Band Manager Tool!");

        while (true) {
            // Get user input for search type
            System.out.println("Search by Band Name (1) or Set Time (2) or Exit (3):");
            int searchType = searchTypeScanner.nextInt();
            if (searchType == 1) {
                // Sort the bands by name
                bandManager.sortByName();
                
                // Get user input for band name
                System.out.println("Enter the band name you are looking for:");
                String bandName = bandNameScanner.nextLine();
                try {
                    Band band = bandManager.searchBandByName(bandName, 0, bandManager.getBands().size() - 1);
                    System.out.println("Band is found: " + band.toString());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (searchType == 2) {
                // Sort the bands by set time
                bandManager.sortBySetTime();

                // Get user input for set time
                System.out.println("Enter the set time:");
                Float setTime = Float.parseFloat(setTimeScanner.nextLine());
                
                Band band = bandManager.searchBandBySetTime(setTime);
                System.out.println("Band with the closest time is: " + band.toString());
            } else if (searchType == 3) {
                System.out.println("Thank you for using the Band Manager tool! Goodbye!");
                break;
            } else {
                System.out.println("Invalid search type, please try again");
            }
        }

        // Close the scanners
        searchTypeScanner.close();
        bandNameScanner.close();
        setTimeScanner.close();
    }
}
