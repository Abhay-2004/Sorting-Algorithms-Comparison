package edu.iastate.cs228.hw2;

/**
 *  
 * @author Abhay Prasanna Rao
 *
 */

import java.util.Comparator;
import java.io.FileNotFoundException;
import java.lang.IllegalArgumentException; 
import java.util.InputMismatchException;

/**
 * 
 * This abstract class is extended by SelectionSort, InsertionSort, MergeSort, and QuickSort.
 * It stores the input (later the sorted) sequence. 
 *
 */
public abstract class AbstractSorter
{
	
	protected Point[] points;    // array of points operated on by a sorting algorithm. 
	                             // stores ordered points after a call to sort(). 
	
	protected String algorithm = null; // "selection sort", "insertion sort", "mergesort", or
	                                   // "quicksort". Initialized by a subclass constructor.
		 
	protected Comparator<Point> pointComparator = null;  
	

	

	

	protected AbstractSorter()
	{
		// No implementation needed. Provides a default super constructor to subclasses. 
		// Removable after implementing SelectionSorter, InsertionSorter, MergeSorter, and QuickSorter.
	}
	
	
	/**
	 * This constructor accepts an array of points as input. Copy the points into the array points[]. 
	 * 
	 * @param  pts  input array of points 
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	protected AbstractSorter(Point[] pts) throws IllegalArgumentException
	{
//		First arg check
		if (pts == null) {
			throw new IllegalArgumentException("AbstractSorter argument pts was null.");
		}
		
		// Second argument check
		if (pts.length == 0) {
			throw new IllegalArgumentException("AbstractSorter argument pts had length zero (no points passed)");
		}
		
		// Copy the pts array over to points
		points = new Point[pts.length];
		
		for (int i = 0; i < pts.length; i++) {
			points[i] = pts[i];
		}
	}

		
	/**
	 * 
	 * @param p
	 * @throws IllegalArgumentException  if p == null
	 */
	public void setReferencePoint(Point p) throws IllegalArgumentException 
	{
		if (p == null) {
			throw new IllegalArgumentException("Point p is null in AbstractSorter.setReferencePoint");
		}
	}
	
	

	/**
	 * Generates a comparator on the fly that compares by x-coordinate if order == 0, by y-coordinate
	 * if order == 1. Assigns the comparator to the variable pointComparator.
	 * 
	 * @param order  0   by x-coordinate 
	 *               1   by y-coordinate
	 * 
	 * @throws IllegalArgumentException if order is less than 0 or greater than 1
	 */
	public void setComparator(int order) throws IllegalArgumentException
	{
	    if (order < 0 || order > 1) {
	        throw new IllegalArgumentException("Order must be either 0 or 1 in setComparator.");
	    }

	    if (order == 0) {
	        // Compare by x-coordinate
	        pointComparator = new Comparator<Point>() {
	            @Override
	            public int compare(Point p1, Point p2) {
	                return Double.compare(p1.getX(), p2.getX());
	            }
	        };
	    } else {
	        // Compare by y-coordinate
	        pointComparator = new Comparator<Point>() {
	            @Override
	            public int compare(Point p1, Point p2) {
	                return Double.compare(p1.getY(), p2.getY());
	            }
	        };
	    }
	}


	

	/**
	 * Use the created pointComparator to conduct sorting.  
	 * 
	 * Should be protected. Made public for testing. 
	 */
	public abstract void sort(); 
	
	
	/**
	 * Obtain the point in the array points[] that has median index 
	 * 
	 * @return	median point 
	 */
	public Point getMedian()
	{
		return points[points.length/2]; 
	}
	
	
	/**
	 * Copys the array points[] onto the array pts[]. 
	 * 
	 * @param pts
	 */
	public void getPoints(Point[] pts)
	{
		for (int i = 0; i < points.length; i++) {
			pts[i] = points[i];
		}
	}
	

	/**
	 * Swaps the two elements indexed at i and j respectively in the array points[]. 
	 * 
	 * @param i
	 * @param j
	 */
	protected void swap(int i, int j)
	{
		Point temp = this.points[i];
		this.points[i] = this.points[j];
		this.points[j] = temp;

	}	
}
