import java.util.ArrayList;
import java.util.List;

public class MergeSort {

    /**
     * Sorts the given ArrayList using the merge sort algorithm
     *
     * @param arrayList The ArrayList to be sorted
     * @return The sorted ArrayList
     */
    public List<Integer> sort(List<Integer> arrayList) {

        // Optimization - if arrayList is null or of size 1, it is already sorted
        if (arrayList == null || arrayList.size() <= 1) {
            return arrayList;
        }

        // Split the ArrayList into two halves
        int middle = arrayList.size() / 2;
        List<Integer> left = new ArrayList<>(arrayList.subList(0, middle));
        List<Integer> right = new ArrayList<>(arrayList.subList(middle, arrayList.size()));

        // Recursive calls to sort the two halves
        left = sort(left);
        right = sort(right);

        // Merge the sorted halves
        List<Integer> result = merge(arrayList, left, right);

        return result;
    }

    /**
     * Merges two sorted ArrayLists into one sorted ArrayList
     *
     * @param arrayList The ArrayList to merge into
     * @param left      The left sorted ArrayList
     * @param right     The right sorted ArrayList
     * @return The merged sorted ArrayList
     */
    private List<Integer> merge(List<Integer> arrayList, List<Integer> left, List<Integer> right) {
        // Pointers to traverse through ArrayList and sublists
        int i = 0, j = 0, k = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i) <= right.get(j)) {
                arrayList.set(k, left.get(i));
                i++;
            } else {
                arrayList.set(k, right.get(j));
                j++;
            }
            k++;
        }

        // Copy the remaining elements of left and right sublists
        while (i < left.size()) {
            arrayList.set(k, left.get(i));
            i++;
            k++;
        }

        while (j < right.size()) {
            arrayList.set(k, right.get(j));
            j++;
            k++;
        }

        return arrayList;
    }
}
