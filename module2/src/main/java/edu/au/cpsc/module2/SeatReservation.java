<<<<<<< HEAD
package edu.au.cpsc.module2;

public class SeatReservation {

    private String flightDesignator;
    private java.time.LocalDate flightDate;
    private String firstName;
    private String lastName;

    private int numberOfBags; // NEW
    private boolean flyingWithInfant; // NEW

    // Getters and Setters

    public String getFlightDesignator() {
        return flightDesignator;
    }

    public void setFlightDesignator(String fd) {
        if (fd == null || fd.length() < 4 || fd.length() > 6) {
            throw new IllegalArgumentException("Invalid flight designator.");
        }
        this.flightDesignator = fd;
    }

    public java.time.LocalDate getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(java.time.LocalDate date) {
        this.flightDate = date;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String fn) {
        this.firstName = fn;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String ln) {
        this.lastName = ln;
    }

    // NEW: Getter and Setter for numberOfBags
    public int getNumberOfBags() {
        return numberOfBags;
    }

    public void setNumberOfBags(int bags) {
        this.numberOfBags = bags;
    }

    // NEW: Getter and "setter-ish" methods for flyingWithInfant
    public boolean isFlyingWithInfant() {
        return flyingWithInfant;
    }

    public void makeFlyingWithInfant() {
        this.flyingWithInfant = true;
    }

    public void makeNotFlyingWithInfant() {
        this.flyingWithInfant = false;
    }

    @Override
    public String toString() {
        return "SeatReservation{" +
                "flightDesignator=" + (flightDesignator != null ? flightDesignator : "null") +
                ",flightDate=" + (flightDate != null ? flightDate : "null") +
                ",firstName=" + (firstName != null ? firstName : "null") +
                ",lastName=" + (lastName != null ? lastName : "null") +
                ",numberOfBags=" + numberOfBags +
                ",flyingWithInfant=" + flyingWithInfant +
                '}';
    }
}
=======
package edu.au.cpsc.module2;

public class SeatReservation {

    private String flightDesignator;
    private java.time.LocalDate flightDate;
    private String firstName;
    private String lastName;

    private int numberOfBags; // NEW
    private boolean flyingWithInfant; // NEW

    // Getters and Setters

    public String getFlightDesignator() {
        return flightDesignator;
    }

    public void setFlightDesignator(String fd) {
        if (fd == null || fd.length() < 4 || fd.length() > 6) {
            throw new IllegalArgumentException("Invalid flight designator.");
        }
        this.flightDesignator = fd;
    }

    public java.time.LocalDate getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(java.time.LocalDate date) {
        this.flightDate = date;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String fn) {
        this.firstName = fn;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String ln) {
        this.lastName = ln;
    }

    // NEW: Getter and Setter for numberOfBags
    public int getNumberOfBags() {
        return numberOfBags;
    }

    public void setNumberOfBags(int bags) {
        this.numberOfBags = bags;
    }

    // NEW: Getter and "setter-ish" methods for flyingWithInfant
    public boolean isFlyingWithInfant() {
        return flyingWithInfant;
    }

    public void makeFlyingWithInfant() {
        this.flyingWithInfant = true;
    }

    public void makeNotFlyingWithInfant() {
        this.flyingWithInfant = false;
    }

    @Override
    public String toString() {
        return "SeatReservation{" +
                "flightDesignator=" + (flightDesignator != null ? flightDesignator : "null") +
                ",flightDate=" + (flightDate != null ? flightDate : "null") +
                ",firstName=" + (firstName != null ? firstName : "null") +
                ",lastName=" + (lastName != null ? lastName : "null") +
                ",numberOfBags=" + numberOfBags +
                ",flyingWithInfant=" + flyingWithInfant +
                '}';
    }
}
>>>>>>> branch4
