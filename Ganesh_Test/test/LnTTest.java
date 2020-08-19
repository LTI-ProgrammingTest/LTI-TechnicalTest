package lnt.coding.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LnTTest {

	public static void main(String[] args) {
		
		
		System.out.println(digitRepeatsNTimesOrNot());
	}

	private static boolean digitRepeatsNTimesOrNot() {
		Scanner sc = new Scanner(System.in);
		long wholeNumber = sc.nextLong();
		int digit = sc.nextInt();
		int occurances = sc.nextInt();

		Map<Integer, Integer> map = new HashMap<Integer, Integer>();

		while (wholeNumber > 0) {
			int rem = (int) (wholeNumber % 10);
			if (map.containsKey(rem)) {
				map.put(rem, map.get(rem) + 1);
			} else {
				map.put(rem, 1);
			}
			wholeNumber /= 10;
		}
		if (map.get(digit) == occurances) {
			return true;
		} else {
			return false;
		}
	}

}
