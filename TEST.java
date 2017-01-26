import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class TEST {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(12);
		list.add(45);
		list.add(675);
		list.add(65);

		Collections.sort(list);
		String name = "";
		for (int x : list) {

			name =name  +""+ String.format("%04d", x);
		}
		System.out.println(name);
	}
}