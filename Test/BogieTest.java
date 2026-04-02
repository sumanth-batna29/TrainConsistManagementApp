import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class BogieTest {

    // ✅ Method required for filtering (FIXES YOUR ERROR)
    public static List<Bogie> filterByCapacity(List<Bogie> bogies, int threshold) {
        return bogies.stream()
                .filter(b -> b.getCapacity() > threshold)
                .collect(Collectors.toList());
    }

    // ✅ Sample data
    private List<Bogie> createSampleBogies() {
        List<Bogie> list = new ArrayList<>();
        list.add(new Bogie("Sleeper", 72));
        list.add(new Bogie("AC Chair", 60));
        list.add(new Bogie("First Class", 50));
        list.add(new Bogie("Sleeper", 80));
        list.add(new Bogie("AC Chair", 70));
        return list;
    }

    @Test
    void testFilter_CapacityGreaterThanThreshold() {
        List<Bogie> result = filterByCapacity(createSampleBogies(), 70);

        assertTrue(result.stream().allMatch(b -> b.getCapacity() > 70));
        assertEquals(2, result.size()); // 72, 80
    }

    @Test
    void testFilter_CapacityEqualToThreshold() {
        List<Bogie> result = filterByCapacity(createSampleBogies(), 70);

        assertFalse(result.stream().anyMatch(b -> b.getCapacity() == 70));
    }

    @Test
    void testFilter_CapacityLessThanThreshold() {
        List<Bogie> result = filterByCapacity(createSampleBogies(), 70);

        assertFalse(result.stream().anyMatch(b -> b.getCapacity() < 70));
    }

    @Test
    void testFilter_MultipleBogiesMatching() {
        List<Bogie> result = filterByCapacity(createSampleBogies(), 60);

        assertEquals(3, result.size()); // 72, 70, 80
    }

    @Test
    void testFilter_NoBogiesMatching() {
        List<Bogie> result = filterByCapacity(createSampleBogies(), 100);

        assertTrue(result.isEmpty());
    }

    @Test
    void testFilter_AllBogiesMatching() {
        List<Bogie> result = filterByCapacity(createSampleBogies(), 40);

        assertEquals(5, result.size());
    }

    @Test
    void testFilter_EmptyBogieList() {
        List<Bogie> emptyList = new ArrayList<>();

        List<Bogie> result = filterByCapacity(emptyList, 60);

        assertTrue(result.isEmpty());
    }

    @Test
    void testFilter_OriginalListUnchanged() {
        List<Bogie> original = createSampleBogies();
        int originalSize = original.size();

        filterByCapacity(original, 60);

        assertEquals(originalSize, original.size());
        assertEquals(5, original.size());
    }
}