package edu.iastate.cs228.hw2;

/**
 * Implements the quicksort algorithm to sort a set of points.
 * 
 * @author Abhay Prasanna Rao
 */
public class QuickSorter extends AbstractSorter {

    /**
     * Constructor that initializes the class with an array of points.
     * 
     * @param pts Array of points.
     */
    public QuickSorter(Point[] pts) {
        super(pts);
        algorithm = "quicksort";
    }

    /**
     * Applies the quicksort algorithm on the array points[] of the AbstractSorter class.
     */
    @Override
    public void sort() {
        quickSortRec(0, points.length - 1);
    }

    /**
     * A recursive helper method to implement quicksort on the subarray of points[] with indices between first and last.
     * 
     * @param first The starting index of the subarray.
     * @param last  The ending index of the subarray.
     */
    private void quickSortRec(int first, int last) {
        if (first < last) {
            int pivotIndex = partition(first, last);
            quickSortRec(first, pivotIndex - 1);
            quickSortRec(pivotIndex + 1, last);
        }
    }

    /**
     * Partitions the subarray of points[] with indices between first and last, and returns the index of the pivot.
     * 
     * @param first The starting index of the subarray.
     * @param last  The ending index of the subarray.
     * @return The index of the pivot.
     */
    private int partition(int first, int last) {
        Point pivot = points[last];
        int i = first - 1;

        for (int j = first; j < last; j++) {
            if (points[j].compareTo(pivot) <= 0) {
                i++;
                swap(i, j);
            }
        }

        swap(i + 1, last);
        return i + 1;
    }
}
