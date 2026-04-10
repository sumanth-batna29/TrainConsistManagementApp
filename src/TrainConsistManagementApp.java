import java.util.Arrays;

public class TrainConsistManagementApp {

    // 🔹 Binary Search Method
    public static boolean binarySearchBogie(String[] bogieIds, String key) {

        // Handle empty array
        if (bogieIds == null || bogieIds.length == 0) {
            return false;
        }

        // 🔹 Ensure array is sorted (important condition)
        Arrays.sort(bogieIds);

        int low = 0;
        int high = bogieIds.length - 1;

        while (low <= high) {

            int mid = (low + high) / 2;

            int cmp = key.compareTo(bogieIds[mid]);

            if (cmp == 0) {
                return true; // Found
            } else if (cmp < 0) {
                high = mid - 1; // Search left
            } else {
                low = mid + 1; // Search right
            }
        }

        return false; // Not found
    }

    // 🔹 Main method (Demo)
    public static void main(String[] args) {

        String[] bogieIds = {"BG309","BG101","BG550","BG205","BG412"};

        String key = "BG205";

        boolean result = binarySearchBogie(bogieIds, key);

        if (result) {
            System.out.println("Bogie ID " + key + " FOUND");
        } else {
            System.out.println("Bogie ID " + key + " NOT FOUND");
        }
    }
}