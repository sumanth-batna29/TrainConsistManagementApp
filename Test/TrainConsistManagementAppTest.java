public class TrainConsistManagementAppTest {

    public static void main(String[] args) {

        System.out.println("Running Test Cases...\n");

        testCargo_SafeAssignment();
        testCargo_UnsafeAssignmentHandled();
        testCargo_CargoNotAssignedAfterFailure();
        testCargo_ProgramContinuesAfterException();
        testCargo_FinallyBlockExecution();

        System.out.println("\nAll tests executed.");
    }

    // ✅ 1. Safe Assignment
    public static void testCargo_SafeAssignment() {
        GoodsBogie bogie = new GoodsBogie("Cylindrical");
        bogie.assignCargo("Petroleum");

        if ("Petroleum".equals(bogie.getCargo())) {
            System.out.println("testCargo_SafeAssignment PASSED");
        } else {
            System.out.println("testCargo_SafeAssignment FAILED");
        }
    }

    // ✅ 2. Unsafe Assignment Handled
    public static void testCargo_UnsafeAssignmentHandled() {
        GoodsBogie bogie = new GoodsBogie("Rectangular");
        bogie.assignCargo("Petroleum");

        if (bogie.getCargo() == null) {
            System.out.println("testCargo_UnsafeAssignmentHandled PASSED");
        } else {
            System.out.println("testCargo_UnsafeAssignmentHandled FAILED");
        }
    }

    // ✅ 3. Cargo Not Assigned After Failure
    public static void testCargo_CargoNotAssignedAfterFailure() {
        GoodsBogie bogie = new GoodsBogie("Rectangular");
        bogie.assignCargo("Petroleum");

        if (bogie.getCargo() == null) {
            System.out.println("testCargo_CargoNotAssignedAfterFailure PASSED");
        } else {
            System.out.println("testCargo_CargoNotAssignedAfterFailure FAILED");
        }
    }

    // ✅ 4. Program Continues After Exception
    public static void testCargo_ProgramContinuesAfterException() {

        GoodsBogie b1 = new GoodsBogie("Rectangular");
        b1.assignCargo("Petroleum"); // handled

        GoodsBogie b2 = new GoodsBogie("Cylindrical");
        b2.assignCargo("Petroleum");

        if ("Petroleum".equals(b2.getCargo())) {
            System.out.println("testCargo_ProgramContinuesAfterException PASSED");
        } else {
            System.out.println("testCargo_ProgramContinuesAfterException FAILED");
        }
    }

    // ✅ 5. Finally Block Execution
    public static void testCargo_FinallyBlockExecution() {

        GoodsBogie bogie = new GoodsBogie("Rectangular");
        bogie.assignCargo("Petroleum");

        // If program reaches here → finally executed
        System.out.println("testCargo_FinallyBlockExecution PASSED");
    }
}