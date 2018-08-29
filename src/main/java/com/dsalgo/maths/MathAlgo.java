package com.dsalgo.maths;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class MathAlgo {

	public void makePramid(int n) {
		if (n < 1)
			return;
		int a = 1;
		int d = 2;
		int x = a + ((n - 1) * d);
		int mid = x / 2;
		String temp = getSpace(mid);
		for (int i = 0; i < n; i++) {
			String space = temp.substring(i);
			String result = space;
			int curr = a + (i * d);
			for (int j = 0; j < curr; j++) {
				result += "#";
			}

			System.out.println(result);
		}

	}

	private String getSpace(int n) {
		StringBuilder sbr = new StringBuilder();
		for (int i = 0; i < n; i++)
			sbr.append(" ");
		return sbr.toString();
	}

	public int getMaxSlotCount(double[] in, double[] out) {
		int count = 1;
		int max = 0;
		int outStar = 0;

		for (int i = 0; i < in.length - 1; i++) {
			count++;

			int tempCount = 0;
			while (outStar < out.length && in[i] < out[outStar] && out[outStar] < in[i + 1]) {
				tempCount++;
				outStar++;
			}
			count -= tempCount;
			if (count < 0)
				count = 1;

			if (max < count)
				max = count;
		}

		return max;
	}

	@Test
	public void testGetMaxSlotCount() {
		/*
		 * double[] in = { 1, 5, 6, 7 }; double[] out = { 2, 3, 4, 10 };
		 */
		double[] in = { 1, 9, 6, 7, 5, 4, 15, 20 };
		double[] out = { 12, 13, 14, 10 };

		Arrays.sort(in);
		Arrays.sort(out);
		int max = getMaxSlotCount(in, out);
		System.out.println("testGetMaxSlotCount: " + max);
	}

	int countWay(int n) {

		if (n < 0)
			return 0;
		if (n == 0)
			return 1;
		else
			return countWay(n - 1) + countWay(n - 2) + countWay(n - 3);
	}

	void permutation() {

		List<String> list = new ArrayList<>();
		String s = "ABCD";
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (i == 0)
				list.add(c + "");
			else

				list = addEntries(list, c);
		}

		Collections.sort(list);
		System.out.println(list.toString());

	}

	List<String> addEntries(List<String> list, char c) {
		List<String> result = new ArrayList<>();

		for (String s : list) {
			for (int i = 0; i <= s.length(); i++)
				result.add(s.substring(0, i) + c + s.substring(i));
		}

		return result;
	}

	@Test
	public void testRandomShuffle() {
		int[] A= {1,2,3,4,5,6};
		randomShuffle(A);
		System.out.println("testRandomShuffle");
		System.out.println(Arrays.toString(A));
	}
	public void randomShuffle(int[] A) {
		for (int i = 0; i < A.length; i++) {
			int k = getRandomNumber(0, i);
			int t = A[i];
			A[i] = A[k];
			A[k] = t;
		}

	}

	private int getRandomNumber(int low, int high) {
		return low + (int) Math.random() * (high - low + 1);
	}

}
