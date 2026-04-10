public class TrainConsistManagementApp {

    // 🔹 Search method with exception handling
    public static boolean searchBogie(String[] bogieIds, String key) {

        // ✅ Fail-Fast Validation
        if (bogieIds == null || bogieIds.length == 0) {
            throw new IllegalStateException("No bogies available in the train to search.");
        }

        // 🔹 Linear Search (after validation)
        for (int i = 0; i < bogieIds.length; i++) {

            if (bogieIds[i].equals(key)) {
                return true; // Found
            }
        }

        return false; // Not found
    }

    // 🔹 Main method (Demo)
    public static void main(String[] args) {

        String[] bogieIds = {}; // Try empty case

        try {
            boolean result = searchBogie(bogieIds, "BG101");

            if (result) {
                System.out.println("Bogie FOUND");
            } else {
                System.out.println("Bogie NOT FOUND");
            }

        } catch (IllegalStateException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}