package edu.iastate.cs228.hw2;

/**
 * Represents a 2D point with x and y coordinates.
 * 
 * @author Abhay Prasanna Rao
 */
public class Point implements Comparable<Point>
{
	private int x; 
	private int y;
	
	/**
	 * Flag to determine by which coordinate comparison should be made.
	 * If true, compare using x coordinates. If false, compare using y coordinates.
	 */
	public static boolean xORy; 
	
	public Point() {
		// Default constructor, x and y get default value 0
	}
	
	/**
	 * Constructor with x and y coordinates.
	 * 
	 * @param x  x-coordinate
	 * @param y  y-coordinate
	 */
	public Point(int x, int y) {
		this.x = x;  
		this.y = y;   
	}
	
	/**
	 * Copy constructor.
	 * 
	 * @param p  Point object to be copied
	 */
	public Point(Point p) {
		x = p.getX();
		y = p.getY();
	}
	
	/**
	 * Getter for x-coordinate.
	 * 
	 * @return  x-coordinate of the point
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Getter for y-coordinate.
	 * 
	 * @return  y-coordinate of the point
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Set the value of the static instance variable xORy.
	 * 
	 * @param xORy  Boolean value to set for xORy
	 */
	public static void setXorY(boolean xORy) {
		Point.xORy = xORy;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
    
		Point other = (Point) obj;
		return x == other.x && y == other.y;   
	}
	
	@Override
	public int compareTo(Point q) {
		if (xORy) {
			if (this.x < q.x || (this.x == q.x && this.y < q.y)) return -1;
			if (this.x == q.x && this.y == q.y) return 0;
		} else {
			if (this.y < q.y || (this.y == q.y && this.x < q.x)) return -1;
			if (this.y == q.y && this.x == q.x) return 0;
		}
		return 1;
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
