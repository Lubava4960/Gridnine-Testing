package com.gridnine.testing.Flaght;

import com.gridnine.testing.Flight;

import java.util.List;

public interface FlightFilter {


    List<Flight> filterFlights(List<Flight> flights);
}
