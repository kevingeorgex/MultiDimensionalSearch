import java.util.HashMap;
import java.util.Map;

public class Sample {
	public static Map<String, Map<String, Integer>> myMap;

	public static void main(String[] args) {
		// Key : A B
		// Key :X Y Values: 1 0
		myMap = new HashMap<String, Map<String, Integer>>();

		insertIntoMap("A", "X", 1);

	}

	private static void insertIntoMap(String key1, String key2, int value) {

		if (myMap.get(key1) != null) {
			Map<String, Integer> mapHere = myMap.get(key1);
			if (mapHere.get(key2) != null) {
				int x = mapHere.get(key2)+1;
				mapHere.put(key2,x);				
			} else {
				Map something = new HashMap<String, Integer>();
				something.put(key2, value);
				myMap.put(key1, something);
			}

		} else {
			Map something = new HashMap<String, Integer>();
			something.put(key2, value);
			myMap.put(key1, something);
		}

	}
}
