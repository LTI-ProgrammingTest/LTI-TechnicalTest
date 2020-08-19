package lnt.coding.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Whole_Number {


	public static void main(String[] args) {


		System.out.println(wholeNumberTrueFalse());
	}

	private static boolean wholeNumberTrueFalse() {
		Scanner sc = new Scanner(System.in);
		long num = sc.nextLong();
		int digit = sc.nextInt();
		int occurances = sc.nextInt();

		Map<Integer, Integer> map = new HashMap<Integer, Integer>();

		while (num > 0) {
			int rem = (int) (num % 10);
			if (map.containsKey(rem)) {
				map.put(rem, map.get(rem) + 1);
			} else {
				map.put(rem, 1);
			}
			num /= 10;
		}
		if (map.get(digit) == occurances) {
			return true;
		} else {
			return false;
		}
	}

}
