package edu.iastate.cs228.hw2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A class to sort points based on their x and y coordinates using various sorting algorithms.
 * It can either accept an array of points or read from a file.
 * 
 * @author Abhay Prasanna Rao
 */
public class PointScanner {

    /** Array of points to be sorted. */
    private Point[] points;
    
    /** Median point after sorting. */
    private Point medianCoordinatePoint;
    
    /** The sorting algorithm to be used. */
    private Algorithm sortingAlgorithm;
    
    /** Time taken by the sorting algorithm in nanoseconds. */
    protected long scanTime;

    /**
     * Constructs a PointScanner with a given array of points and a sorting algorithm.
     * 
     * @param pts  Array of points to be sorted.
     * @param algo The sorting algorithm to be used.
     * @throws IllegalArgumentException If the points array is null or has zero length.
     */
    public PointScanner(Point[] pts, Algorithm algo) throws IllegalArgumentException {
        if (pts == null || pts.length == 0) {
            throw new IllegalArgumentException("Points array is either null or of length zero.");
        }
        this.points = pts.clone();
        this.sortingAlgorithm = algo;
    }

    /**
     * Constructs a PointScanner by reading points from a given file and using a specified sorting algorithm.
     * 
     * @param inputFileName Name of the file from which points are to be read.
     * @param algo          The sorting algorithm to be used.
     * @throws FileNotFoundException     If the file does not exist.
     * @throws InputMismatchException    If the file contains an odd number of integers.
     */
    protected PointScanner(String inputFileName, Algorithm algo) throws FileNotFoundException, InputMismatchException {
        this.sortingAlgorithm = algo;

        Scanner scanner = new Scanner(new File(inputFileName));
        ArrayList<Integer> values = new ArrayList<>();

        while (scanner.hasNextInt()) {
            values.add(scanner.nextInt());
        }
        scanner.close();

        if (values.size() % 2 != 0) {
            throw new InputMismatchException("File contains an odd number of integers.");
        }

        int numPoints = values.size() / 2;
        points = new Point[numPoints];

        for (int i = 0; i < numPoints; i++) {
            int x = values.get(2 * i);
            int y = values.get(2 * i + 1);
            points[i] = new Point(x, y);
        }
    }

    /**
     * Sorts the array of points using the specified sorting algorithm, first by x-coordinate and then by y-coordinate.
     */
    public void scan() {
        AbstractSorter sorter;
        switch (sortingAlgorithm) {
            case SelectionSort:
                sorter = new SelectionSorter(points);
                break;
            case InsertionSort:
                sorter = new InsertionSorter(points);
                break;
            case MergeSort:
                sorter = new MergeSorter(points);
                break;
            default:
                sorter = new QuickSorter(points);
                break;
        }

        // Sort by x-coordinate
        sorter.setComparator(0);
        long startTime = System.nanoTime();
        sorter.sort();

        // Sort by y-coordinate
        sorter.setComparator(1);
        sorter.sort();

        long endTime = System.nanoTime();
        scanTime = endTime - startTime;

        // Determine the median coordinate point
        medianCoordinatePoint = sorter.getMedian();
    }

    /**
     * Returns a string containing stats about the sorting, including the sorting algorithm name, number of points, and the time taken.
     * 
     * @return A formatted string containing the sorting stats.
     */
    public String stats() {
        return String.format("%-15s %10d %15d", sortingAlgorithm.name(), points.length, scanTime);
    }

    @Override
    public String toString() {
        return "MCP: " + medianCoordinatePoint.toString();
    }

    /**
     * Writes the median coordinate point (MCP) to a file. The file's name is based on the sorting algorithm used.
     * 
     * @throws FileNotFoundException If there's an issue creating or writing to the file.
     */
    public void writeMCPToFile() throws FileNotFoundException {
        String filename = "MCP_" + sortingAlgorithm.name() + ".txt";
        try (PrintWriter writer = new PrintWriter(filename)) {
            writer.println(toString());
        }
    }
}
