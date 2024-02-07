package com.gridnine.testing.Flaght;

import com.gridnine.testing.Flight;
import com.gridnine.testing.Segment;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class GroundTimeExceedsTwoHoursFilterTest {

   @Test
    void testGroundTimeExceedsTwoHoursFilter() {
        GroundTimeExceedsTwoHoursFilter filter = new GroundTimeExceedsTwoHoursFilter();

        List<Flight> flights = new ArrayList<>();

        // Создание первого перелета, где общее время на земле в пределах двух часов
        List<Segment> segments1 = new ArrayList<>();
        segments1.add(new Segment(LocalDateTime.of(2024, 1, 1, 12, 0), LocalDateTime.of(2024, 1, 1, 13, 0)));
        segments1.add(new Segment(LocalDateTime.of(2024, 1, 1, 14, 0), LocalDateTime.of(2024, 1, 1, 15, 0))); // Общее время на земле = 0 часов
        Flight flight1 = new Flight(segments1);
        flights.add(flight1);

        // Создание второго перелета, где общее время на земле превышает два часа
        List<Segment> segments2 = new ArrayList<>();
        segments2.add(new Segment(LocalDateTime.of(2024, 2, 2, 12, 0), LocalDateTime.of(2024, 2, 2, 14, 0)));
        segments2.add(new Segment(LocalDateTime.of(2024, 2, 2, 15, 0), LocalDateTime.of(2024, 2, 2, 17, 30))); // Общее время на земле = 2.5 часа, что превышает два часа
        Flight flight2 = new Flight(segments2);
        flights.add(flight2);

        // Применение фильтра
        List<Flight> filteredFlights = filter.filterFlights(flights);

        // Проверка результатов

        assertEquals(flight1, filteredFlights.get(0)); // Проверяем, что первый перелет не был удален
    }

}

