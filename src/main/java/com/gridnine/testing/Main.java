package com.gridnine.testing;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.*;

public class Main {
    public static void main(String[] args) {

        List<Flight> flights = FlightBuilder.createFlights();

        // Выводим результаты обработки тестового набора перелётов
        for (Flight flight : flights) {
            System.out.println(flight);
        }

        // Исключаем перелёты по правилу: Вылет до текущего момента времени
        List<Flight> flightsAfterNow = excludeFlightsBeforeNow(flights);
        System.out.println("Перелёты выполняющиеся после текущего момента времени:");
        printFlights(flightsAfterNow);
        System.out.println();

        // Исключаем перелёты по правилу: Сегменты с датой прилёта раньше даты вылета
        List<Flight> flightsWithValidSegments = excludeFlightsWithInvalidSegments(flights);
        System.out.println("Перелёты с валидными сегментами (дата прилёта позже даты вылета):");
        printFlights(flightsWithValidSegments);
        System.out.println();

        // Исключаем перелёты по правилу: Общее время, проведённое на земле, превышает два часа
        List<Flight> flightsWithValidGroundTime = excludeFlightsWithExcessiveGroundTime(flights);
        System.out.println("Перелёты с валидным временем на земле (не превышает двух часов):");
        printFlights(flightsWithValidGroundTime);
    }

    // Метод для исключения перелетов, у которых вылет произошел до текущего момента времени
    static List<Flight> excludeFlightsBeforeNow(List<Flight> flights) {
        LocalDateTime now = LocalDateTime.now();
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .allMatch(segment -> segment.getDepartureDate().isAfter(now)))
                .collect(Collectors.toList());
    }

    // Метод для исключения перелетов, у которых сегменты имеют дату прилета раньше даты вылета
    static List<Flight> excludeFlightsWithInvalidSegments(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .allMatch(segment -> segment.getArrivalDate().isAfter(segment.getDepartureDate())))
                .collect(Collectors.toList());
    }

    // Метод для исключения перелетов, у которых общее время на земле превышает 2 часа
    static List<Flight> excludeFlightsWithExcessiveGroundTime(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> calculateTotalGroundTime(flight) <= 2 * 60)
                .collect(Collectors.toList());
    }

    // Метод для расчета общего времени на земле между сегментами перелета
    static int calculateTotalGroundTime(Flight flight) {
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

    // Метод для вывода списка перелетов в консоль
    private static void printFlights(List<Flight> flights) {
        for (Flight flight : flights) {
            System.out.println(flight);
        }
    }
}