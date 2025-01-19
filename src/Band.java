/*
 *  Title: Assignment 2 - Band Manager
 *  Created by: Robert Norlander
 *  Email: shamotar@csp.edu
 *  Date: 2025-01-19
 *  Class: CSC 420 - Data Structures and Algorithms
 *  Professor: Susan Furtney
 */

public class Band {
    private String _bandName;
    private Float _setTime;

    public Band(String bandName, Float setTime) {
        this._bandName = bandName;
        this._setTime = setTime;
    }

    public String getBandName() {
        return _bandName;
    }

    public void setBandName(String bandName) {
        this._bandName = bandName;
    }

    public Float getSetTime() {
        return _setTime;
    }

    public void setSetTime(Float setTime) {
        this._setTime = setTime;
    }

    @Override
    public String toString() {
        return _bandName + " has a set time of " +  _setTime + " minutes";
    }
}
