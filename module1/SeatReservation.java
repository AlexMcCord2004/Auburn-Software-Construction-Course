public class SeatReservation {
    
private String flightDesignator;
private java.time.LocalDate flightDate;
private String firstName;
private String lastName;

public String getFlightDesignator() {
    return flightDesignator;
}

public void setFlightDesignator(String fd) {
    if (fd == null || fd.length() < 4 || fd.length() > 6) {
        throw new IllegalArgumentException("Flight designator must be between 4 and 6 characters long and cannot be null.");
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
}
