package com.dsalgo.array;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import org.junit.Test;

public class ArrayAlgo {

	public static class Result {
		int count;
		int max;
	}

	public int[] rotateArray(int[] A) {
		if (A == null || A.length == 0)
			return A;
		int x = A[0];
		for (int i = 1; i < A.length; i++) {
			int t = x;
			x = A[i];
			A[i] = t;
		}
		A[0] = x;
		return A;
	}

	// @Test
	public void TestRoundOff() {
		int[] A = { 9, 6, 5, 1 };
		int n = 11;
		Integer[] T = new Integer[n + 1];
		Arrays.fill(A, Integer.MAX_VALUE);

		int result = canRoundOff(A, n, T);

		System.out.println(result);
		System.out.println(Arrays.toString(T));

	}

	public int canRoundOff(int[] A, int n, Integer[] t) {

		if (n == 0)
			return 0;
		if (t[n] != -1)
			return t[n];

		int ans = Integer.MAX_VALUE;

		for (int i = 0; i < A.length; i++) {
			if (A[i] <= n) {
				int sub_ans = canRoundOff(A, n - A[i], t);

				if (sub_ans != Integer.MAX_VALUE && sub_ans + 1 < ans) {
					ans = sub_ans + 1;
					t[n] = ans;
				}

			}
		}
		return ans;
	}

	// @Test
	public void testRotateArray() {
		int[] A = { 1, 2, 3, 4, 5, 6 };
		A = rotateArray(A);
		System.out.println(Arrays.toString(A));
	}

	public static ArrayAlgo.Result getLIS(int[] A, int i, int n, int x) {

		if (i == n) {
			ArrayAlgo.Result re = new ArrayAlgo.Result();
			if (A[i] > x) {
				re.count = 2;
				re.max = A[i];
			}

			else {
				re.count = 1;
				re.max = x;
			}
			return re;
		}
		ArrayAlgo.Result a = getLIS(A, i + 1, n, x);
		ArrayAlgo.Result b = getLIS(A, i + 1, n, A[i]);

		ArrayAlgo.Result r = null;
		if (a.count > b.count)
			r = a;
		else
			r = b;
		if (A[i] > x && A[i] < r.max)
			r.count = r.count + 1;
		return r;
	}

	@Test
	public void testPrintSubSortIndex() {
		int[] A = { 1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19 };
		System.out.println("printSubSortIndex: ");
		printSubSortIndex(A);
	}

	public void printSubSortIndex(int[] A) {

		int end = getEndOfSort(A);
		if (end == A.length - 1)
			System.out.println("Array is already sorted");
		int start = getStartingOfSort(A);

		int min = getMin(A, end);
		int max = getMax(A, start);

		int ceil = ceilingIndex(A, max, start);
		int floor = floorIndex(A, min, end);
		System.out.println(floor + "  " + ceil);

	}

	private int getEndOfSort(int[] A) {
		for (int i = 1; i < A.length; i++) {
			if (A[i - 1] > A[i])
				return i - 1;
		}
		return A.length - 1;
	}

	private int getStartingOfSort(int[] A) {
		for (int i = A.length - 1; i > 0; i--) {
			if (A[i - 1] > A[i])
				return i - 1;
		}
		return 0;
	}

	private int floorIndex(int[] A, int x, int end) {
		for (int i = 0; i <= end; i++) {
			if (A[x] < A[i])
				return i;
		}
		return end;
	}

	private int ceilingIndex(int[] A, int x, int start) {
		for (int i = start; i < A.length; i++) {
			if (A[x] < A[i])
				return i - 1;
		}
		return A.length - 1;
	}

	private int getMin(int[] A, int s) {
		int min = Integer.MAX_VALUE;
		int index = 0;
		for (int i = s; i < A.length; i++) {
			if (min > A[i]) {
				min = A[i];
				index = i;
			}
		}
		return index;
	}

	private int getMax(int[] A, int end) {
		int max = Integer.MIN_VALUE;
		int index = 0;
		for (int i = 0; i <= end; i++) {
			if (max < A[i]) {
				max = A[i];
				index = i;
			}
		}
		return index;
	}
	
	@Test
	public void testPairWithSum() {
		int[] A= {4,6,7,1,8,9,-2,3};
		int sum=7;
		System.out.println("pairWithSum: ");
		pairWithSum(A, sum);
	}
	
	public void pairWithSum(int[] A,int sum) {
		if(A == null || A.length ==0)
			return;
		Arrays.sort(A);
		int i=0;
		int j=A.length-1;
		
		while(i < j) {
			int k=A[i]+A[j];
			if(k == sum)
				System.out.println(A[i++]+" "+A[j--]);
			else {
				if(k > sum)
					j--;
				else
					i++;
			}
		}
	}

	public static void main(String[] args) {
		int[] A = { 10, 22, 9, 33, 21 };// , 50, 41, 60, 80};

		ArrayAlgo.Result x = getLIS(A, 1, A.length - 1, A[0]);
		System.out.println(x.count);
	}

}
