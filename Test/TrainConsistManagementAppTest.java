public class TrainConsistManagementAppTest {

    public static void main(String[] args) {

        System.out.println("Running UC19 Test Cases...\n");

        testBinarySearch_BogieFound();
        testBinarySearch_BogieNotFound();
        testBinarySearch_FirstElementMatch();
        testBinarySearch_LastElementMatch();
        testBinarySearch_SingleElementArray();
        testBinarySearch_EmptyArray();
        testBinarySearch_UnsortedInputHandled();

        System.out.println("\nAll test cases executed.");
    }

    // ✅ Test 1
    public static void testBinarySearch_BogieFound() {

        String[] arr = {"BG101","BG205","BG309","BG412","BG550"};

        boolean result = TrainConsistManagementApp.binarySearchBogie(arr, "BG309");

        if (result)
            System.out.println("testBinarySearch_BogieFound PASSED");
        else
            System.out.println("testBinarySearch_BogieFound FAILED");
    }

    // ✅ Test 2
    public static void testBinarySearch_BogieNotFound() {

        String[] arr = {"BG101","BG205","BG309","BG412","BG550"};

        boolean result = TrainConsistManagementApp.binarySearchBogie(arr, "BG999");

        if (!result)
            System.out.println("testBinarySearch_BogieNotFound PASSED");
        else
            System.out.println("testBinarySearch_BogieNotFound FAILED");
    }

    // ✅ Test 3
    public static void testBinarySearch_FirstElementMatch() {

        String[] arr = {"BG101","BG205","BG309","BG412","BG550"};

        boolean result = TrainConsistManagementApp.binarySearchBogie(arr, "BG101");

        if (result)
            System.out.println("testBinarySearch_FirstElementMatch PASSED");
        else
            System.out.println("testBinarySearch_FirstElementMatch FAILED");
    }

    // ✅ Test 4
    public static void testBinarySearch_LastElementMatch() {

        String[] arr = {"BG101","BG205","BG309","BG412","BG550"};

        boolean result = TrainConsistManagementApp.binarySearchBogie(arr, "BG550");

        if (result)
            System.out.println("testBinarySearch_LastElementMatch PASSED");
        else
            System.out.println("testBinarySearch_LastElementMatch FAILED");
    }

    // ✅ Test 5
    public static void testBinarySearch_SingleElementArray() {

        String[] arr = {"BG101"};

        boolean result = TrainConsistManagementApp.binarySearchBogie(arr, "BG101");

        if (result)
            System.out.println("testBinarySearch_SingleElementArray PASSED");
        else
            System.out.println("testBinarySearch_SingleElementArray FAILED");
    }

    // ✅ Test 6
    public static void testBinarySearch_EmptyArray() {

        String[] arr = {};

        boolean result = TrainConsistManagementApp.binarySearchBogie(arr, "BG101");

        if (!result)
            System.out.println("testBinarySearch_EmptyArray PASSED");
        else
            System.out.println("testBinarySearch_EmptyArray FAILED");
    }

    // ✅ Test 7
    public static void testBinarySearch_UnsortedInputHandled() {

        String[] arr = {"BG309","BG101","BG550","BG205","BG412"};

        boolean result = TrainConsistManagementApp.binarySearchBogie(arr, "BG205");

        if (result)
            System.out.println("testBinarySearch_UnsortedInputHandled PASSED");
        else
            System.out.println("testBinarySearch_UnsortedInputHandled FAILED");
    }
}