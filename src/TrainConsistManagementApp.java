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
        LinkedList<String> orderedConsist = new LinkedList<>();  // UC4: LinkedList for ordered sequence

        public Train(String trainId, String name) {
            this.trainId = trainId;
            this.name = name;
        }

        // UC4: Add bogie with duplicate check and order preservation
        public void addBogie(Bogie bogie) {
            if (uniqueBogieIds.contains(bogie.bogieId)) {
                System.out.println("✗ ERROR: Bogie ID '" + bogie.bogieId + "' already exists! Duplicate not added.");
                return;
            }
            bogies.add(bogie);
            uniqueBogieIds.add(bogie.bogieId);
            orderedConsist.addLast(bogie.bogieId);  // UC4: Add to end of LinkedList
            System.out.println("✓ Added Bogie: " + bogie.bogieId + " (" + bogie.bogieSubType + ") - Position: " + orderedConsist.size());
        }

        // UC4: Add bogie at the beginning (Engine position)
        public void addBogieAtFirst(Bogie bogie) {
            if (uniqueBogieIds.contains(bogie.bogieId)) {
                System.out.println("✗ ERROR: Bogie ID '" + bogie.bogieId + "' already exists!");
                return;
            }
            bogies.add(0, bogie);
            uniqueBogieIds.add(bogie.bogieId);
            orderedConsist.addFirst(bogie.bogieId);  // UC4: Add to beginning
            System.out.println("✓ Added Bogie at FIRST position: " + bogie.bogieId + " (" + bogie.bogieSubType + ")");
        }

        // UC4: Add bogie at the end (Guard Coach position)
        public void addBogieAtLast(Bogie bogie) {
            if (uniqueBogieIds.contains(bogie.bogieId)) {
                System.out.println("✗ ERROR: Bogie ID '" + bogie.bogieId + "' already exists!");
                return;
            }
            bogies.add(bogie);
            uniqueBogieIds.add(bogie.bogieId);
            orderedConsist.addLast(bogie.bogieId);  // UC4: Add to end
            System.out.println("✓ Added Bogie at LAST position: " + bogie.bogieId + " (" + bogie.bogieSubType + ")");
        }

        // UC4: Insert bogie at specific position
        public void insertBogieAtPosition(Bogie bogie, int position) {
            if (uniqueBogieIds.contains(bogie.bogieId)) {
                System.out.println("✗ ERROR: Bogie ID '" + bogie.bogieId + "' already exists!");
                return;
            }
            if (position < 0 || position > bogies.size()) {
                System.out.println("✗ ERROR: Invalid position " + position + "! Valid range: 0-" + bogies.size());
                return;
            }
            bogies.add(position, bogie);
            uniqueBogieIds.add(bogie.bogieId);
            orderedConsist.add(position, bogie.bogieId);  // UC4: Insert at specific index
            System.out.println("✓ Inserted Bogie at position " + position + ": " + bogie.bogieId + " (" + bogie.bogieSubType + ")");
        }

        // UC4: Remove bogie from first position (remove engine)
        public void removeFirstBogie() {
            if (orderedConsist.isEmpty()) {
                System.out.println("✗ ERROR: No bogies to remove!");
                return;
            }
            String removedId = orderedConsist.removeFirst();  // UC4: Remove from beginning
            uniqueBogieIds.remove(removedId);
            bogies.remove(0);
            System.out.println("✓ Removed FIRST Bogie: " + removedId);
        }

        // UC4: Remove bogie from last position (remove guard coach)
        public void removeLastBogie() {
            if (orderedConsist.isEmpty()) {
                System.out.println("✗ ERROR: No bogies to remove!");
                return;
            }
            String removedId = orderedConsist.removeLast();  // UC4: Remove from end
            uniqueBogieIds.remove(removedId);
            bogies.remove(bogies.size() - 1);
            System.out.println("✓ Removed LAST Bogie: " + removedId);
        }

        // UC4: Display train consist in order (physical sequence)
        public void displayOrderedConsist() {
            System.out.println("\n--- TRAIN CONSIST (Ordered Sequence) ---");
            System.out.println("Total Bogies: " + orderedConsist.size());
            if (orderedConsist.isEmpty()) {
                System.out.println("No bogies in train.");
                return;
            }
            System.out.println("Consist: Locomotive ← → " + String.join(" ← → ", orderedConsist) + " ← → Guard Coach");
            System.out.println("\nDetailed Order:");
            for (int i = 0; i < orderedConsist.size(); i++) {
                String bogieId = orderedConsist.get(i);
                Bogie bogie = findBogieById(bogieId);
                if (bogie != null) {
                    System.out.println("  Position " + (i + 1) + ": " + bogie.bogieId + " | " + bogie.bogieSubType + " | Capacity: " + bogie.capacity);
                }
            }
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
        System.out.println("UC4: Maintain Ordered Bogie IDs (LinkedList)\n");

        // Create a train
        Train train = new Train("TR001", "Express");

        // UC4 Demonstration: Building train in physical sequence
        System.out.println("--- STEP 1: Add Bogies to Train (in sequence) ---");
        train.addBogieAtFirst(new Bogie("LOC001", "Engine", "Locomotive", 0));
        train.addBogie(new Bogie("BG101", "Passenger", "Sleeper", 72));
        train.addBogie(new Bogie("BG102", "Passenger", "AC Chair", 90));
        train.addBogie(new Bogie("BG103", "Cargo", "Rectangular", 500));
        train.addBogieAtLast(new Bogie("BG104", "Special", "Guard Coach", 20));

        // Display current consist
        train.displayOrderedConsist();

        // UC4 Demonstration: Insert Pantry Car at position 2
        System.out.println("\n--- STEP 2: Insert Pantry Car at Position 2 ---");
        train.insertBogieAtPosition(new Bogie("BG105", "Special", "Pantry Car", 50), 2);
        train.displayOrderedConsist();

        // UC4 Demonstration: Remove first and last bogies
        System.out.println("\n--- STEP 3: Remove First and Last Bogies ---");
        train.removeFirstBogie();
        train.displayOrderedConsist();

        train.removeLastBogie();
        train.displayOrderedConsist();

        // Display summaries
        train.displayTrainSummary();
        train.displayUniqueBogieIds();

        // Show LinkedList behavior
        System.out.println("\n--- LinkedList Key Concepts ---");
        System.out.println("1. LinkedList maintains insertion order (physical sequence)");
        System.out.println("2. addFirst() / addLast() attach bogies at head or tail");
        System.out.println("3. add(index, element) inserts at specific position");
        System.out.println("4. removeFirst() / removeLast() detach from head or tail");
        System.out.println("5. Each element is connected via node references (not indexes)");
        System.out.println("6. Perfect for modeling train chaining behavior");
        System.out.println("7. Insertion/deletion at ends: O(1)");
        System.out.println("8. Insertion/deletion at middle: O(n)");
    }
}