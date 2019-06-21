import PQ.*;


/**
 * Class uses the package of Priority Queues provided by professor without further modifications
 */
public abstract class BestFirstSearch{
	//pq that holds the boards and assigns the keys for future search of the most efficient path
	PriorityQueue<Integer, Board> pq;

	/**
	 * Method that calculates the priority of the specific board
	 * @param b the board to evaluate
	 * @return integer value corresponding to the key of the board
	 */
	abstract int calculatePriority(Board b);

	Board run(Board initialState, long milliseconds){
		long currentTime = System.currentTimeMillis();
		long timeToTerminateAt = currentTime + milliseconds;


		pq = new HeapPriorityQueue<>();
		int p = calculatePriority(initialState);
		pq.insert(p, initialState);
		while(System.currentTimeMillis() < timeToTerminateAt && !pq.isEmpty()){

			Board currentState = pq.removeMin()
			                       .getValue();
			if(currentState.isGoal())
				return currentState; // returns the board if it is a goal
			Iterable<Board> neighbors = currentState.neighbors();
			for(Board neighbor : neighbors){
				neighbor.updatePathToStartState(currentState);
				p = calculatePriority(neighbor);
				pq.insert(p, neighbor);
			}
		}
		//return null in case one of the conditions fail, requires further check to ensure correct execution
		return null;
	}

}

