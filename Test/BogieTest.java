import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class PassengerBogieTest {

    public static List<PassengerBogie> createSampleBogies() {
        List<PassengerBogie> bogies = new ArrayList<>();
        bogies.add(new PassengerBogie("Sleeper", 72));
        bogies.add(new PassengerBogie("AC Chair", 60));
        bogies.add(new PassengerBogie("First Class", 80));
        bogies.add(new PassengerBogie("Sleeper", 50));
        return bogies;
    }

    @Test
    void testLoopFilteringLogic() {
        List<PassengerBogie> bogies = createSampleBogies();
        List<PassengerBogie> filtered = TrainConsistPerformance.filterUsingLoop(bogies, 60);
        assertEquals(2, filtered.size());
        assertTrue(filtered.stream().allMatch(b -> b.getCapacity() > 60));
    }

    @Test
    void testStreamFilteringLogic() {
        List<PassengerBogie> bogies = createSampleBogies();
        List<PassengerBogie> filtered = TrainConsistPerformance.filterUsingStream(bogies, 60);
        assertEquals(2, filtered.size());
        assertTrue(filtered.stream().allMatch(b -> b.getCapacity() > 60));
    }

    @Test
    void testLoopAndStreamResultsMatch() {
        List<PassengerBogie> bogies = createSampleBogies();
        List<PassengerBogie> loopFiltered = TrainConsistPerformance.filterUsingLoop(bogies, 60);
        List<PassengerBogie> streamFiltered = TrainConsistPerformance.filterUsingStream(bogies, 60);
        assertEquals(loopFiltered.size(), streamFiltered.size());
        assertEquals(loopFiltered, streamFiltered);
    }

    @Test
    void testExecutionTimeMeasurement() {
        List<PassengerBogie> bogies = createSampleBogies();
        long start = System.nanoTime();
        TrainConsistPerformance.filterUsingLoop(bogies, 60);
        long end = System.nanoTime();
        long elapsed = end - start;
        assertTrue(elapsed > 0);

        start = System.nanoTime();
        TrainConsistPerformance.filterUsingStream(bogies, 60);
        end = System.nanoTime();
        elapsed = end - start;
        assertTrue(elapsed > 0);
    }

    @Test
    void testLargeDatasetProcessing() {
        List<PassengerBogie> bogies = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            bogies.add(new PassengerBogie("Sleeper", 70 + i % 10));
        }

        List<PassengerBogie> loopFiltered = TrainConsistPerformance.filterUsingLoop(bogies, 75);
        List<PassengerBogie> streamFiltered = TrainConsistPerformance.filterUsingStream(bogies, 75);

        assertEquals(loopFiltered.size(), streamFiltered.size());
        assertTrue(loopFiltered.stream().allMatch(b -> b.getCapacity() > 75));
    }
}