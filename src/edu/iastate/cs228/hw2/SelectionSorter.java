package edu.iastate.cs228.hw2;

/**
 * Implements the selection sort algorithm to sort a set of points.
 * 
 * @author Abhay Prasanna Rao
 */
public class SelectionSorter extends AbstractSorter {

    /**
     * Constructor that initializes the class with an array of points.
     * 
     * @param pts Array of points.
     */
    public SelectionSorter(Point[] pts) {
        super(pts);
        algorithm = "selection sort";
    }

    /**
     * Applies the selection sort algorithm on the array points[] of the AbstractSorter class.
     */
    @Override
    public void sort() {
        for (int i = 0; i < points.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < points.length; j++) {
                if (points[j].compareTo(points[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                swap(i, minIndex);
            }
        }
    }
}
