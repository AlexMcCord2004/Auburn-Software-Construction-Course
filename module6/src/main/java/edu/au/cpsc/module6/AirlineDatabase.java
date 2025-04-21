package edu.au.cpsc.module6;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AirlineDatabase implements Serializable {

    private static final long serialVersionUID = 1L;

    private final List<ScheduledFlight> flights;

    public AirlineDatabase() {
        this.flights = new ArrayList<>();
    }

    public List<ScheduledFlight> getScheduledFlights() {
        return Collections.unmodifiableList(flights);
    }

    public void addScheduledFlight(ScheduledFlight sf) {
        if (sf == null) throw new IllegalArgumentException("ScheduledFlight cannot be null.");
        flights.add(sf);
    }

    public void removeScheduledFlight(ScheduledFlight sf) {
        flights.remove(sf);
    }

    public void updateScheduledFlight(ScheduledFlight updatedFlight) {
        for (int i = 0; i < flights.size(); i++) {
            ScheduledFlight current = flights.get(i);
            if (current.equals(updatedFlight)) {
                flights.set(i, updatedFlight);
                return;
            }
        }
        flights.add(updatedFlight);
    }
}
