package com.example.gus.places.model;

/**
 * Created by Laotshi on 11/16/15.
 */
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Item implements Serializable {

    private String cityName;
    private String locationId;
    private String displayName;
    private String locationType;
    private String hotelType;
    private boolean poi;
    private String airport;
    private String country;
    private String lat;
    private String lon;


    /**
     *
     * @return
     * The cityName
     */
    public String getCityName() {
        return cityName;
    }

    /**
     *
     * @param cityName
     * The cityName
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     *
     * @return
     * The locationId
     */
    public String getLocationId() {
        return locationId;
    }

    /**
     *
     * @param locationId
     * The locationId
     */
    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    /**
     *
     * @return
     * The displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     *
     * @param displayName
     * The displayName
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     *
     * @return
     * The locationType
     */
    public String getLocationType() {
        return locationType;
    }

    /**
     *
     * @param locationType
     * The locationType
     */
    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    /**
     *
     * @return
     * The hotelType
     */
    public String getHotelType() {
        return hotelType;
    }

    /**
     *
     * @param hotelType
     * The hotelType
     */
    public void setHotelType(String hotelType) {
        this.hotelType = hotelType;
    }

    /**
     *
     * @return
     * The poi
     */
    public boolean isPoi() {
        return poi;
    }

    /**
     *
     * @param poi
     * The poi
     */
    public void setPoi(boolean poi) {
        this.poi = poi;
    }

    /**
     *
     * @return
     * The airport
     */
    public String getAirport() {
        return airport;
    }

    /**
     *
     * @param airport
     * The airport
     */
    public void setAirport(String airport) {
        this.airport = airport;
    }

    /**
     *
     * @return
     * The country
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     * @param country
     * The country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     *
     * @return
     * The lat
     */
    public String getLat() {
        return lat;
    }

    /**
     *
     * @param lat
     * The lat
     */
    public void setLat(String lat) {
        this.lat = lat;
    }

    /**
     *
     * @return
     * The lon
     */
    public String getLon() {
        return lon;
    }

    /**
     *
     * @param lon
     * The lon
     */
    public void setLon(String lon) {
        this.lon = lon;
    }

}
