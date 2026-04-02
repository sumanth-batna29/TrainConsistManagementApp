import java.util.*;
import java.util.stream.*;

class Bogie {
    String type;
    int capacity;

    public Bogie(String type, int capacity) {
        this.type = type;
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return type + " - Capacity: " + capacity;
    }
}

public class TrainConsistManagementApp {

    // ✅ UC10 Method (IMPORTANT)
    public static int calculateTotalSeats(List<Bogie> bogies) {
        return bogies.stream()
                .map(Bogie::getCapacity)     // extract capacity
                .reduce(0, Integer::sum);   // sum all values
    }

    public static void main(String[] args) {

        List<Bogie> bogies = new ArrayList<>();

        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 60));
        bogies.add(new Bogie("First Class", 50));
        bogies.add(new Bogie("Sleeper", 80));

        // ✅ Calculate total seats
        int totalSeats = calculateTotalSeats(bogies);

        System.out.println("Total Seating Capacity: " + totalSeats);
    }
}