import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.stream.*;

import static org.junit.jupiter.api.Assertions.*;

public class BogieTest {

    // ✅ Method under test
    public static int calculateTotalSeats(List<Bogie> bogies) {
        return bogies.stream()
                .map(Bogie::getCapacity)
                .reduce(0, Integer::sum);
    }

    // ✅ Sample Data
    private List<Bogie> createSampleBogies() {
        List<Bogie> list = new ArrayList<>();
        list.add(new Bogie("Sleeper", 72));
        list.add(new Bogie("AC Chair", 60));
        list.add(new Bogie("First Class", 50));
        list.add(new Bogie("Sleeper", 80));
        return list;
    }

    @Test
    void testReduce_TotalSeatCalculation() {
        int total = calculateTotalSeats(createSampleBogies());

        assertEquals(262, total); // 72 + 60 + 50 + 80
    }

    @Test
    void testReduce_MultipleBogiesAggregation() {
        int total = calculateTotalSeats(createSampleBogies());

        assertTrue(total > 0);
        assertEquals(262, total);
    }

    @Test
    void testReduce_SingleBogieCapacity() {
        List<Bogie> list = new ArrayList<>();
        list.add(new Bogie("Sleeper", 72));

        int total = calculateTotalSeats(list);

        assertEquals(72, total);
    }

    @Test
    void testReduce_EmptyBogieList() {
        List<Bogie> emptyList = new ArrayList<>();

        int total = calculateTotalSeats(emptyList);

        assertEquals(0, total);
    }

    @Test
    void testReduce_CorrectCapacityExtraction() {
        List<Bogie> list = createSampleBogies();

        List<Integer> capacities = list.stream()
                .map(Bogie::getCapacity)
                .toList();

        assertTrue(capacities.contains(72));
        assertTrue(capacities.contains(60));
        assertTrue(capacities.contains(50));
        assertTrue(capacities.contains(80));
    }

    @Test
    void testReduce_AllBogiesIncluded() {
        List<Bogie> list = createSampleBogies();

        int expectedSum = 0;
        for (Bogie b : list) {
            expectedSum += b.getCapacity();
        }

        int actual = calculateTotalSeats(list);

        assertEquals(expectedSum, actual);
    }

    @Test
    void testReduce_OriginalListUnchanged() {
        List<Bogie> original = createSampleBogies();
        int sizeBefore = original.size();

        calculateTotalSeats(original);

        assertEquals(sizeBefore, original.size());
    }
}