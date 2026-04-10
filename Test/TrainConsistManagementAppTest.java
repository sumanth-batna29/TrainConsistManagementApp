public class TrainConsistManagementAppTest {

    public static void main(String[] args) {

        System.out.println("Running UC18 Test Cases...\n");

        testSearch_BogieFound();
        testSearch_BogieNotFound();
        testSearch_FirstElementMatch();
        testSearch_LastElementMatch();
        testSearch_SingleElementArray();

        System.out.println("\nAll test cases executed.");
    }

    // ✅ Test 1
    public static void testSearch_BogieFound() {

        String[] arr = {"BG101","BG205","BG309","BG412","BG550"};

        boolean result = TrainConsistManagementApp.searchBogie(arr, "BG309");

        if (result)
            System.out.println("testSearch_BogieFound PASSED");
        else
            System.out.println("testSearch_BogieFound FAILED");
    }

    // ✅ Test 2
    public static void testSearch_BogieNotFound() {

        String[] arr = {"BG101","BG205","BG309","BG412","BG550"};

        boolean result = TrainConsistManagementApp.searchBogie(arr, "BG999");

        if (!result)
            System.out.println("testSearch_BogieNotFound PASSED");
        else
            System.out.println("testSearch_BogieNotFound FAILED");
    }

    // ✅ Test 3
    public static void testSearch_FirstElementMatch() {

        String[] arr = {"BG101","BG205","BG309","BG412","BG550"};

        boolean result = TrainConsistManagementApp.searchBogie(arr, "BG101");

        if (result)
            System.out.println("testSearch_FirstElementMatch PASSED");
        else
            System.out.println("testSearch_FirstElementMatch FAILED");
    }

    // ✅ Test 4
    public static void testSearch_LastElementMatch() {

        String[] arr = {"BG101","BG205","BG309","BG412","BG550"};

        boolean result = TrainConsistManagementApp.searchBogie(arr, "BG550");

        if (result)
            System.out.println("testSearch_LastElementMatch PASSED");
        else
            System.out.println("testSearch_LastElementMatch FAILED");
    }

    // ✅ Test 5
    public static void testSearch_SingleElementArray() {

        String[] arr = {"BG101"};

        boolean result = TrainConsistManagementApp.searchBogie(arr, "BG101");

        if (result)
            System.out.println("testSearch_SingleElementArray PASSED");
        else
            System.out.println("testSearch_SingleElementArray FAILED");
    }
}