package com.example.hospitalfinder;

public class Hospital {
    private String name;
    private double longitude;
    private double latitude;
    private boolean hasCapacity;        //if the hospital has capacity
    private float distanceToUser;       //calculated in MainActivity.java

    public Hospital(String name, double longitude, double latitude, boolean hasCapacity) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.hasCapacity = hasCapacity;
    }
    public String getName() {
        return name;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public boolean gethasCapacity() {
        return hasCapacity;
    }

    public float getDistanceToUser() {
        return distanceToUser;
    }

    public void setCapacity(boolean hasCapacity) {
        this.hasCapacity = hasCapacity;
    }

    public void setDistanceToUser(float distanceToUser) {
        this.distanceToUser = distanceToUser;
    }

    //all hospital data
    public static Hospital[] createHospitalArray() {
        Hospital MontrealGeneral = new Hospital("Montreal General Hospital", -73.587702, 45.496639, true);
        Hospital CHUM = new Hospital("CHUM",-73.557569,45.511077, true);
        Hospital JeanTalon = new Hospital("Jean Talon Hospital", -73.608767,45.546147, true);
        Hospital MontrealHeart = new Hospital("Montreal Heart Institute",  -73.579030,45.573254, true);

        Hospital[] HospitalsInfo = new Hospital[]{MontrealGeneral, CHUM, JeanTalon, MontrealHeart};

        return HospitalsInfo;
    }

}
