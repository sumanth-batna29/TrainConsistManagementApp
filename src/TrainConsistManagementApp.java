
import java.util.Arrays;

public class TrainConsistManagementApp {

    // 🔹 Method to sort bogie names
    public static void sortBogieNames(String[] bogieNames) {
        Arrays.sort(bogieNames); // Built-in sorting
    }

    // 🔹 Display method
    public static void display(String[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    // 🔹 Main method (Demo)
    public static void main(String[] args) {

        String[] bogies = {"Sleeper","AC Chair","First Class","General","Luxury"};

        System.out.println("Before Sorting:");
        display(bogies);

        sortBogieNames(bogies);

        System.out.println("After Sorting:");
        display(bogies);
    }
}