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
        HashMap<String, Integer> bogieCapacityMap = new HashMap<>();  // UC6: HashMap for bogie-capacity mapping

        public Train(String trainId, String name) {
            this.trainId = trainId;
            this.name = name;
        }

        // UC6: Add bogie with capacity mapping
        public void addBogie(Bogie bogie) {
            if (uniqueBogieIds.contains(bogie.bogieId)) {
                System.out.println("✗ ERROR: Bogie ID '" + bogie.bogieId + "' already exists! Duplicate not added.");
                return;
            }
            bogies.add(bogie);
            uniqueBogieIds.add(bogie.bogieId);
            orderedConsist.addLast(bogie.bogieId);
            insertionOrderConsist.add(bogie.bogieId);
            bogieCapacityMap.put(bogie.bogieId, bogie.capacity);  // UC6: Map bogie ID to capacity
            System.out.println("✓ Added Bogie: " + bogie.bogieId + " (" + bogie.bogieSubType + ") | Capacity: " + bogie.capacity);
        }

        // UC6: Add bogie at the beginning (Engine position)
        public void addBogieAtFirst(Bogie bogie) {
            if (uniqueBogieIds.contains(bogie.bogieId)) {
                System.out.println("✗ ERROR: Bogie ID '" + bogie.bogieId + "' already exists!");
                return;
            }
            bogies.add(0, bogie);
            uniqueBogieIds.add(bogie.bogieId);
            orderedConsist.addFirst(bogie.bogieId);
            insertionOrderConsist.add(bogie.bogieId);
            bogieCapacityMap.put(bogie.bogieId, bogie.capacity);  // UC6: Map bogie ID to capacity
            System.out.println("✓ Added Bogie at FIRST position: " + bogie.bogieId + " (" + bogie.bogieSubType + ") | Capacity: " + bogie.capacity);
        }

        // UC6: Add bogie at the end (Guard Coach position)
        public void addBogieAtLast(Bogie bogie) {
            if (uniqueBogieIds.contains(bogie.bogieId)) {
                System.out.println("✗ ERROR: Bogie ID '" + bogie.bogieId + "' already exists!");
                return;
            }
            bogies.add(bogie);
            uniqueBogieIds.add(bogie.bogieId);
            orderedConsist.addLast(bogie.bogieId);
            insertionOrderConsist.add(bogie.bogieId);
            bogieCapacityMap.put(bogie.bogieId, bogie.capacity);  // UC6: Map bogie ID to capacity
            System.out.println("✓ Added Bogie at LAST position: " + bogie.bogieId + " (" + bogie.bogieSubType + ") | Capacity: " + bogie.capacity);
        }

        // UC6: Display bogie-capacity mapping (HashMap)
        public void displayBogieCapacityMap() {
            System.out.println("\n--- BOGIE CAPACITY MAP (HashMap) ---");
            System.out.println("Total Mappings: " + bogieCapacityMap.size());
            if (bogieCapacityMap.isEmpty()) {
                System.out.println("No bogie-capacity mappings.");
                return;
            }

            System.out.println("\nUsing entrySet() for iteration:");
            int serialNo = 1;
            for (Map.Entry<String, Integer> entry : bogieCapacityMap.entrySet()) {
                String bogieId = entry.getKey();
                Integer capacity = entry.getValue();
                Bogie bogie = findBogieById(bogieId);
                String subType = (bogie != null) ? bogie.bogieSubType : "Unknown";
                System.out.println("  " + serialNo + ". Bogie ID: " + bogieId + " | Type: " + subType + " | Capacity: " + capacity);
                serialNo++;
            }
        }

        // UC6: Display using keySet() iteration
        public void displayBogieCapacityUsingKeySet() {
            System.out.println("\n--- BOGIE CAPACITY MAP (Using keySet()) ---");
            System.out.println("Bogie IDs (Keys): " + bogieCapacityMap.keySet());
            System.out.println("\nDetailed View:");
            for (String bogieId : bogieCapacityMap.keySet()) {
                Integer capacity = bogieCapacityMap.get(bogieId);  // UC6: Lookup capacity using key
                Bogie bogie = findBogieById(bogieId);
                String subType = (bogie != null) ? bogie.bogieSubType : "Unknown";
                System.out.println("  → " + bogieId + " [" + subType + "]: " + capacity);
            }
        }

        // UC6: Display using values() iteration
        public void displayCapacityValues() {
            System.out.println("\n--- CAPACITY VALUES (Using values()) ---");
            System.out.println("All Capacity Values: " + bogieCapacityMap.values());
            System.out.println("\nCapacity Analysis:");
            int totalCapacity = 0;
            int maxCapacity = 0;
            int minCapacity = Integer.MAX_VALUE;

            for (Integer capacity : bogieCapacityMap.values()) {
                totalCapacity += capacity;
                maxCapacity = Math.max(maxCapacity, capacity);
                minCapacity = Math.min(minCapacity, capacity);
            }

            System.out.println("  Total Capacity: " + totalCapacity);
            System.out.println("  Maximum Capacity: " + maxCapacity);
            System.out.println("  Minimum Capacity: " + minCapacity);
            System.out.println("  Average Capacity: " + (totalCapacity / bogieCapacityMap.size()));
        }

        // UC6: Check if bogie exists in map and get its capacity
        public void getCapacityOfBogie(String bogieId) {
            if (bogieCapacityMap.containsKey(bogieId)) {
                Integer capacity = bogieCapacityMap.get(bogieId);
                System.out.println("✓ Bogie '" + bogieId + "' found with capacity: " + capacity);
            } else {
                System.out.println("✗ Bogie '" + bogieId + "' not found in map.");
            }
        }

        // UC6: Update capacity of existing bogie
        public void updateBogieCapacity(String bogieId, int newCapacity) {
            if (bogieCapacityMap.containsKey(bogieId)) {
                int oldCapacity = bogieCapacityMap.get(bogieId);
                bogieCapacityMap.put(bogieId, newCapacity);
                System.out.println("✓ Updated capacity for bogie '" + bogieId + "': " + oldCapacity + " → " + newCapacity);
            } else {
                System.out.println("✗ Bogie '" + bogieId + "' not found in map.");
            }
        }

        // UC6: Display complete train with all data structures
        public void displayCompleteTrainInfo() {
            System.out.println("\n--- COMPLETE TRAIN INFORMATION ---");
            System.out.println("Train ID: " + trainId);
            System.out.println("Train Name: " + name);
            System.out.println("Total Bogies: " + bogies.size());

            System.out.println("\nTrain Consist (Insertion Order):");
            int position = 1;
            for (String bogieId : insertionOrderConsist) {
                Integer capacity = bogieCapacityMap.get(bogieId);
                Bogie bogie = findBogieById(bogieId);
                String subType = (bogie != null) ? bogie.bogieSubType : "Unknown";
                System.out.println("  " + position + ". [" + bogieId + "] " + subType + " - Capacity: " + capacity);
                position++;
            }
        }

        // UC6: Data structure comparison
        public void displayDataStructureComparison() {
            System.out.println("\n--- DATA STRUCTURE COMPARISON ---");
            System.out.println("\n1. ArrayList (bogies):");
            System.out.println("   Purpose: Store complete Bogie objects");
            System.out.println("   Elements: " + bogies.size());
            for (Bogie b : bogies) {
                System.out.println("   - " + b);
            }

            System.out.println("\n2. HashSet (uniqueBogieIds):");
            System.out.println("   Purpose: Ensure uniqueness");
            System.out.println("   Elements: " + uniqueBogieIds);

            System.out.println("\n3. LinkedHashSet (insertionOrderConsist):");
            System.out.println("   Purpose: Maintain insertion order with uniqueness");
            System.out.println("   Elements: " + insertionOrderConsist);

            System.out.println("\n4. HashMap (bogieCapacityMap):");
            System.out.println("   Purpose: Map bogie ID to capacity (key-value pairs)");
            System.out.println("   Mappings: " + bogieCapacityMap);
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
        System.out.println("UC6: Map Bogie to Capacity (HashMap)\n");

        // Create a train
        Train train = new Train("TR001", "Express");

        // UC6 Demonstration: Adding bogies with capacity mapping
        System.out.println("--- STEP 1: Add Bogies with Capacity Information ---");
        train.addBogieAtFirst(new Bogie("LOC001", "Engine", "Locomotive", 0));
        train.addBogie(new Bogie("BG101", "Passenger", "Sleeper", 72));
        train.addBogie(new Bogie("BG102", "Passenger", "AC Chair", 90));
        train.addBogie(new Bogie("BG103", "Passenger", "First Class", 48));
        train.addBogie(new Bogie("BG104", "Cargo", "Rectangular", 500));
        train.addBogie(new Bogie("BG105", "Cargo", "Cylindrical", 600));
        train.addBogieAtLast(new Bogie("BG106", "Special", "Guard Coach", 20));

        // Display HashMap using entrySet()
        train.displayBogieCapacityMap();

        // Display HashMap using keySet()
        System.out.println("\n--- STEP 2: Access Using keySet() ---");
        train.displayBogieCapacityUsingKeySet();

        // Display capacity values and analytics
        System.out.println("\n--- STEP 3: Capacity Analysis ---");
        train.displayCapacityValues();

        // UC6 Demonstration: Lookup capacity using key
        System.out.println("\n--- STEP 4: Lookup Capacity for Specific Bogie ---");
        train.getCapacityOfBogie("BG101");
        train.getCapacityOfBogie("BG104");
        train.getCapacityOfBogie("BG999");  // Non-existent bogie

        // UC6 Demonstration: Update capacity
        System.out.println("\n--- STEP 5: Update Bogie Capacity ---");
        train.updateBogieCapacity("BG102", 95);
        train.updateBogieCapacity("BG104", 550);
        train.updateBogieCapacity("BG999", 100);  // Non-existent bogie

        // Display updated capacity map
        System.out.println("\n--- STEP 6: Display Updated Capacity Map ---");
        train.displayBogieCapacityMap();

        // Display complete train information
        train.displayCompleteTrainInfo();

        // Display data structure comparison
        train.displayDataStructureComparison();

        // Show HashMap behavior
        System.out.println("\n--- HashMap Key Concepts ---");
        System.out.println("1. HashMap stores data as key-value pairs");
        System.out.println("2. put(key, value) inserts or updates mappings");
        System.out.println("3. get(key) retrieves value in O(1) constant time");
        System.out.println("4. containsKey(key) checks if key exists");
        System.out.println("5. entrySet() returns all key-value pairs");
        System.out.println("6. keySet() returns all keys");
        System.out.println("7. values() returns all values");
        System.out.println("8. HashMap does NOT maintain insertion order (use LinkedHashMap for order)");
        System.out.println("9. Perfect for fast lookups and attribute associations");
    }
}