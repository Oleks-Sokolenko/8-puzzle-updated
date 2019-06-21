package PQ;

public class Test {

  public static void main(String[] args) {
    String data[] = {"Apple", "Banana", "Grape", "Grapefruit", "Plum", "Raspberry", "Strawberry"};

    /** Creating a Priority Queue of Strings and using their length as the priorities (keys). */
    HeapPriorityQueue<Integer, String> PQStrings = new HeapPriorityQueue<Integer, String>();
    for (int j=0; j < data.length; j++) {
        PQStrings.insert(data[j].length(), data[j]);
    }

    /** Removing elements from the queue according to their priorities (keys). */
    while (PQStrings.size() > 0)
    {
        Entry<Integer, String> nextEntry = PQStrings.removeMin();
        System.out.println(nextEntry.getValue());
    }
  }


}
