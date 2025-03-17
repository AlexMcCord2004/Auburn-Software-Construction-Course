private String flightDesignator;
private java.time.LocalDate flightDate;
private String firstName;
private String lastName;

public String getFlightDesignator() {
    return flightDesignator;
}

public void setFlightDesignator(String fd) {
    if (fd == null){
        throw new IllegalArgumentException("flight designator cannot be null");
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

@Override
public String toString() {
    return "SeatReservation{" +
            "flightDesignator='" + flightDesignator + '\'' +
            ", flightDate=" + flightDate +
            ", firstName='" + (firstName != null ? firstName : "null") + '\'' +
            ", lastName='" + (lastName != null ? lastName : "null") + '\'' +
            '}';
}
