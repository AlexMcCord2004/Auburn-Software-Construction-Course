package edu.au.cpsc.module3;

import java.io.*;
import java.util.*;

public class Airport {
    private String ident;
    private String type;
    private String name;
    private Integer elevationFt;
    private String continent;
    private String isoCountry;
    private String isoRegion;
    private String municipality;
    private String gpsCode;
    private String iataCode;
    private String localCode;
    private String coordinates;

    public String getIdent() {
        return ident;
    }

    public void setIdent(String ident) {
        this.ident = ident;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getElevationFt() {
        return elevationFt;
    }

    public void setElevationFt(Integer elevationFt) {
        this.elevationFt = elevationFt;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getIsoCountry() {
        return isoCountry;
    }

    public void setIsoCountry(String isoCountry) {
        this.isoCountry = isoCountry;
    }

    public String getIsoRegion() {
        return isoRegion;
    }

    public void setIsoRegion(String isoRegion) {
        this.isoRegion = isoRegion;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getGpsCode() {
        return gpsCode;
    }

    public void setGpsCode(String gpsCode) {
        this.gpsCode = gpsCode;
    }

    public String getIataCode() {
        return iataCode;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    public String getLocalCode() {
        return localCode;
    }

    public void setLocalCode(String localCode) {
        this.localCode = localCode;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public static List<Airport> readAll() throws IOException {
        List<Airport> airports = new ArrayList<>();

        try (InputStream input = Airport.class.getResourceAsStream("/airport-codes.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {

            String line = reader.readLine(); // skip header
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",", -1); // -1 keeps empty fields

                Airport a = new Airport();
                a.setIdent(fields[0]);
                a.setType(fields[1]);
                a.setName(fields[2]);
                a.setElevationFt(parseInteger(fields[3]));
                a.setContinent(fields[4]);
                a.setIsoCountry(fields[5]);
                a.setIsoRegion(fields[6]);
                a.setMunicipality(fields[7]);
                a.setGpsCode(fields[8]);
                a.setIataCode(fields[9]);
                a.setLocalCode(fields[10]);
                a.setCoordinates(fields[11]);

                airports.add(a);
            }
        }

        return airports;
    }

    private static Integer parseInteger(String s) {
        return s.isEmpty() ? null : Integer.parseInt(s);
    }
}