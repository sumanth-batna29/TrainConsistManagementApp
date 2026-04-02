import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Bogie class (reuse from UC7)
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
        return "Bogie Type: " + type + ", Capacity: " + capacity;
    }
}

public class TrainConsistManagementApp {

    public static void main(String[] args) {

        // Step 1: Create list of bogies (reuse from UC7)
        List<Bogie> bogieList = new ArrayList<>();
        bogieList.add(new Bogie("Sleeper", 72));
        bogieList.add(new Bogie("AC Chair", 60));
        bogieList.add(new Bogie("First Class", 50));
        bogieList.add(new Bogie("Sleeper", 80));

        // Step 2: Convert list to stream and filter
        List<Bogie> filteredBogies = bogieList.stream()
                .filter(b -> b.getCapacity() > 60) // condition
                .collect(Collectors.toList());

        // Step 3: Display filtered bogies
        System.out.println("Filtered Bogies (Capacity > 60):");
        filteredBogies.forEach(System.out::println);

        // Step 4: Show original list remains unchanged
        System.out.println("\nOriginal Bogie List:");
        bogieList.forEach(System.out::println);
    }
}