package ru.kubsu.geocoder.dto;

import java.util.Objects;

public class NominatimPlace {
    Double lat;
    Double lon;
    String displayName;
    String type;

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NominatimPlace that = (NominatimPlace) o;
        return Objects.equals(lat, that.lat) && Objects.equals(lon, that.lon) && Objects.equals(displayName, that.displayName) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lat, lon, displayName, type);
    }

    @Override
    public String toString() {
        return "NominatimPlace{" +
                "lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                ", displayName='" + displayName + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
