package com.gridnine.testing.Flaght;

import com.gridnine.testing.Flight;
import com.gridnine.testing.Segment;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.temporal.ChronoUnit.MINUTES;

public class CalculateTotalGroundTime {
    public static int calculateTotalGroundTime(Flight flight) {
        int totalGroundTime = 0;
        List<Segment> segments = flight.getSegments();
        int numSegments = segments.size();
        for (int i = 0; i < numSegments - 1; i++) {
            LocalDateTime currentSegmentArrival = segments.get(i).getArrivalDate();
            LocalDateTime nextSegmentDeparture = segments.get(i + 1).getDepartureDate();
            totalGroundTime += (int) currentSegmentArrival.until(nextSegmentDeparture, MINUTES);
        }
        return totalGroundTime;
    }
}
