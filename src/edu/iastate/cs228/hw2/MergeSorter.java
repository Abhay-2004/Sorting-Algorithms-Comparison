package edu.iastate.cs228.hw2;

/**
 * @author Abhay Prasanna Rao
 *
 * This class implements the mergesort algorithm.
 */
public class MergeSorter extends AbstractSorter {

	/**
	 * Constructor that takes an array of points.
	 * It invokes the superclass constructor and sets the algorithm's name.
	 * 
	 * @param pts  Array of points to be sorted.
	 */
	public MergeSorter(Point[] pts) {
		super(pts);
		algorithm = "mergesort";
	}

	/**
	 * Calls the recursive mergeSortRec method to sort the points[] array.
	 */
	@Override
	public void sort() {
		mergeSortRec(points);
	}

	/**
	 * Recursive method that performs the merge sort on an array of points.
	 * 
	 * @param pts  Array of points to be sorted.
	 */
	private void mergeSortRec(Point[] pts) {
		if (pts.length <= 1) {
			return;
		}

		// Split the array in half
		Point[] left = new Point[pts.length / 2];
		Point[] right = new Point[pts.length - left.length];

		System.arraycopy(pts, 0, left, 0, left.length);
		System.arraycopy(pts, left.length, right, 0, right.length);

		// Recursively sort both halves
		mergeSortRec(left);
		mergeSortRec(right);

		// Merge the sorted halves
		merge(left, right, pts);
	}

	/**
	 * Helper method to merge two sorted arrays into a single sorted array.
	 * 
	 * @param left   Left half of the sorted array.
	 * @param right  Right half of the sorted array.
	 * @param pts    Array where the merged result is stored.
	 */
	private void merge(Point[] left, Point[] right, Point[] pts) {
		int i = 0, j = 0, k = 0;

		while (i < left.length && j < right.length) {
			if (pointComparator.compare(left[i], right[j]) <= 0) {
				pts[k] = left[i];
				i++;
			} else {
				pts[k] = right[j];
				j++;
			}
			k++;
		}

		// Copy remaining elements from both halves
		while (i < left.length) {
			pts[k] = left[i];
			i++;
			k++;
		}
		while (j < right.length) {
			pts[k] = right[j];
			j++;
			k++;
		}
	}
}
