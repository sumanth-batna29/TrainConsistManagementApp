
public class TrainConsistManagementAppTest {

    public static void main(String[] args) {

        System.out.println("Running UC17 Test Cases...\n");

        testSort_BasicAlphabeticalSorting();
        testSort_UnsortedInput();
        testSort_AlreadySortedArray();
        testSort_DuplicateBogieNames();
        testSort_SingleElementArray();

        System.out.println("\nAll test cases executed.");
    }

    // 🔹 Utility method to compare arrays
    public static boolean isEqual(String[] a, String[] b) {

        if (a.length != b.length) return false;

        for (int i = 0; i < a.length; i++) {
            if (!a[i].equals(b[i])) return false;
        }

        return true;
    }

    // ✅ Test 1
    public static void testSort_BasicAlphabeticalSorting() {

        String[] input = {"Sleeper","AC Chair","First Class","General","Luxury"};
        String[] expected = {"AC Chair","First Class","General","Luxury","Sleeper"};

        TrainConsistManagementApp.sortBogieNames(input);

        if (isEqual(input, expected))
            System.out.println("testSort_BasicAlphabeticalSorting PASSED");
        else
            System.out.println("testSort_BasicAlphabeticalSorting FAILED");
    }

    // ✅ Test 2
    public static void testSort_UnsortedInput() {

        String[] input = {"Luxury","General","Sleeper","AC Chair"};
        String[] expected = {"AC Chair","General","Luxury","Sleeper"};

        TrainConsistManagementApp.sortBogieNames(input);

        if (isEqual(input, expected))
            System.out.println("testSort_UnsortedInput PASSED");
        else
            System.out.println("testSort_UnsortedInput FAILED");
    }

    // ✅ Test 3
    public static void testSort_AlreadySortedArray() {

        String[] input = {"AC Chair","First Class","General"};
        String[] expected = {"AC Chair","First Class","General"};

        TrainConsistManagementApp.sortBogieNames(input);

        if (isEqual(input, expected))
            System.out.println("testSort_AlreadySortedArray PASSED");
        else
            System.out.println("testSort_AlreadySortedArray FAILED");
    }

    // ✅ Test 4
    public static void testSort_DuplicateBogieNames() {

        String[] input = {"Sleeper","AC Chair","Sleeper","General"};
        String[] expected = {"AC Chair","General","Sleeper","Sleeper"};

        TrainConsistManagementApp.sortBogieNames(input);

        if (isEqual(input, expected))
            System.out.println("testSort_DuplicateBogieNames PASSED");
        else
            System.out.println("testSort_DuplicateBogieNames FAILED");
    }

    // ✅ Test 5
    public static void testSort_SingleElementArray() {

        String[] input = {"Sleeper"};
        String[] expected = {"Sleeper"};

        TrainConsistManagementApp.sortBogieNames(input);

        if (isEqual(input, expected))
            System.out.println("testSort_SingleElementArray PASSED");
        else
            System.out.println("testSort_SingleElementArray FAILED");
    }
}