/*
 *  Title: Assignment 2 - Band Manager
 *  Created by: Robert Norlander
 *  Email: shamotar@csp.edu
 *  Date: 2025-01-19
 *  Class: CSC 420 - Data Structures and Algorithms
 *  Professor: Susan Furtney
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BandManager {
    private ArrayList<Band> _bands = new ArrayList<Band>();

    public void addBand(Band band) {
        _bands.add(band);
    }

    public void removeBand(Band band) {
        _bands.remove(band);
    }

    public ArrayList<Band> getBands() {
        return _bands;
    }

    /*
     * @param pathName - the path to the file to load the band data from
     * @throws FileNotFoundException - if the file is not found
     * @description - Load band data from a file and add it to the band manager
     */
    public void loadFromFile(String pathName) throws FileNotFoundException {
        File bandDataFile = new File(pathName);
        Scanner bandDataScanner = new Scanner(bandDataFile);

        while (bandDataScanner.hasNextLine()) {
            String bandData = bandDataScanner.nextLine();
            String[] bandDataTokens = bandData.split("\\|");
            Band band = new Band(bandDataTokens[0], Float.parseFloat(bandDataTokens[1]));
            this.addBand(band);
        }
        bandDataScanner.close();
    }

    /*
     * @description - Sort the bands by name in ascending order
     */
    public void sortByName() {
        for (int i = 0; i < _bands.size(); i++) {
            for (int j = i + 1; j < _bands.size(); j++) {
                if (_bands.get(i).getBandName().compareTo(_bands.get(j).getBandName()) > 0) {
                    Band temp = _bands.get(i);
                    _bands.set(i, _bands.get(j));
                    _bands.set(j, temp);
                }
            }
        }
    }

    /*
     * @description - Sort the bands by set time in ascending order
     */
    public void sortBySetTime() {
        for (int i = 0; i < _bands.size(); i++) {
            for (int j = i + 1; j < _bands.size(); j++) {
                if (_bands.get(i).getSetTime() > _bands.get(j).getSetTime()) {
                    Band temp = _bands.get(i);
                    _bands.set(i, _bands.get(j));
                    _bands.set(j, temp);
                }
            }
        }
    }

    /*
     * @param bandName - the name of the band to search for
     * @param low - the lower bound of the search
     * @param high - the upper bound of the search
     * @return Band - the band that was found
     * @throws IllegalArgumentException - if the band is not found
     * @description - Search for a band by name using a binary search algorithm
     */
    public Band searchBandByName(String bandName, int low, int high) {
        if (high >= low) {
            int mid = low + (high - low) / 2;

            System.out.println(_bands.get(mid).getBandName());

            if (_bands.get(mid).getBandName().equals(bandName)) {
                return _bands.get(mid);
            }

            if (_bands.get(mid).getBandName().compareTo(bandName) > 0) {
                return searchBandByName(bandName, low, mid - 1);
            }

            return searchBandByName(bandName, mid + 1, high);
        }

        // Throw an exception if the band is not found
        throw new IllegalArgumentException("Band [" + bandName + "] was not found");
    }

    /*
     * @param setTime - the set time to search for
     * @return Band - the band with the closest set time
     * @description - Search for a band by set time and return the closest band
     */
    public Band searchBandBySetTime(Float setTime) {
        for (Band band : _bands) {
            if (band.getSetTime().equals(setTime)) {
                return band;
            }
        }

        // If band is not found, get the closest band with the closest set time
        Band closestBand = _bands.get(0);
        for (Band band : _bands) {
            if (Math.abs(band.getSetTime() - setTime) < Math.abs(closestBand.getSetTime() - setTime)) {
                closestBand = band;
            }
        }
        return closestBand;
    }
}
