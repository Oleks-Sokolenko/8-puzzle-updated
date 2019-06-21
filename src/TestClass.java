import java.util.ArrayList;

public class TestClass{
	public static void main(String[] args){

		//original arrays given in the assignment
		int[][] testArr0 = {{1,2,3},{4,0,5},{7,8,6}};
		int[][] testArr1 = {{0,1,2},{4,5,3},{7,8,6}};
		int[][] testArr2 = {{0,8,6},{2,7,5},{1,3,4}};

		//create boards for test arrays
		Board testBoard0 = new Board(testArr0);
		Board testBoard1 = new Board(testArr1);
		Board testBoard2 = new Board(testArr2);

		//create searches
		BestFirstSearch BFS = new BreadthFirstSearch();
		BestFirstSearch HS = new HeuristicSearch();

		//run BFS searches
		Board BFStest0 = BFS.run(testBoard0,2000);
		Board BFStest1 = BFS.run(testBoard1,2000);
		Board BFStest2 = BFS.run(testBoard2,2000);

		//run HS searches
		Board HStest0 = HS.run(testBoard0, 2000);
		Board HStest1 = HS.run(testBoard1, 2000);
		Board HStest2 = HS.run(testBoard2, 2000);




		if(BFStest0 == null){
			System.out.println("===============================");
			System.out.println("null");
			System.out.println("===============================");
		}else
			output(BFStest0);


		if(BFStest1 == null){
			System.out.println("===============================");
			System.out.println("null");
			System.out.println("===============================");
		}else
			output(BFStest1);


		if(BFStest2 == null){
			System.out.println("===============================");
			System.out.println("null");
			System.out.println("===============================");
		}else
			output(BFStest2);


		if(HStest0 == null){
			System.out.println("===============================");
			System.out.println("null");
			System.out.println("===============================");
		}else
			output(HStest0);

		if(HStest1 == null){
			System.out.println("===============================");
			System.out.println("null");
			System.out.println("===============================");
		}else
			output(HStest1);

		if(HStest2 == null){
			System.out.println("===============================");
			System.out.println("null");
			System.out.println("===============================");
		}else
			output(HStest2);

	}

	/*
		Method to output the path from original state to final
	 */
	private static void output(Board b){
		ArrayList<Board> list = b.getPath();
		System.out.println("===============================");
		list.stream().map(Board::toString).forEach(System.out::println);
		System.out.println("===============================");
	}
}
