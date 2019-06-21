import java.util.*;

public class Board{
	//variables are set final to prevent value changes
	private final int[][] mBoard;
	private final int[][] GOAL;

	private ArrayList<Board> mPath;

	private final int BOARD_SIZE; //size is set with respect to the original matrix provided




	/**
	 * Constructor creates a board of size equivalent to the size of the array provided
	 *
	 * @param tiles a matrix representation of the new board to be created
	 * @throws IllegalArgumentException when passed array is not square(n x n) where #columns == #rows
	 */
	public Board(final int[][] tiles){
		if(!validate(tiles)){
			throw new IllegalArgumentException("Not a square(n x n) array!");
		}
		BOARD_SIZE = tiles.length;
		mBoard = new int[BOARD_SIZE][BOARD_SIZE];
		for(int i = 0; i < BOARD_SIZE; i++){
			System.arraycopy(tiles[i], 0, mBoard[i], 0, BOARD_SIZE);
		}
		GOAL = new int[BOARD_SIZE][BOARD_SIZE];
		createGoal();
	}

	/*
		Validate array for being square (n x n)
		@return true if it is square, false otherwise
	 */
	private boolean validate(final int[][] tiles){
		for(int i = 0; i < tiles.length; i++){
			if(tiles.length != tiles[i].length){
				return false;
			}
		}
		return true;
	}

	/**
	 * Method to get the string representation of the board in format
	 * x x x
	 * x x x
	 * x x x
	 *
	 * @return the representation of the board
	 */
	@Override
	public String toString(){
		String board = "";
		for(int i = 0; i < BOARD_SIZE; i++){
			for(int j = 0; j < BOARD_SIZE; j++){
				board += mBoard[i][j];
				if(j < (mBoard[i].length - 1))
					board += " ";

			}
			if(i < mBoard.length - 1)
				board += "\n";
		}



		return board;
	}

	/**
	 * Method to look up the tile at the specified position in range for rows and columns of (0, size-1)
	 *
	 * @param row row of the board
	 * @param col column of the board
	 * @return the value of the tile at the specified position
	 * @throws IllegalArgumentException if row or column is out of bounds
	 */
	public int tileAt(final int row, final int col){
		if(row < 0 || row >= BOARD_SIZE){
			throw new IllegalArgumentException("Row is out of bounds!");
		}
		if(col < 0 || col >= BOARD_SIZE){
			throw new IllegalArgumentException("Column is out of bounds!");
		}
		return mBoard[row][col];
	}

	/**
	 * Method to get the size of the board
	 *
	 * @return size of the side of the square board
	 */
	public int size(){
		return BOARD_SIZE;
	}

	/**
	 * Method to compare current state of the board to final goal state
	 *
	 * @return true if board is in final state, false otherwise
	 */
	public boolean isGoal(){

		Board goal = new Board(GOAL);
		return this.equals(goal);
	}

	/*
		Method to create a goal matrix for future comparison
	 */
	private void createGoal(){
		int count = 1;
		for(int i = 0; i < BOARD_SIZE; i++){
			for(int j = 0; j < BOARD_SIZE; j++){
				GOAL[i][j] = count % (int)Math.pow(BOARD_SIZE, 2);
				count++;
			}
		}
	}

	/**
	 * Get the goal matrix
	 * @return 2d array of goal matrix
	 */
	public int[][] getGOAL(){
		return GOAL;
	}

	/**
	 * Equals method
	 *
	 * @param y object to compare
	 * @return true is y is the same object, or if objects are equal, otherwise false
	 */
	@Override
	public boolean equals(Object y){
		if(!(y instanceof Board)){
			return false;
		}
		Board other = (Board)y;
		if(this == other){
			return true;
		}
		if(other.size() != this.size()){
			return false;
		}
		for(int i = 0; i < BOARD_SIZE; i++){
			for(int j = 0; j < BOARD_SIZE; j++){
				if(this.mBoard[i][j] != other.mBoard[i][j]){
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Find neighboring points
	 * by moving tiles around the empty slot to create all the possible outcomes
	 *
	 * @return Iterable list of neighbors
	 * @throws IllegalStateException when empty tile is not present in the board
	 */
	public Iterable<Board> neighbors(){
		List<Board> neighbors = new ArrayList<>();
		Point emptySlot = getEmpty();

		if(emptySlot == null){
			throw new IllegalStateException("Empty slot is not found! Incorrect board!!!");
		}
		if(emptySlot.x != 0){
			//row--
			Board neighbor = swap(emptySlot, new Point((emptySlot.x - 1), emptySlot.y));
			neighbors.add(neighbor);
		}
		if(emptySlot.x != (BOARD_SIZE - 1)){
			//row++
			Board neighbor = swap(emptySlot, new Point((emptySlot.x + 1), emptySlot.y));
			neighbors.add(neighbor);
		}
		if(emptySlot.y != 0){
			//col--
			Board neighbor = swap(emptySlot, new Point((emptySlot.x), emptySlot.y - 1));
			neighbors.add(neighbor);
		}
		if(emptySlot.y != (BOARD_SIZE - 1)){
			//col++
			Board neighbor = swap(emptySlot, new Point((emptySlot.x), emptySlot.y + 1));
			neighbors.add(neighbor);
		}

		return neighbors;
	}

	/*
		Get empty point from board
		returning custom Point
	 */
	private Point getEmpty(){
		for(int i = 0; i < BOARD_SIZE; i++){
			for(int j = 0; j < BOARD_SIZE; j++){
				if(mBoard[i][j] == 0){
					return new Point(i, j);
				}
			}
		}
		//if point isn't found return null, programme will throw exception in neighbors() method
		return null;
	}

	/*
		Swap method to change the positions of array
	 */
	private Board swap(final Point originalPos, final Point finalPos){
		//create a new board and copy values over to have separate deep copy of final board
		int[][] newBoard = new int[BOARD_SIZE][BOARD_SIZE];
		for(int i = 0; i < BOARD_SIZE; i++){
			System.arraycopy(mBoard[i], 0, newBoard[i], 0, BOARD_SIZE);
		}
		//swap elements in the new array to create a new board
		int temp = newBoard[originalPos.x][originalPos.y];
		newBoard[originalPos.x][originalPos.y] = newBoard[finalPos.x][finalPos.y];
		newBoard[finalPos.x][finalPos.y] = temp;

		return new Board(newBoard);
	}

	/**
	 * Method to update the path to original state
	 * @param prev previous board
	 */
	public void updatePathToStartState(Board prev){
		mPath = new ArrayList<>();
		if(prev == null)
			return;
		ArrayList<Board> prevPath = prev.getPath();
		if(prevPath == null){
			mPath.add(prev);
		}else{
			for(Board path : prevPath){
				mPath.add(path);
			}

		}

		mPath.add(this);
	}

	/**
	 * Method returns a full path from the original state to the final
	 * @return arraylist of states
	 */
	public ArrayList<Board> getPath(){
		return mPath;
	}

}
