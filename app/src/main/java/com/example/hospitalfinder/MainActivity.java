package com.example.hospitalfinder;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import static com.example.hospitalfinder.Hospital.createHospitalArray;
import static java.lang.Float.MAX_VALUE;

public class MainActivity extends AppCompatActivity {

    private TextView AddressText;
    private LocationRequest locationRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find button and the Text in res/layout/activity_main.xml
        Button findHospital = findViewById(R.id.findHospitalButton);
        //AddressText = findViewById(R.id.user_loc_text);                     //find location textView box in res/layout/activity_main.xml

        //this function executes when the Find Hospital button is pressed
        findHospital.setOnClickListener(new View.OnClickListener() {        //build a new anonymous class, onClick listener is an interface
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                //if app has access to user location
                if (getApplicationContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                    //Create a LocationRequest object and set its properties, it is used to find user's location
                    locationRequest = LocationRequest.create();
                    locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                    locationRequest.setInterval(1000);
                    locationRequest.setFastestInterval(1000);

                    //this keeps track of the user's location
                    LocationServices.getFusedLocationProviderClient(MainActivity.this).requestLocationUpdates(locationRequest, new LocationCallback() {
                        @Override
                        public void onLocationResult(@NonNull LocationResult locationResult) {
                            super.onLocationResult(locationResult);
                            LocationServices.getFusedLocationProviderClient(MainActivity.this).removeLocationUpdates(this);
                        }
                    }, Looper.getMainLooper());


                    LocationManager mlocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    Location locationGPS = mlocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                    Location userLocation = new Location("User Current Location");

                    userLocation.setLatitude(locationGPS.getLatitude());
                    userLocation.setLongitude(locationGPS.getLongitude());

                    //load hospital data from Hospital.java
                    Hospital[] HospitalInfo = createHospitalArray();

                    //get the distance between each hospital and the user, add it as a field to the hospital
                    for (Hospital hospital: HospitalInfo) {
                        Location hospitalLocaiton = new Location("Hospital Location");
                        hospitalLocaiton.setLatitude(hospital.getLatitude());
                        hospitalLocaiton.setLongitude(hospital.getLongitude());

                        float distance = userLocation.distanceTo(hospitalLocaiton);
                        hospital.setDistanceToUser(distance);
                    }

                    //if there are no available hospital, the value in nearestHospital will not change
                    Hospital nearestHospital = new Hospital("There are no available hospitals in your area", 0, 0, false);
                    nearestHospital.setDistanceToUser(MAX_VALUE);

                    //identify the hospital with available beds and is nearest to the user
                    for (Hospital hospital: HospitalInfo) {
                        if (hospital.gethasCapacity() && nearestHospital.getDistanceToUser() > hospital.getDistanceToUser()) {
                            nearestHospital = hospital;
                        }
                    }

                    TextView outputTextView = (TextView) findViewById(R.id.closestHospital);

                    outputTextView.setText(nearestHospital.getName());      //show the nearest hospital name on the screen



                } else {
                    //app does not have location permission, request permission from user, requestPermissions() takes arrays of permission as input
                    String[] permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION};
                    requestPermissions(permissions, 1);

                }
            }
        });
    }

    //this executes when requesting access to the user's location
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {     //Manifest.permission.ACCESS_FINE_LOCATION is in the first index of grantResults
                Toast.makeText(MainActivity.this, "Location Permission Granted", Toast.LENGTH_SHORT);
            } else {
                Toast.makeText(MainActivity.this, "Location Permission Denied", Toast.LENGTH_SHORT);
            }
        }
    }

}