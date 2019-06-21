public class HeuristicSearch extends BestFirstSearch{

	/**
	 * Calculate priority on misplaced tiles, more misplaced tiles higher the counter, higher the counter lower the priority
	 * @param b the board to evaluate
	 * @return integer value for priority queue
	 */
	@Override
	int calculatePriority(Board b){
		int [][] goal = b.getGOAL(); //gets the goal array
		int counter = 0; //original priority with zero tiles misplaced
		for(int i = 0; i < b.size(); i++){
			for(int j = 0; j < b.size(); j++){
				//compare the current board to the goal array, if they are not equal, increment the counter
				if(goal[i][j] != b.tileAt(i,j)){
					counter++;
				}
			}
		}
		return counter;
	}

}
