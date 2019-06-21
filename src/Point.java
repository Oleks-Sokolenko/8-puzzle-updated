public class Point{

	protected int x;
	protected int y;

	/**
	 * Constructor to assign x and y with values provided
	 * @param x position of the element in the matrix representing row
	 * @param y position of the element in the matrix representing column
	 */
	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}

	public int getY(){
		return y;
	}

	public void setY(int y){
		this.y = y;
	}

	public int getX(){
		return x;
	}

	public void setX(int x){
		this.x = x;
	}
}

