package com.example.jseppa2742ex2g;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Objects;

public class Sensor {
    private int sensorId;
    private int roomId;
    private int sensorTypeId;
    private String description;
//    private Room room;
//    private SensorType sensorType;
    private ArrayList<SensorReading> sensorReadings;

    public Sensor(int sensorId, int roomId, int sensorTypeId, String description) {
        this.sensorId = sensorId;
        this.roomId = roomId;
        this.sensorTypeId = sensorTypeId;
        this.description = description;
    }

    public int getSensorId() { return sensorId; }
    public void setSensorId(int sensorId) { this.sensorId = sensorId; }
    public int getRoomId() { return roomId; }
    public void setRoomId(int roomId) { this.roomId = roomId; }
    public int getSensorTypeId() { return sensorTypeId; }
    public void setSensorTypeId(int sensorTypeId) { this.sensorTypeId = sensorTypeId; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
//    public domain.Room getRoom() { return room; }
//    public void setRoom(domain.Room room) { this.room = room; }
//    public domain.SensorType getSensorType() { return sensorType; }
//    public void setSensorType(domain.SensorType sensorType) { this.sensorType = sensorType; }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sensor)) return false;
        Sensor sensor = (Sensor) o;
        return sensorId == sensor.sensorId &&
                roomId == sensor.roomId &&
                sensorTypeId == sensor.sensorTypeId &&
                Objects.equals(description, sensor.description);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() { return Objects.hash(sensorId, roomId, sensorTypeId, description); }

    @Override
    public String toString() {
        return Integer.toString(sensorId) +
                ", roomId=" + roomId +
                ", sensorTypeId=" + sensorTypeId +
                ", " + description;
    }

    public ArrayList<SensorReading> getSensorReadings() { return sensorReadings; }

    public void setSensorReadings(ArrayList<SensorReading> sensorReadings) { this.sensorReadings = sensorReadings; }

    public int findMinReadingIndex() {
        int minIndex = 0;
        float minValue = 0;
        int i = 0;
        for (SensorReading sensorReading : sensorReadings) {
            if (i == 0 || sensorReading.getValue() < minValue) {
                minValue = sensorReading.getValue();
                minIndex = i;
            }
            i++;
        }
        return minIndex;
    }

    public int findMinReadingIndex(int startIndex, int endIndex) {
        float min = sensorReadings.get(startIndex).getValue();
        int minIndex = startIndex;
        //search for minimum between two indexes
        if (startIndex > 0 && (endIndex < (sensorReadings.size() - 1)) && startIndex < endIndex && startIndex != endIndex) {
            for (int i = startIndex + 1; i <= endIndex; i++) {
                if (sensorReadings.get(i).getValue() < min) {
                    min = sensorReadings.get(i).getValue();
                    minIndex = i;
                }
            }
        } else { throw new IndexOutOfBoundsException("Index out of bounds: 0 - " + (this.sensorReadings.size() - 1)); }
        return minIndex;
    }

    public int findMaxReadingIndex() {
        int maxIndex = 0;
        float maxValue = 0;
        int i = 0;
        for (SensorReading sensorReading : sensorReadings) {
            if (i == 0 || sensorReading.getValue() > maxValue) {
                maxValue = sensorReading.getValue();
                maxIndex = i;
            }
            i++;
        }
        return maxIndex;
    }

    public int findMaxReadingIndex(int startIndex, int endIndex) {
        float max = sensorReadings.get(startIndex).getValue();
        int maxIndex = startIndex;
        if (startIndex > 0 && (endIndex < (sensorReadings.size() - 1)) && startIndex < endIndex && startIndex != endIndex) {
            for(int i = startIndex + 1; i <= endIndex; i++){
                if(sensorReadings.get(i).getValue() > max){
                    max = sensorReadings.get(i).getValue();
                    maxIndex = i;
                }
            }
        } else { throw new IndexOutOfBoundsException("Index out of bounds: 0 - " + (this.sensorReadings.size() - 1)); }
        return maxIndex;
    }

    public int findNextCycleMaxIndex(int startIndex) {
        SensorReading rMax = this.sensorReadings.get(startIndex);
        int i = startIndex + 1;

        for ( ; i < this.sensorReadings.size() - 1; i++) {
            if (rMax.getValue() < this.sensorReadings.get(i).getValue())
                rMax = this.sensorReadings.get(i);
            else
                break;
        }
        return i - 1;
    }

    public int findNextCycleMinIndex(int startIndex) {
        SensorReading rMin = this.sensorReadings.get(startIndex);
        int i = startIndex + 1;

        for ( ; i < this.sensorReadings.size() - 1; i++) {
            if (rMin.getValue() > this.sensorReadings.get(i).getValue())
                rMin = this.sensorReadings.get(i);
            else
                break;
        }
        return i - 1;
    }

    public SensorReading getSensorReading(int index) {
        return this.sensorReadings.get(index);
    }
}
