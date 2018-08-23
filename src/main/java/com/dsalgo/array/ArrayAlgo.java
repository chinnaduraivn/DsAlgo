package com.dsalgo.array;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import org.junit.Test;

public class ArrayAlgo {
	
	public static class Result{
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
	
	@Test
	public void TestRoundOff() {
		int[] A = { 9,6,5,1};
		int n = 11;
		Integer[] T =new Integer[n+1];
		Arrays.fill(A, Integer.MAX_VALUE);
		
		
		int result = canRoundOff(A,n,T);
		
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
	//@Test
	public void testRotateArray() {
		int[] A= {1,2,3,4,5,6};
		A=rotateArray(A);
		System.out.println(Arrays.toString(A));
	}

	
	
	public static ArrayAlgo.Result  getLIS(int[] A,int i,int n,int x) {
		
		if(i == n) {
			ArrayAlgo.Result re=new ArrayAlgo.Result();
			if(A[i]>x) {
				re.count=2;
				re.max= A[i];
			}
				
			else{
				re.count=1;
				re.max= x;
			}
			return re;
		}
		ArrayAlgo.Result a=getLIS(A, i+1, n, x);
		ArrayAlgo.Result b=getLIS(A, i+1, n, A[i]);
		
		ArrayAlgo.Result r=null;
		if(a.count>b.count)
				r=a;
		else
				r=b;
		if(A[i]>x && A[i]<r.max)
			r.count=r.count+1;
		return r;
	}

	public static void main(String[] args) {
		int[] A ={10, 22, 9, 33, 21};//, 50, 41, 60, 80};
		
		
		ArrayAlgo.Result x=getLIS(A, 1, A.length-1, A[0]);
		System.out.println(x.count);
	}

}
