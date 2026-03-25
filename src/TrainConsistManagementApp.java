import java.util.*;

public class TrainConsistManagementApp {

    static class Bogie {
        String bogieId;
        String bogieType; // "Passenger" or "Cargo"
        int capacity;

        public Bogie(String bogieId, String bogieType, int capacity) {
            this.bogieId = bogieId;
            this.bogieType = bogieType;
            this.capacity = capacity;
        }
    }

    static class Train {
        String trainId;
        String name;
        List<Bogie> bogies = new ArrayList<>();
        Set<String> uniqueBogieIds = new HashSet<>();  // UC3: HashSet for unique IDs

        public Train(String trainId, String name) {
            this.trainId = trainId;
            this.name = name;
        }

        // UC2: Add Passenger Bogie
        public void addPassengerBogie(Bogie bogie) {
            if ("Passenger".equals(bogie.bogieType)) {
                bogies.add(bogie);
                uniqueBogieIds.add(bogie.bogieId);  // UC3: Add to HashSet
                System.out.println("✓ Added Passenger Bogie: " + bogie.bogieId);
            }
        }

        // UC2: Add Cargo Bogie
        public void addCargoBogie(Bogie bogie) {
            if ("Cargo".equals(bogie.bogieType)) {
                bogies.add(bogie);
                uniqueBogieIds.add(bogie.bogieId);  // UC3: Add to HashSet
                System.out.println("✓ Added Cargo Bogie: " + bogie.bogieId);
            }
        }

        // UC3: Add bogie with duplicate check
        public void addBogie(Bogie bogie) {
            if (uniqueBogieIds.contains(bogie.bogieId)) {
                System.out.println("✗ ERROR: Bogie ID '" + bogie.bogieId + "' already exists! Duplicate not added.");
                return;
            }
            bogies.add(bogie);
            uniqueBogieIds.add(bogie.bogieId);
            System.out.println("✓ Added Bogie: " + bogie.bogieId + " (" + bogie.bogieType + ")");
        }

        // UC3: Display unique bogie IDs
        public void displayUniqueBogieIds() {
            System.out.println("\n--- Unique Bogie IDs (HashSet) ---");
            System.out.println("Total Unique Bogies: " + uniqueBogieIds.size());
            System.out.println("Bogie IDs: " + uniqueBogieIds);
        }

        // Display train summary
        public void displayTrainSummary() {
            System.out.println("\n--- Train Summary ---");
            System.out.println("Train ID: " + trainId);
            System.out.println("Train Name: " + name);
            System.out.println("Total Bogies: " + bogies.size());
        }

        // Display all bogies
        public void displayAllBogies() {
            System.out.println("\n--- All Bogies in Train ---");
            if (bogies.isEmpty()) {
                System.out.println("No bogies added yet.");
                return;
            }
            for (int i = 0; i < bogies.size(); i++) {
                Bogie b = bogies.get(i);
                System.out.println((i + 1) + ". ID: " + b.bogieId + " | Type: " + b.bogieType + " | Capacity: " + b.capacity);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("========== TRAIN CONSIST MANAGEMENT APP ==========");
        System.out.println("UC3: Track Unique Bogie IDs (HashSet)\n");

        // Create a train
        Train train = new Train("TR001", "Express");

        // UC3 Demonstration: Adding bogies with duplicate check
        System.out.println("--- Adding Bogies (with Duplicate Detection) ---");

        // Add unique bogies
        train.addBogie(new Bogie("BG101", "Passenger", 80));
        train.addBogie(new Bogie("BG102", "Cargo", 500));
        train.addBogie(new Bogie("BG103", "Passenger", 90));
        train.addBogie(new Bogie("BG104", "Cargo", 600));

        // Attempt to add duplicate bogie IDs
        System.out.println("\n--- Attempting to Add Duplicate Bogies ---");
        train.addBogie(new Bogie("BG101", "Passenger", 80));  // Duplicate
        train.addBogie(new Bogie("BG102", "Cargo", 500));     // Duplicate
        train.addBogie(new Bogie("BG105", "Passenger", 75));  // New
        train.addBogie(new Bogie("BG101", "Cargo", 550));     // Duplicate (different type)

        // Display results
        train.displayTrainSummary();
        train.displayAllBogies();
        train.displayUniqueBogieIds();

        // Show HashSet behavior
        System.out.println("\n--- HashSet Key Concepts ---");
        System.out.println("1. HashSet automatically removes duplicates");
        System.out.println("2. Order of elements is NOT guaranteed (unordered)");
        System.out.println("3. Lookup time is O(1) - very fast");
        System.out.println("4. Perfect for tracking unique IDs in real-world systems");
    }
}