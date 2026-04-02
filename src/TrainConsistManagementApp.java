import java.util.*;
import java.util.stream.*;

class PassengerBogie {
    String name; // Sleeper, AC Chair, First Class
    int capacity;

    public PassengerBogie(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return name + " (" + capacity + " seats)";
    }
}

public class TrainConsistManagementApp {

    // Loop-based filtering
    public static List<PassengerBogie> filterUsingLoop(List<PassengerBogie> bogies, int threshold) {
        List<PassengerBogie> result = new ArrayList<>();
        for (PassengerBogie b : bogies) {
            if (b.getCapacity() > threshold) {
                result.add(b);
            }
        }
        return result;
    }

    // Stream-based filtering
    public static List<PassengerBogie> filterUsingStream(List<PassengerBogie> bogies, int threshold) {
        return bogies.stream()
                .filter(b -> b.getCapacity() > threshold)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<PassengerBogie> bogies = new ArrayList<>();
        bogies.add(new PassengerBogie("Sleeper", 72));
        bogies.add(new PassengerBogie("AC Chair", 60));
        bogies.add(new PassengerBogie("First Class", 80));
        bogies.add(new PassengerBogie("Sleeper", 50));

        int threshold = 60;

        // Loop filtering with timing
        long startLoop = System.nanoTime();
        List<PassengerBogie> loopFiltered = filterUsingLoop(bogies, threshold);
        long endLoop = System.nanoTime();
        System.out.println("Loop filtered bogies: " + loopFiltered);
        System.out.println("Loop elapsed time: " + (endLoop - startLoop) + " ns");

        // Stream filtering with timing
        long startStream = System.nanoTime();
        List<PassengerBogie> streamFiltered = filterUsingStream(bogies, threshold);
        long endStream = System.nanoTime();
        System.out.println("Stream filtered bogies: " + streamFiltered);
        System.out.println("Stream elapsed time: " + (endStream - startStream) + " ns");
    }
}