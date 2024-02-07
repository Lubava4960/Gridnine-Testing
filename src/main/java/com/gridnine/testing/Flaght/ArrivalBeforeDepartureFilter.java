package com.gridnine.testing.Flaght;

import com.gridnine.testing.Flight;

import java.util.ArrayList;
import java.util.List;

/**
 * Фильтр для исключения перелётов с сегментами, где дата прилёта раньше даты вылета
 */
public class ArrivalBeforeDepartureFilter implements  FlightFilter{

    @Override
    public List<Flight> filterFlights(List<Flight> flights) {
        List<Flight> filteredFlights = new ArrayList<>();
        for (Flight flight : flights) {
            boolean hasSegmentsWithArrivalBeforeDeparture = flight.getSegments().stream()
                    .anyMatch(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate()));
            if (!hasSegmentsWithArrivalBeforeDeparture) {
                filteredFlights.add(flight);
            }
        }
        return filteredFlights;
    }

}
