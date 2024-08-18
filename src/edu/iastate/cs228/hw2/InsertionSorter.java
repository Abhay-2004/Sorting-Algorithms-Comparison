package edu.iastate.cs228.hw2;

/**
 * This class extends AbstractSorter and provides an implementation 
 * for the insertion sort algorithm to sort an array of points.
 * 
 * @author Abhay Prasanna Rao
 */
public class InsertionSorter extends AbstractSorter 
{
    /**
     * Constructor that takes an array of points.
     * It invokes the superclass constructor to initialize the points array,
     * and sets the algorithm name to "insertion sort".
     * 
     * @param pts  Array of points to be sorted
     */
	public InsertionSorter(Point[] pts) 
	{
		super(pts);  // Calling the superclass constructor to set the points array
		algorithm = "insertion sort";  // Setting the algorithm name
	}	

    /**
     * Implementation of the insertion sort algorithm to sort the points array.
     * The sorting is based on the comparator (pointComparator) 
     * defined in the parent class AbstractSorter.
     */
	@Override 
	public void sort()
	{
		int n = points.length;  
        for (int i = 1; i < n; ++i) {  
            Point key = points[i];  
            int j = i - 1;  
  
            // Move elements of points[0..i-1] that are greater than key
            // to one position ahead of their current position
            while (j >= 0 && pointComparator.compare(points[j], key) > 0) {  
                points[j + 1] = points[j];  
                j = j - 1;  
            }  
            points[j + 1] = key;  
        }
	}
}
