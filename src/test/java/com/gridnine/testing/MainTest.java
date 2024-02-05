package com.gridnine.testing;

import com.gridnine.testing.Flight;
import com.gridnine.testing.Segment;
import com.gridnine.testing.FlightBuilder;
import com.gridnine.testing.Main;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class MainTest {
    @Test
    public void testExcludeFlightsBeforeNow() {
        List<Flight> flights = FlightBuilder.createFlights();
        List<Flight> filteredFlights = Main.excludeFlightsBeforeNow(flights);


        LocalDateTime now = LocalDateTime.now();
        for (Flight flight : filteredFlights) {
            for (Segment segment : flight.getSegments()) {
                LocalDateTime departureDate = segment.getDepartureDate();
                assertTrue(departureDate.isAfter(now));
            }
        }
    }

    @Test
    public void testExcludeFlightsWithInvalidSegments() {
        List<Flight> flights = FlightBuilder.createFlights();
        List<Flight> filteredFlights = Main.excludeFlightsWithInvalidSegments(flights);

        for (Flight flight : filteredFlights) {
            for (Segment segment : flight.getSegments()) {
                LocalDateTime departureDate = segment.getDepartureDate();
                LocalDateTime arrivalDate = segment.getArrivalDate();
                assertEquals(true, arrivalDate.isAfter(departureDate));
            }
        }
    }

    @Test
    public void testExcludeFlightsWithExcessiveGroundTime() {
        List<Flight> flights = FlightBuilder.createFlights();
        List<Flight> filteredFlights = Main.excludeFlightsWithExcessiveGroundTime(flights);

        for (Flight flight : filteredFlights) {
            int totalGroundTime = Main.calculateTotalGroundTime(flight);
            assertEquals(true, totalGroundTime <= 2 * 60);
        }
    }
}