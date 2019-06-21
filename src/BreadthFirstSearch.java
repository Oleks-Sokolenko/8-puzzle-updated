import java.util.*;

public class BreadthFirstSearch extends BestFirstSearch{


	Queue<Board> queue;

	/**
	 * Simple constructor to create a new dequeue
	 */
	public BreadthFirstSearch(){
		queue = new ArrayDeque<>();
	}

	/**
	 * Method to calculate regular priority can possibly be done with just a counter since queue will be adding them
	 * in Breadth-first order anyway
	 * @param b the board to evaluate
	 * @return integer value for the key
	 */
	@Override
	int calculatePriority(Board b){
		queue.add(b);
		return queue.size();
	}

}
