package com.gridnine.testing.Flaght;
import com.gridnine.testing.Flight;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для фильтрации  перелётов с вылетом до текущего момента времени
 */

public class DepartureBeforeCurrentTimeFilter implements FlightFilter {
    @Override
    public List<Flight> filterFlights(List<Flight> flights) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        List<Flight> filteredFlights = new ArrayList<>();
        for (Flight flight : flights) {
            boolean hasDepartureBeforeCurrentTime = flight.getSegments().stream()
                    .anyMatch(segment -> segment.getDepartureDate().isBefore(currentDateTime));
            if (!hasDepartureBeforeCurrentTime) {
                filteredFlights.add(flight);
            }
        }
        return filteredFlights;
    }

}
