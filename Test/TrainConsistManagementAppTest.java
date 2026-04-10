public class TrainConsistManagementAppTest {

    public static void main(String[] args) {

        System.out.println("Running UC20 Test Cases...\n");

        testSearch_ThrowsExceptionWhenEmpty();
        testSearch_AllowsSearchWhenDataExists();
        testSearch_BogieFoundAfterValidation();
        testSearch_BogieNotFoundAfterValidation();
        testSearch_SingleElementValidCase();

        System.out.println("\nAll test cases executed.");
    }

    // ✅ Test 1: Exception case
    public static void testSearch_ThrowsExceptionWhenEmpty() {

        String[] arr = {};

        try {
            TrainConsistManagementApp.searchBogie(arr, "BG101");
            System.out.println("testSearch_ThrowsExceptionWhenEmpty FAILED");
        } catch (IllegalStateException e) {
            System.out.println("testSearch_ThrowsExceptionWhenEmpty PASSED");
        }
    }

    // ✅ Test 2: No exception when data exists
    public static void testSearch_AllowsSearchWhenDataExists() {

        String[] arr = {"BG101","BG205"};

        try {
            TrainConsistManagementApp.searchBogie(arr, "BG101");
            System.out.println("testSearch_AllowsSearchWhenDataExists PASSED");
        } catch (Exception e) {
            System.out.println("testSearch_AllowsSearchWhenDataExists FAILED");
        }
    }

    // ✅ Test 3: Found after validation
    public static void testSearch_BogieFoundAfterValidation() {

        String[] arr = {"BG101","BG205","BG309"};

        boolean result = TrainConsistManagementApp.searchBogie(arr, "BG205");

        if (result)
            System.out.println("testSearch_BogieFoundAfterValidation PASSED");
        else
            System.out.println("testSearch_BogieFoundAfterValidation FAILED");
    }

    // ✅ Test 4: Not found after validation
    public static void testSearch_BogieNotFoundAfterValidation() {

        String[] arr = {"BG101","BG205","BG309"};

        boolean result = TrainConsistManagementApp.searchBogie(arr, "BG999");

        if (!result)
            System.out.println("testSearch_BogieNotFoundAfterValidation PASSED");
        else
            System.out.println("testSearch_BogieNotFoundAfterValidation FAILED");
    }

    // ✅ Test 5: Single element case
    public static void testSearch_SingleElementValidCase() {

        String[] arr = {"BG101"};

        boolean result = TrainConsistManagementApp.searchBogie(arr, "BG101");

        if (result)
            System.out.println("testSearch_SingleElementValidCase PASSED");
        else
            System.out.println("testSearch_SingleElementValidCase FAILED");
    }
}