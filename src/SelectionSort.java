import java.util.List;

public class SelectionSort {

    /**
     * Method to perform Selection Sort on an ArrayList of integers
     *
     * @param arr The ArrayList to be sorted
     * @return The sorted ArrayList
     */
    public List<Integer> sort(List<Integer> arr) {
        int n = arr.size();

        // Check if the list is empty or has only one element
        if (n <= 1) {
            // No need to sort, return the list as is
            return arr;
        }

        for (int i = 0; i < n - 1; i++) {
            // Find the minimum element in the unsorted part of the ArrayList
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr.get(j) < arr.get(minIndex)) {
                    minIndex = j;
                }
            }

            // Swap the found minimum element with the first element
            int temp = arr.get(minIndex);
            arr.set(minIndex, arr.get(i));
            arr.set(i, temp);
        }

        return arr;
    }
}
