package com.gridnine.testing;

import com.gridnine.FlightBuilder;
import com.gridnine.testing.Flaght.ArrivalBeforeDepartureFilter;
import com.gridnine.testing.Flaght.DepartureBeforeCurrentTimeFilter;
import com.gridnine.testing.Flaght.GroundTimeExceedsTwoHoursFilter;



import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Flight> flights = FlightBuilder.createFlights();

        DepartureBeforeCurrentTimeFilter departureBeforeCurrentTimeFilter = new DepartureBeforeCurrentTimeFilter();
        List<Flight> filteredFlights1 = departureBeforeCurrentTimeFilter.filterFlights(flights);
        System.out.println("Filtered Flights (Departure Before Current Time): ");
        printFlights(filteredFlights1);

        ArrivalBeforeDepartureFilter arrivalBeforeDepartureFilter = new ArrivalBeforeDepartureFilter();
        List<Flight> filteredFlights2 = arrivalBeforeDepartureFilter.filterFlights(flights);
        System.out.println("Filtered Flights (Arrival Before Departure): ");
        printFlights(filteredFlights2);

        GroundTimeExceedsTwoHoursFilter groundTimeExceedsTwoHoursFilter = new GroundTimeExceedsTwoHoursFilter();
        List<Flight> filteredFlights3 = groundTimeExceedsTwoHoursFilter.filterFlights(flights);
        System.out.println("Filtered Flights (Ground Time Exceeds Two Hours): ");
        printFlights(filteredFlights3);
    }

    private static void printFlights(List<Flight> flights) {
        for (Flight flight : flights) {
            System.out.println(flight.toString());
        }
        System.out.println();
    }


    }
