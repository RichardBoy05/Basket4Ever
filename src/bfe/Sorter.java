package bfe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sorter {

	public static int[] findIndexes(List<Integer> stats) {

		int indexes[] = new int[10];
		List<Integer> sortedlist = new ArrayList<>();
		List<Integer> statsCopy = new ArrayList<>();
		sortedlist.addAll(stats);
		statsCopy.addAll(stats);
		Collections.sort(sortedlist);
		sortedlist = sortedlist.subList(sortedlist.size() - 10, sortedlist.size());

		int counter = 0;

		for (int value : sortedlist) {
			
			int place = statsCopy.indexOf(value);
			
			indexes[counter] = place;
			statsCopy.remove(place);
			statsCopy.add(place, -1);
			
			counter++;
		}

		return indexes;
	}

}
