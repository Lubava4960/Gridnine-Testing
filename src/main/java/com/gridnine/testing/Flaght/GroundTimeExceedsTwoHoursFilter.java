package com.gridnine.testing.Flaght;

import com.gridnine.testing.Flight;
import com.gridnine.testing.Segment;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *  Фильтр для исключения перелётов, где общее время на земле превышает два часа
 */
public class GroundTimeExceedsTwoHoursFilter implements FlightFilter{
    @Override
    public List<Flight> filterFlights(List<Flight> flights) {
        List<Flight> filteredFlights = new ArrayList<>();
        for (Flight flight : flights) {
            boolean hasGroundTimeExceedsTwoHours = hasGroundTimeExceedsTwoHours(flight);
            if (!hasGroundTimeExceedsTwoHours) {
                filteredFlights.add(flight);
            }
        }
        return filteredFlights;
    }

    private boolean hasGroundTimeExceedsTwoHours(Flight flight) {
        if (flight.getSegments().size() < 2) {
            return false;
        }
        List<Segment> segments = flight.getSegments();
        for (int i = 1; i < segments.size(); i++) {
            LocalDateTime previousArrival = segments.get(i - 1).getArrivalDate();
            LocalDateTime currentDeparture = segments.get(i).getDepartureDate();
            long groundTime = Duration.between(previousArrival, currentDeparture).toHours();
            if (groundTime > 2) {
                return true;
            }
        }
        return false;
    }
}


