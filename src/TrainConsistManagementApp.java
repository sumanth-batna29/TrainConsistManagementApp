// ===============================
// Custom Runtime Exception
// ===============================
class CargoSafetyException extends RuntimeException {
    public CargoSafetyException(String message) {
        super(message);
    }
}

// ===============================
// Goods Bogie Class
// ===============================
class GoodsBogie {

    private String shape;   // Rectangular / Cylindrical
    private String cargo;

    public GoodsBogie(String shape) {
        this.shape = shape;
        this.cargo = null;
    }

    public String getShape() {
        return shape;
    }

    public String getCargo() {
        return cargo;
    }

    // Cargo Assignment with try-catch-finally
    public void assignCargo(String cargoType) {

        try {
            // ❌ Unsafe condition
            if (shape.equalsIgnoreCase("Rectangular") &&
                    cargoType.equalsIgnoreCase("Petroleum")) {

                throw new CargoSafetyException(
                        "Unsafe: Cannot assign Petroleum to Rectangular bogie");
            }

            // ✅ Safe assignment
            this.cargo = cargoType;
            System.out.println("Cargo assigned: " + cargoType);

        } catch (CargoSafetyException e) {
            System.out.println("Error: " + e.getMessage());

        } finally {
            System.out.println("Cargo assignment attempt completed.");
        }
    }
}

// ===============================
// Main Application Class
// ===============================
public class TrainConsistManagementApp {

    public static void main(String[] args) {

        // ✅ Safe case
        GoodsBogie g1 = new GoodsBogie("Cylindrical");
        g1.assignCargo("Petroleum");

        System.out.println();

        // ❌ Unsafe case
        GoodsBogie g2 = new GoodsBogie("Rectangular");
        g2.assignCargo("Petroleum");

        System.out.println();

        // ✅ Program continues
        GoodsBogie g3 = new GoodsBogie("Rectangular");
        g3.assignCargo("Coal");

        System.out.println("\nProgram continues safely after handling exceptions.");
    }
}