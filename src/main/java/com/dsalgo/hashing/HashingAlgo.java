package com.dsalgo.hashing;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

public class HashingAlgo {
	
	@Test
	public void testWordDistance() {
		String[] para= {"I","am","coding","for","joy","I","enjoy"};
		int d =wordDistance(para, "I", "for");
		System.out.println("testWordDistance "+d);
	}

	public int wordDistance(String[] para, String s1, String s2) {

		HashMap<String, ArrayList<Integer>> map = new HashMap<>();
		int i = 0;
		ArrayList<Integer> list = null;
		for (String w : para) {

			if (map.containsKey(w)) {
				list = map.get(w);
				list.add(i);
				map.put(w, list);
			} else {
				list = new ArrayList<>();
				list.add(i);
				map.put(w, list);
			}
			i++;
		}

		System.out.println(map);
		if (!map.containsKey(s1) || !map.containsKey(s2))
			return Integer.MAX_VALUE;
		int d = findMinDiff(map.get(s1), map.get(s2));

		return d;
	}

	private int findMinDiff(ArrayList<Integer> r1, ArrayList<Integer> r2) {
		if (r1 == null || r2 == null)
			return Integer.MAX_VALUE;
		int i = 0;
		int j = 0;
		int t = 0;
		int min = Integer.MAX_VALUE;

		while (min > 1 && i < r1.size() && j < r2.size()) {
			t = Math.abs(r1.get(i) - r2.get(j));
			if (t < min)
				min = t;
			if (r1.get(i) > r2.get(j))
				j++;
			else
				i++;
		}

		return min;
	}

}
