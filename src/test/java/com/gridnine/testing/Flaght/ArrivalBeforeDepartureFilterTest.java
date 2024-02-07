package com.gridnine.testing.Flaght;

import com.gridnine.testing.Flight;
import com.gridnine.testing.Segment;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

class DepartureBeforeCurrentTimeFilterTest {

    @Test
    void testArrivalBeforeDepartureFilter() {
        ArrivalBeforeDepartureFilter filter = new ArrivalBeforeDepartureFilter();

        List<Flight> flights = new ArrayList<>();

        // Создание первого перелета, где все сегменты имеют правильную последовательность дат
        List<Segment> segments1 = new ArrayList<>();
        segments1.add(new Segment(LocalDateTime.of(2024, 1, 1, 12, 0), LocalDateTime.of(2024, 1, 1, 14, 0)));
        segments1.add(new Segment(LocalDateTime.of(2024, 1, 1, 15, 0), LocalDateTime.of(2024, 1, 1, 17, 0)));
        Flight flight1 = new Flight(segments1);
        flights.add(flight1);

        // Создание второго перелета, где у сегмента второй даты прилета раньше даты вылета
        List<Segment> segments2 = new ArrayList<>();
        segments2.add(new Segment(LocalDateTime.of(2024, 2, 2, 12, 0), LocalDateTime.of(2024, 2, 2, 14, 0)));
        segments2.add(new Segment(LocalDateTime.of(2024, 2, 2, 15, 0), LocalDateTime.of(2024, 2, 1, 13, 0))); // Некорректный порядок дат
        Flight flight2 = new Flight(segments2);
        flights.add(flight2);

        // Применение фильтра
        List<Flight> filteredFlights = filter.filterFlights(flights);

        // Проверка результатов
        assertEquals(1, filteredFlights.size()); // Ожидается, что только первый перелет останется после фильтрации
        assertEquals(flight1, filteredFlights.get(0)); // Проверяем, что первый перелет не был удален
    }

}