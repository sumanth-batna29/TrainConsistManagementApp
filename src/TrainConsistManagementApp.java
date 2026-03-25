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
        LinkedHashSet<String> insertionOrderConsist = new LinkedHashSet<>();  // UC5: LinkedHashSet for insertion order with uniqueness

        public Train(String trainId, String name) {
            this.trainId = trainId;
            this.name = name;
        }

        // UC5: Add bogie with duplicate check and insertion order preservation
        public void addBogie(Bogie bogie) {
            if (uniqueBogieIds.contains(bogie.bogieId)) {
                System.out.println("✗ ERROR: Bogie ID '" + bogie.bogieId + "' already exists! Duplicate not added.");
                return;
            }
            bogies.add(bogie);
            uniqueBogieIds.add(bogie.bogieId);
            orderedConsist.addLast(bogie.bogieId);
            insertionOrderConsist.add(bogie.bogieId);  // UC5: Add to LinkedHashSet (maintains insertion order)
            System.out.println("✓ Added Bogie: " + bogie.bogieId + " (" + bogie.bogieSubType + ") - Total: " + bogies.size());
        }

        // UC5: Add bogie at the beginning (Engine position)
        public void addBogieAtFirst(Bogie bogie) {
            if (uniqueBogieIds.contains(bogie.bogieId)) {
                System.out.println("✗ ERROR: Bogie ID '" + bogie.bogieId + "' already exists!");
                return;
            }
            bogies.add(0, bogie);
            uniqueBogieIds.add(bogie.bogieId);
            orderedConsist.addFirst(bogie.bogieId);
            insertionOrderConsist.add(bogie.bogieId);  // UC5: Add to LinkedHashSet
            System.out.println("✓ Added Bogie at FIRST position: " + bogie.bogieId + " (" + bogie.bogieSubType + ")");
        }

        // UC5: Add bogie at the end (Guard Coach position)
        public void addBogieAtLast(Bogie bogie) {
            if (uniqueBogieIds.contains(bogie.bogieId)) {
                System.out.println("✗ ERROR: Bogie ID '" + bogie.bogieId + "' already exists!");
                return;
            }
            bogies.add(bogie);
            uniqueBogieIds.add(bogie.bogieId);
            orderedConsist.addLast(bogie.bogieId);
            insertionOrderConsist.add(bogie.bogieId);  // UC5: Add to LinkedHashSet
            System.out.println("✓ Added Bogie at LAST position: " + bogie.bogieId + " (" + bogie.bogieSubType + ")");
        }

        // UC5: Display insertion order consist (LinkedHashSet)
        public void displayInsertionOrderConsist() {
            System.out.println("\n--- TRAIN CONSIST (Insertion Order - LinkedHashSet) ---");
            System.out.println("Total Unique Bogies: " + insertionOrderConsist.size());
            if (insertionOrderConsist.isEmpty()) {
                System.out.println("No bogies in train.");
                return;
            }
            System.out.println("Formation Order: " + String.join(" → ", insertionOrderConsist));
            System.out.println("\nDetailed Insertion Order:");
            int position = 1;
            for (String bogieId : insertionOrderConsist) {
                Bogie bogie = findBogieById(bogieId);
                if (bogie != null) {
                    System.out.println("  Position " + position + ": " + bogie.bogieId + " | " + bogie.bogieSubType + " | Capacity: " + bogie.capacity);
                    position++;
                }
            }
        }

        // Display train summary
        public void displayTrainSummary() {
            System.out.println("\n--- Train Summary ---");
            System.out.println("Train ID: " + trainId);
            System.out.println("Train Name: " + name);
            System.out.println("Total Bogies (ArrayList): " + bogies.size());
            System.out.println("Total Unique Bogies (HashSet): " + uniqueBogieIds.size());
            System.out.println("Total Bogies (LinkedHashSet): " + insertionOrderConsist.size());
        }

        // UC5: Comparison of different data structures
        public void displayDataStructureComparison() {
            System.out.println("\n--- Data Structure Comparison ---");
            System.out.println("ArrayList (Ordered, Allows Duplicates):");
            System.out.println("  Bogies: " + bogies.size() + " elements");
            for (int i = 0; i < bogies.size(); i++) {
                System.out.print("  [" + i + "] " + bogies.get(i).bogieId);
                if (i < bogies.size() - 1) System.out.print(", ");
            }
            System.out.println("\n");

            System.out.println("HashSet (Unordered, No Duplicates):");
            System.out.println("  Bogies: " + uniqueBogieIds.size() + " elements");
            System.out.println("  Elements: " + uniqueBogieIds);
            System.out.println("  (Note: Order is random)\n");

            System.out.println("LinkedHashSet (Insertion Order, No Duplicates):");
            System.out.println("  Bogies: " + insertionOrderConsist.size() + " elements");
            System.out.println("  Elements (in insertion order): " + insertionOrderConsist);
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

    public static void main(String[] args) {
        System.out.println("========== TRAIN CONSIST MANAGEMENT APP ==========");
        System.out.println("UC5: Preserve Insertion Order of Bogies (LinkedHashSet)\n");

        // Create a train
        Train train = new Train("TR001", "Express");

        // UC5 Demonstration: Adding bogies in sequence
        System.out.println("--- STEP 1: Add Bogies in Attachment Sequence ---");
        train.addBogieAtFirst(new Bogie("LOC001", "Engine", "Locomotive", 0));
        train.addBogie(new Bogie("BG101", "Passenger", "Sleeper", 72));
        train.addBogie(new Bogie("BG102", "Passenger", "AC Chair", 90));
        train.addBogie(new Bogie("BG103", "Cargo", "Rectangular", 500));
        train.addBogieAtLast(new Bogie("BG104", "Special", "Guard Coach", 20));

        // Display insertion order
        train.displayInsertionOrderConsist();

        // UC5 Demonstration: Attempt to add duplicate bogies
        System.out.println("\n--- STEP 2: Attempt to Add Duplicate Bogies ---");
        train.addBogie(new Bogie("BG101", "Passenger", "Sleeper", 72));        // Duplicate
        train.addBogie(new Bogie("BG102", "Passenger", "AC Chair", 90));       // Duplicate
        train.addBogie(new Bogie("BG105", "Special", "Pantry Car", 50));       // New
        train.addBogie(new Bogie("LOC001", "Engine", "Locomotive", 0));        // Duplicate

        // Display insertion order after duplicate attempts
        train.displayInsertionOrderConsist();

        // UC5 Demonstration: Add more bogies
        System.out.println("\n--- STEP 3: Add More Bogies ---");
        train.addBogie(new Bogie("BG106", "Cargo", "Cylindrical", 600));
        train.addBogie(new Bogie("BG107", "Passenger", "First Class", 48));

        // Display final insertion order
        train.displayInsertionOrderConsist();

        // Display summaries and comparison
        train.displayTrainSummary();
        train.displayDataStructureComparison();

        // Show LinkedHashSet behavior
        System.out.println("\n--- LinkedHashSet Key Concepts ---");
        System.out.println("1. LinkedHashSet maintains insertion order (like ArrayList)");
        System.out.println("2. LinkedHashSet enforces uniqueness (like HashSet)");
        System.out.println("3. add() automatically prevents duplicate bogies");
        System.out.println("4. Iteration returns elements in insertion order");
        System.out.println("5. Perfect for tracking unique items with order preserved");
        System.out.println("6. Time complexity: add/remove/contains = O(1)");
        System.out.println("7. Space complexity: O(n) with extra links for ordering");
        System.out.println("8. Use LinkedHashSet when you need BOTH uniqueness AND order");
    }
}