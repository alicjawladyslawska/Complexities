import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * TestAlgorithms class
 * Test both MergeSort and SelectionSort algorithms against each other, then write results to a CSV file.
 */
public class TestAlgorithms {

    // Create a static instance of Configuration
    private static Configuration c = new Configuration();
    private static boolean functionalityTest = false;

    /**
     * The main method to initiate the testing of sorting algorithms and write results to a CSV file.
     *
     * @param args Command line arguments (not used in this context).
     */
    public static void main(String[] args) {
        // Create instances of both sort classes
        MergeSort m = new MergeSort();
        SelectionSort s = new SelectionSort();

        // Clear the CSV file and write headers
        clearAndWriteHeaders();

        // Run tests for different array sizes
        test(m, s, "unsortedArray10");
        test(m, s, "unsortedArray25");
        test(m, s, "unsortedArray35");
        test(m, s, "unsortedArray50");
        test(m, s, "unsortedArray100");
        test(m, s, "unsortedArray250");
        test(m, s, "unsortedArray500");
        test(m, s, "unsortedArray1000"); 
        test(m, s, "unsortedArray10000");

        // Functionality test cases here - to check if the array sorts correctly (3 cases - random order, reverse order, and already sorted)
        //set the functionality flag to true to avoid writing to the csv file
        functionalityTest = true;
        test(m, s, "functionalityArrayRandomised");
        test(m, s, "functionalityArrayReversed");
        test(m, s, "functionalityArrayOrdered");
        functionalityTest = false;


        //Test exceptional cases here - ensure that no error will be thrown if the list is null
        System.out.println("-----");
        System.out.println("Exception case testing");
        test(m, s, "emptyArray");
    }

    /**
     * Test the MergeSort and SelectionSort algorithms on an array of a specific size, and write results to CSV.
     *
     * @param mergeSort    The MergeSort algorithm instance.
     * @param selectionSort The SelectionSort algorithm instance.
     * @param arrayKey     The key to retrieve the unsorted array from the Configuration.
     */
    private static void test(MergeSort mergeSort, SelectionSort selectionSort, String arrayKey) {
        System.out.println("**********");
        System.out.println("Array of size " + c.getArraysMap().get(arrayKey).size());
        if(functionalityTest){
            System.out.println("Initial Array: " + c.getArraysMap().get(arrayKey));
        }
        
        long startTime;
        long endTime;
        long elapsedTime;
        long totalMerge = 0;
        long totalSelection = 0;

        for (int i = 0; i <= 4; i++) {
            // Merge Sort
            startTime = System.nanoTime();
            mergeSort.sort(c.getArraysMap().get(arrayKey));
            endTime = System.nanoTime();
            elapsedTime = endTime - startTime;
            totalMerge += elapsedTime;

            // Selection Sort
            startTime = System.nanoTime();
            selectionSort.sort(c.getArraysMap().get(arrayKey));
            endTime = System.nanoTime();
            elapsedTime = endTime - startTime;
            totalSelection += elapsedTime;
        }

        long mergeAverageTime = totalMerge / 5;
        System.out.println("Merge Sort time elapsed: " + mergeAverageTime + " nanoseconds (on average).");

        long selectionAverageTime = totalSelection / 5;
        System.out.println("Selection Sort time elapsed: " + selectionAverageTime + " nanoseconds (on average).");

        if (functionalityTest) {
            System.out.println("Merge-Sorted Array: " + mergeSort.sort(c.getArraysMap().get(arrayKey)));
            System.out.println("Selection-Sorted Array: " + selectionSort.sort(c.getArraysMap().get(arrayKey)));
            return;
        }

        writeToCSV(c.getArraysMap().get(arrayKey).size(), mergeAverageTime, selectionAverageTime);
    }

    /**
     * Clear the CSV file and write the headers.
     */
    private static void clearAndWriteHeaders() {
        String filePath = "../AlgorithmResults.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            // Writing headers to the CSV file
            writer.write("List Size, Merge Sort Runtime, Selection Sort Runtime\n");
            System.out.println("CSV file cleared and headers written successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Write information to a CSV file.
     *
     * @param listSize       The size of the array being tested.
     * @param mergeTime      The average time taken by Merge Sort.
     * @param selectionTime  The average time taken by Selection Sort.
     */
    public static void writeToCSV(int listSize, long mergeTime, long selectionTime) {
        // Check for exceptional conditions, e.g., listSize is 0
        if (listSize <= 0 || functionalityTest == true) {
            System.out.println("Skipping CSV write for exceptional case.");
            return;
        }

        String filePath = "../AlgorithmResults.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            // Appending a new line with list size, merge sort time, and selection sort time
            writer.write(listSize + "," + mergeTime + "," + selectionTime + "\n");
            System.out.println("Data appended to CSV successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
