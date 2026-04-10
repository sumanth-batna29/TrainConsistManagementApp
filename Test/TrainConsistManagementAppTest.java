public class TrainConsistManagementAppTest {

    public static void main(String[] args) {

        System.out.println("Running UC16 Test Cases...\n");

        testSort_BasicSorting();
        testSort_AlreadySortedArray();
        testSort_DuplicateValues();
        testSort_SingleElementArray();
        testSort_AllEqualValues();

        System.out.println("\nAll test cases executed.");
    }

    // 🔹 Utility method to compare arrays
    public static boolean isEqual(int[] a, int[] b) {

        if (a.length != b.length) return false;

        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) return false;
        }

        return true;
    }

    // ✅ Test 1
    public static void testSort_BasicSorting() {

        int[] input = {72, 56, 24, 70, 60};
        int[] expected = {24, 56, 60, 70, 72};

        TrainConsistManagementApp.sortCapacities(input);

        if (isEqual(input, expected))
            System.out.println("testSort_BasicSorting PASSED");
        else
            System.out.println("testSort_BasicSorting FAILED");
    }

    // ✅ Test 2
    public static void testSort_AlreadySortedArray() {

        int[] input = {24, 56, 60, 70, 72};
        int[] expected = {24, 56, 60, 70, 72};

        TrainConsistManagementApp.sortCapacities(input);

        if (isEqual(input, expected))
            System.out.println("testSort_AlreadySortedArray PASSED");
        else
            System.out.println("testSort_AlreadySortedArray FAILED");
    }

    // ✅ Test 3
    public static void testSort_DuplicateValues() {

        int[] input = {72, 56, 56, 24};
        int[] expected = {24, 56, 56, 72};

        TrainConsistManagementApp.sortCapacities(input);

        if (isEqual(input, expected))
            System.out.println("testSort_DuplicateValues PASSED");
        else
            System.out.println("testSort_DuplicateValues FAILED");
    }

    // ✅ Test 4
    public static void testSort_SingleElementArray() {

        int[] input = {50};
        int[] expected = {50};

        TrainConsistManagementApp.sortCapacities(input);

        if (isEqual(input, expected))
            System.out.println("testSort_SingleElementArray PASSED");
        else
            System.out.println("testSort_SingleElementArray FAILED");
    }

    // ✅ Test 5
    public static void testSort_AllEqualValues() {

        int[] input = {40, 40, 40};
        int[] expected = {40, 40, 40};

        TrainConsistManagementApp.sortCapacities(input);

        if (isEqual(input, expected))
            System.out.println("testSort_AllEqualValues PASSED");
        else
            System.out.println("testSort_AllEqualValues FAILED");
    }
}