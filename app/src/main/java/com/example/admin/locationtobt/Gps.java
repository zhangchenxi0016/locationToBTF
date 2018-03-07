package com.example.admin.locationtobt;

/**
 * Created by admin on 2018/3/6.
 */

public class Gps {
    public double lat;
    public double lon;
    Gps(double lat,double lon){
        this.lat=lat;
        this.lon=lon;
    }
    public double getLon(){
        return lon;
    }
    public double getLat(){
        return lat;
    }
}
