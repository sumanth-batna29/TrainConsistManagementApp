import java.util.*;

public class TrainConsistManagementApp {

    static class Bogie {
        String bogieId;
        String bogieType; // "Passenger" or "Cargo"
        String bogieSubType; // "Sleeper", "AC Chair", "First Class", "Pantry", "Guard", etc.
        int capacity;

        public Bogie(String bogieId, String bogieType, String bogieSubType, int capacity) {
            this.bogieId = bogieId;
            this.bogieType = bogieType;
            this.bogieSubType = bogieSubType;
            this.capacity = capacity;
        }

        @Override
        public String toString() {
            return bogieId + " (" + bogieSubType + ")";
        }
    }

    static class Train {
        String trainId;
        String name;
        List<Bogie> bogies = new ArrayList<>();
        Set<String> uniqueBogieIds = new HashSet<>();
        LinkedList<String> orderedConsist = new LinkedList<>();
        LinkedHashSet<String> insertionOrderConsist = new LinkedHashSet<>();
        HashMap<String, Integer> bogieCapacityMap = new HashMap<>();
        List<Bogie> passengerBogies = new ArrayList<>();  // UC7: List for passenger bogies
        List<Bogie> cargoBogies = new ArrayList<>();      // UC7: List for cargo bogies

        public Train(String trainId, String name) {
            this.trainId = trainId;
            this.name = name;
        }

        // UC7: Add bogie with capacity mapping and categorization
        public void addBogie(Bogie bogie) {
            if (uniqueBogieIds.contains(bogie.bogieId)) {
                System.out.println("✗ ERROR: Bogie ID '" + bogie.bogieId + "' already exists! Duplicate not added.");
                return;
            }
            bogies.add(bogie);
            uniqueBogieIds.add(bogie.bogieId);
            orderedConsist.addLast(bogie.bogieId);
            insertionOrderConsist.add(bogie.bogieId);
            bogieCapacityMap.put(bogie.bogieId, bogie.capacity);

            // UC7: Categorize bogie by type
            if ("Passenger".equals(bogie.bogieType)) {
                passengerBogies.add(bogie);
            } else if ("Cargo".equals(bogie.bogieType)) {
                cargoBogies.add(bogie);
            }

            System.out.println("✓ Added Bogie: " + bogie.bogieId + " (" + bogie.bogieSubType + ") | Capacity: " + bogie.capacity);
        }

        // UC7: Add bogie at the beginning (Engine position)
        public void addBogieAtFirst(Bogie bogie) {
            if (uniqueBogieIds.contains(bogie.bogieId)) {
                System.out.println("✗ ERROR: Bogie ID '" + bogie.bogieId + "' already exists!");
                return;
            }
            bogies.add(0, bogie);
            uniqueBogieIds.add(bogie.bogieId);
            orderedConsist.addFirst(bogie.bogieId);
            insertionOrderConsist.add(bogie.bogieId);
            bogieCapacityMap.put(bogie.bogieId, bogie.capacity);
            System.out.println("✓ Added Bogie at FIRST position: " + bogie.bogieId + " (" + bogie.bogieSubType + ") | Capacity: " + bogie.capacity);
        }

        // UC7: Add bogie at the end (Guard Coach position)
        public void addBogieAtLast(Bogie bogie) {
            if (uniqueBogieIds.contains(bogie.bogieId)) {
                System.out.println("✗ ERROR: Bogie ID '" + bogie.bogieId + "' already exists!");
                return;
            }
            bogies.add(bogie);
            uniqueBogieIds.add(bogie.bogieId);
            orderedConsist.addLast(bogie.bogieId);
            insertionOrderConsist.add(bogie.bogieId);
            bogieCapacityMap.put(bogie.bogieId, bogie.capacity);
            System.out.println("✓ Added Bogie at LAST position: " + bogie.bogieId + " (" + bogie.bogieSubType + ") | Capacity: " + bogie.capacity);
        }

        // UC7: Sort passenger bogies by capacity (ascending - low to high)
        public void sortPassengerBogiesByCapacityAscending() {
            System.out.println("\n--- Sorting Passenger Bogies by Capacity (Ascending) ---");
            passengerBogies.sort(Comparator.comparingInt(b -> b.capacity));
            System.out.println("✓ Sorted in ascending order (low to high capacity)");
        }

        // UC7: Sort passenger bogies by capacity (descending - high to low)
        public void sortPassengerBogiesByCapacityDescending() {
            System.out.println("\n--- Sorting Passenger Bogies by Capacity (Descending) ---");
            passengerBogies.sort(Comparator.comparingInt(Bogie::getCapacity).reversed());
            System.out.println("✓ Sorted in descending order (high to low capacity)");
        }

        // UC7: Sort passenger bogies by bogie type (alphabetical)
        public void sortPassengerBogiesByType() {
            System.out.println("\n--- Sorting Passenger Bogies by Type (Alphabetical) ---");
            passengerBogies.sort(Comparator.comparing(b -> b.bogieSubType));
            System.out.println("✓ Sorted alphabetically by type");
        }

        // UC7: Sort cargo bogies by capacity (ascending)
        public void sortCargoBogiesByCapacityAscending() {
            System.out.println("\n--- Sorting Cargo Bogies by Capacity (Ascending) ---");
            cargoBogies.sort(Comparator.comparingInt(b -> b.capacity));
            System.out.println("✓ Sorted in ascending order");
        }

        // UC7: Sort cargo bogies by capacity (descending)
        public void sortCargoBogiesByCapacityDescending() {
            System.out.println("\n--- Sorting Cargo Bogies by Capacity (Descending) ---");
            cargoBogies.sort(Comparator.comparingInt(Bogie::getCapacity).reversed());
            System.out.println("✓ Sorted in descending order");
        }

        // UC7: Display passenger bogies with sorting information
        public void displayPassengerBogies() {
            System.out.println("\n--- PASSENGER BOGIES ---");
            System.out.println("Total Passenger Bogies: " + passengerBogies.size());
            if (passengerBogies.isEmpty()) {
                System.out.println("No passenger bogies.");
                return;
            }
            System.out.println("\nCurrent Order:");
            int position = 1;
            for (Bogie bogie : passengerBogies) {
                System.out.println("  " + position + ". [" + bogie.bogieId + "] " + bogie.bogieSubType + " - Capacity: " + bogie.capacity);
                position++;
            }
        }

        // UC7: Display cargo bogies with sorting information
        public void displayCargoBogies() {
            System.out.println("\n--- CARGO BOGIES ---");
            System.out.println("Total Cargo Bogies: " + cargoBogies.size());
            if (cargoBogies.isEmpty()) {
                System.out.println("No cargo bogies.");
                return;
            }
            System.out.println("\nCurrent Order:");
            int position = 1;
            for (Bogie bogie : cargoBogies) {
                System.out.println("  " + position + ". [" + bogie.bogieId + "] " + bogie.bogieSubType + " - Capacity: " + bogie.capacity);
                position++;
            }
        }

        // UC7: Display all bogies sorted
        public void displayAllBogiesSorted() {
            System.out.println("\n--- ALL BOGIES (Current Order) ---");
            System.out.println("Total Bogies: " + bogies.size());
            int position = 1;
            for (Bogie bogie : bogies) {
                System.out.println("  " + position + ". [" + bogie.bogieId + "] " + bogie.bogieSubType + " (" + bogie.bogieType + ") - Capacity: " + bogie.capacity);
                position++;
            }
        }

        // UC7: Capacity analysis by type
        public void displayCapacityAnalysisByType() {
            System.out.println("\n--- CAPACITY ANALYSIS BY TYPE ---");

            // Passenger capacity analysis
            if (!passengerBogies.isEmpty()) {
                int totalPassengerCapacity = passengerBogies.stream().mapToInt(b -> b.capacity).sum();
                int avgPassengerCapacity = totalPassengerCapacity / passengerBogies.size();
                int maxPassengerCapacity = passengerBogies.stream().mapToInt(b -> b.capacity).max().orElse(0);
                int minPassengerCapacity = passengerBogies.stream().mapToInt(b -> b.capacity).min().orElse(0);

                System.out.println("\nPassenger Bogies:");
                System.out.println("  Total Capacity: " + totalPassengerCapacity);
                System.out.println("  Average Capacity: " + avgPassengerCapacity);
                System.out.println("  Max Capacity: " + maxPassengerCapacity);
                System.out.println("  Min Capacity: " + minPassengerCapacity);
            }

            // Cargo capacity analysis
            if (!cargoBogies.isEmpty()) {
                int totalCargoCapacity = cargoBogies.stream().mapToInt(b -> b.capacity).sum();
                int avgCargoCapacity = totalCargoCapacity / cargoBogies.size();
                int maxCargoCapacity = cargoBogies.stream().mapToInt(b -> b.capacity).max().orElse(0);
                int minCargoCapacity = cargoBogies.stream().mapToInt(b -> b.capacity).min().orElse(0);

                System.out.println("\nCargo Bogies:");
                System.out.println("  Total Capacity: " + totalCargoCapacity);
                System.out.println("  Average Capacity: " + avgCargoCapacity);
                System.out.println("  Max Capacity: " + maxCargoCapacity);
                System.out.println("  Min Capacity: " + minCargoCapacity);
            }
        }

        // UC7: Display train summary
        public void displayTrainSummary() {
            System.out.println("\n--- TRAIN SUMMARY ---");
            System.out.println("Train ID: " + trainId);
            System.out.println("Train Name: " + name);
            System.out.println("Total Bogies: " + bogies.size());
            System.out.println("Passenger Bogies: " + passengerBogies.size());
            System.out.println("Cargo Bogies: " + cargoBogies.size());
        }

        // Helper method to get capacity (for method reference)
        public int getCapacity() {
            return 0;
        }

        // Helper method to find bogie by ID
        private Bogie findBogieById(String bogieId) {
            for (Bogie b : bogies) {
                if (b.bogieId.equals(bogieId)) {
                    return b;
                }
            }
            return null;
        }
    }

    // UC7: Static inner class for Bogie to support getCapacity() method reference
    // Adding getter method to Bogie class
    static class BogieComparator {
        // UC7: Custom comparator - Sort by capacity descending
        public static Comparator<Bogie> byCapacityDescending() {
            return (b1, b2) -> Integer.compare(b2.capacity, b1.capacity);
        }

        // UC7: Custom comparator - Sort by capacity ascending
        public static Comparator<Bogie> byCapacityAscending() {
            return (b1, b2) -> Integer.compare(b1.capacity, b2.capacity);
        }

        // UC7: Custom comparator - Sort by type then capacity
        public static Comparator<Bogie> byTypeAndCapacity() {
            return Comparator.comparing((Bogie b) -> b.bogieSubType)
                    .thenComparingInt(b -> b.capacity);
        }
    }

    public static void main(String[] args) {
        System.out.println("========== TRAIN CONSIST MANAGEMENT APP ==========");
        System.out.println("UC7: Sort Bogies by Capacity (Comparator)\n");

        // Create a train
        Train train = new Train("TR001", "Express");

        // UC7 Demonstration: Adding passenger bogies
        System.out.println("--- STEP 1: Add Passenger Bogies ---");
        train.addBogieAtFirst(new Bogie("LOC001", "Engine", "Locomotive", 0));
        train.addBogie(new Bogie("BG101", "Passenger", "Sleeper", 72));
        train.addBogie(new Bogie("BG102", "Passenger", "AC Chair", 90));
        train.addBogie(new Bogie("BG103", "Passenger", "First Class", 48));
        train.addBogie(new Bogie("BG104", "Passenger", "General", 120));

        // UC7 Demonstration: Adding cargo bogies
        System.out.println("\n--- STEP 2: Add Cargo Bogies ---");
        train.addBogie(new Bogie("BG105", "Cargo", "Rectangular", 500));
        train.addBogie(new Bogie("BG106", "Cargo", "Cylindrical", 600));
        train.addBogie(new Bogie("BG107", "Cargo", "Flat", 800));

        // Display original order
        train.displayAllBogiesSorted();
        train.displayPassengerBogies();
        train.displayCargoBogies();

        // UC7 Demonstration: Sort passenger bogies ascending
        System.out.println("\n--- STEP 3: Sort Passenger Bogies by Capacity (Ascending) ---");
        train.sortPassengerBogiesByCapacityAscending();
        train.displayPassengerBogies();

        // UC7 Demonstration: Sort passenger bogies descending
        System.out.println("\n--- STEP 4: Sort Passenger Bogies by Capacity (Descending) ---");
        train.sortPassengerBogiesByCapacityDescending();
        train.displayPassengerBogies();

        // UC7 Demonstration: Sort passenger bogies by type
        System.out.println("\n--- STEP 5: Sort Passenger Bogies by Type (Alphabetical) ---");
        train.sortPassengerBogiesByType();
        train.displayPassengerBogies();

        // UC7 Demonstration: Sort cargo bogies
        System.out.println("\n--- STEP 6: Sort Cargo Bogies by Capacity (Descending) ---");
        train.sortCargoBogiesByCapacityDescending();
        train.displayCargoBogies();

        // UC7 Demonstration: Capacity analysis
        train.displayCapacityAnalysisByType();
        train.displayTrainSummary();

        // Show Comparator behavior
        System.out.println("\n--- Comparator Key Concepts ---");
        System.out.println("1. Comparator defines custom comparison logic for sorting");
        System.out.println("2. Comparator.comparingInt() creates comparator from int field");
        System.out.println("3. .reversed() returns comparator in reverse order");
        System.out.println("4. Comparator.comparing() creates comparator from any field");
        System.out.println("5. Lambda expressions provide concise comparator syntax");
        System.out.println("6. List.sort(comparator) sorts list in-place");
        System.out.println("7. thenComparingInt() chains multiple comparators");
        System.out.println("8. Comparators enable flexible sorting without modifying data");
        System.out.println("9. Perfect for business logic-based ordering");

        // Show different sorting strategies
        System.out.println("\n--- Comparator Strategies Demonstrated ---");
        System.out.println("1. Capacity Ascending: Low capacity → High capacity");
        System.out.println("2. Capacity Descending: High capacity → Low capacity");
        System.out.println("3. Alphabetical Type: Sorted by bogie subtype");
        System.out.println("4. Chained Comparators: Type first, then capacity");
        System.out.println("5. Stream Analytics: Using map, reduce, min, max operations");
    }
}