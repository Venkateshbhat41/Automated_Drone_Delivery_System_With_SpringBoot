package fi.sade23.pizzeria.monitoringservice;

import java.time.LocalDateTime;

public class DroneFlight {

    private String targetLatitude;
    private String targetLongitude;
    private String currentLatitude; 
    private String currentLongitude; 
    private double currentAltitude; 
    private double currentSpeed; 
    private LocalDateTime lastUpdateTime; 

    public DroneFlight() {
        // Default constructor
    }

    public DroneFlight(String targetLatitude, String targetLongitude) {
        this.targetLatitude = targetLatitude;
        this.targetLongitude = targetLongitude;
     
    }

    public String getTargetLatitude() {
        return targetLatitude;
    }

    public void setTargetLatitude(String targetLatitude) {
        this.targetLatitude = targetLatitude;
    }

    public String getTargetLongitude() {
        return targetLongitude;
    }

    public void setTargetLongitude(String targetLongitude) {
        this.targetLongitude = targetLongitude;
    }

    public String getCurrentLatitude() {
        return currentLatitude;
    }

    public void setCurrentLatitude(String currentLatitude) {
        this.currentLatitude = currentLatitude;
    }

    public String getCurrentLongitude() {
        return currentLongitude;
    }

    public void setCurrentLongitude(String currentLongitude) {
        this.currentLongitude = currentLongitude;
    }

    public double getCurrentAltitude() {
        return currentAltitude;
    }

    public void setCurrentAltitude(double currentAltitude) {
        this.currentAltitude = currentAltitude;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(double currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public LocalDateTime getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(LocalDateTime lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

}
