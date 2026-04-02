// ✅ IMPORTS AT TOP
import java.util.ArrayList;
import java.util.List;

// ===============================
// Custom Exception Class
// ===============================
class InvalidCapacityException extends Exception {
    public InvalidCapacityException(String message) {
        super(message);
    }
}

// ===============================
// Passenger Bogie Class
// ===============================
class PassengerBogie {

    private String type;
    private int capacity;

    // Constructor with validation (Fail-Fast)
    public PassengerBogie(String type, int capacity) throws InvalidCapacityException {
        if (capacity <= 0) {
            throw new InvalidCapacityException("Capacity must be greater than zero");
        }
        this.type = type;
        this.capacity = capacity;
    }

    // Getters (needed for test cases)
    public String getType() {
        return type;
    }

    public int getCapacity() {
        return capacity;
    }

    // Display method
    public void display() {
        System.out.println("Bogie Type: " + type + ", Capacity: " + capacity);
    }
}

// ===============================
// Train Consist Management App
// ===============================
public class TrainConsistManagementApp {

    private List<PassengerBogie> bogies;

    // Constructor (name must match class)
    public TrainConsistManagementApp() {
        bogies = new ArrayList<>();
    }

    // Add bogie
    public void addBogie(PassengerBogie bogie) {
        bogies.add(bogie);
    }

    // Display consist
    public void displayConsist() {
        System.out.println("\nTrain Consist:");
        for (PassengerBogie b : bogies) {
            b.display();
        }
    }

    // ===============================
    // Main Method
    // ===============================
    public static void main(String[] args) {

        TrainConsistManagementApp app = new TrainConsistManagementApp();

        try {
            // ✅ Valid bogies
            PassengerBogie b1 = new PassengerBogie("Sleeper", 72);
            PassengerBogie b2 = new PassengerBogie("AC Chair", 50);

            app.addBogie(b1);
            app.addBogie(b2);

            // ❌ Invalid bogie (throws exception)
            PassengerBogie b3 = new PassengerBogie("First Class", 0);
            app.addBogie(b3);

        } catch (InvalidCapacityException e) {
            System.out.println("Exception: " + e.getMessage());
        }

        // Program continues safely
        app.displayConsist();
        System.out.println("\nExecution completed successfully.");
    }
}