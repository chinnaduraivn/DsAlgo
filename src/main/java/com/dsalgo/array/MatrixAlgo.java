package com.dsalgo.array;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class MatrixAlgo {

	public int[][] makeRowColumnZero(int[][] A) {

		if (A == null)
			return A;
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[i].length; j++) {
				if (A[i][j] == 0) {
					setZero(A, i, 0);
					setZero(A, 0, j);
				}

			}
		}

		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[i].length; j++) {
				if (A[i][0] == 0 || A[0][j] == 0) {
					setZero(A, i, j);
				}

			}
		}
		return A;
	}

	private void setZero(int[][] A, int row, int column) {
		if (!isValidIndex(A, row, column))
			return;
		A[row][column] = 0;

	}

	private boolean isValidIndex(int[][] A, int row, int column) {
		if (A == null || row < 0 || column < 0 || row > A.length || column > A[0].length)
			return false;

		return true;
	}

	// @Test
	public void testRowColumnZero() {
		int[][] A = { { 1, 2, 3, 4 }, { 5, 0, 7, 8 }, { 9, 10, 11, 0 } };
		int[][] B = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
		String a = "[[1, 0, 3, 0], [0, 0, 0, 0], [0, 0, 0, 0]]";
		String b = Arrays.deepToString(B);

		makeRowColumnZero(A);
		assertEquals(Arrays.deepToString(A), a);

		makeRowColumnZero(B);
		assertEquals(Arrays.deepToString(B), b);

	}

	public int[][] rotateMatrixWithExtraSpace(int[][] A) {
		if (A == null || A.length == 0)
			return A;
		int r = A.length;
		int c = A[0].length;
		int[][] B = new int[c][r];

		for (int i = 0; i < r; i++)
			for (int j = 0; j < c; j++) {
				B[r - j][i] = A[i][j];
			}
		return B;
	}

	@Test
	public void testRotateMatrixWithExtraSpace() {
		int[][] A = { { 1, 2, 3, 4 }, { 5, 0, 7, 8 }, { 9, 10, 11, 0 } };
		int[][] B = rotateMatrixWithExtraSpace(A);
		System.out.println("testRotateMatrixWithExtraSpace  " + Arrays.deepToString(B));

	}

	public int[][] rotateMatrix(int[][] A) {
		if (A == null || A.length == 0)
			return A;
		int row = A.length;
		int col = A[0].length;
		int a;
		int b;
		int c;
		int d;
		int layer = 0;

		while (layer < row / 2) {
			a = A[layer][layer];

			for (int i = layer + 1; i < col - layer; i++) {
				int t = a;
				a = A[layer][i];
				A[layer][i] = t;
			}

			b = a;

			for (int i = layer + 1; i < row - layer; i++) {
				int t = b;
				b = A[i][col - layer - 1];
				A[i][col - layer - 1] = t;
			}

			c = b;
			for (int i = col - layer - 2; i > layer; i--) {
				int t = c;
				c = A[row - layer - 1][i];
				A[row - layer - 1][i] = t;
			}
			d = c;
			for (int i = row - layer - 1; i > layer; i--) {
				int t = d;
				d = A[i][layer];
				A[i][layer] = t;
			}

			A[layer][layer] = d;
			layer++;

		}

		return A;
	}

	@Test
	public void testRotateMatrix() {
		int[][] A = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
		int[][] B = rotateMatrix(A);

		System.out.println("testRotateMatrix  " + Arrays.deepToString(B));
	}

	public void spiralTraversal(int[][] A) {
		if (A == null || A.length == 0)
			return;
		boolean isLeftToRight = true;
		int r = A.length;
		int c = A[0].length;
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (isLeftToRight)
					System.out.print(A[i][j] + " ");
				else
					System.out.print(A[i][c - j - 1] + " ");

			}
			System.out.println();
			isLeftToRight = !isLeftToRight;
		}
	}

	@Test
	public void testSpiralTraversal() {
		System.out.println("testSpiralTraversal:");
		int[][] A = getMatrix();
		spiralTraversal(A);
	}

	public void spiralTraversalVertical(int[][] A) {
		if (A == null || A.length == 0)
			return;
		boolean isTopToBottom = true;
		int r = A.length;
		int c = A[0].length;
		for (int i = 0; i < c; i++) {
			for (int j = 0; j < r; j++) {

				if (isTopToBottom)
					System.out.print(A[j][i] + " ");
				else
					System.out.print(A[r - j - 1][i] + " ");

			}
			System.out.println();
			isTopToBottom = !isTopToBottom;
		}
	}

	@Test
	public void testSpiralTraversalVertical() {
		System.out.println("testSpiralTraversalVertical:");
		int[][] A = getMatrix();
		spiralTraversalVertical(A);
	}

	public int[][] rotateBy90(int[][] A) {

		if (A == null || A.length < 1 || (A.length != A[0].length))
			return A;
		int n = A.length - 1;
		int layer = 0;

		while (layer < (A.length) / 2) {
			for (int i = layer; i < n - layer; i++) {
				int a = 0;
				int b = 0;

				a = A[n - i][layer];
				A[n - i][layer] = A[layer][i];
				b = A[n - layer][n - i];
				A[n - layer][n - i] = a;

				a = A[i][n - layer];
				A[i][n - layer] = b;

				A[layer][i] = a;

			}
			layer++;
		}
		return A;

	}

	@Test
	public void testRotateBy90() {
		int[][] A = getSqureMatrix();
		A = rotateBy90(A);
		System.out.println("testRotateBy90  " + Arrays.deepToString(A));
	}
	
	@Test
	public void testFindMaximumFlat() {
		int[][] land = { { 1, 2, 3, 4 }, { 1, 2, 4, 4 }, { 9, 10, 11, 4 }, { 3, 3, 4, 4 } };
		System.out.println("testFindMaximumFlat");
		findMaximumFlat(land);
	}
	
	public void findMaximumFlat(int[][] land) {
		if(land ==null || land.length ==0)
			return;
		int i=land.length;
		int j=land[0].length;
		boolean[][] visited=new boolean[i][j];
		for (int k = 0; k < i; k++) {
			for (int l = 0; l < j; l++) {
				if(!visited[k][l]) {
					int s=getFlatSurfaceSize(land, visited, k, l, land[k][l]);
					System.out.println(land[k][l]+"  "+s);
				}
				
			}
		}
	}
	
	private int getFlatSurfaceSize(int[][] land,boolean[][] visited,int i,int j,int z) {
		if(i < 0 || j < 0 || i >=land.length || j >= land[0].length || visited[i][j] || land[i][j] != z)
			return 0;
		
		int size =1;
		visited[i][j]=true;
		for (int x = -1; x <= 1; x++) {
			for (int y = -1; y <= 1; y++) {
				size+=getFlatSurfaceSize(land, visited, i+x, j+y,z);
			}
		}
		return size;
	}
	@Test
	public void testCombinationOfLength() {
		List<Integer> result=new ArrayList<>();
		combinationOfLength(3, 10, 0, 2, 0, result);
		System.out.println("testCombinationOfLength : ");
		System.out.println(result);
	}
	public void combinationOfLength(int small,int large,int length,int k,int n,List<Integer> result){
		
		if(k == n) {
			result.add(length);
			return;
		}
		combinationOfLength(small, large, length+small, k, n+1, result);
		combinationOfLength(small, large, length+large, k, n+1, result);
	}

	private int[][] getMatrix() {
		int[][] A = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
		return A;
	}

	private int[][] getSqureMatrix() {
		int[][] A = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
		return A;
	}

	public static void main(String[] args) {
		MatrixAlgo matrixAlgo = new MatrixAlgo();
		// matrixAlgo.testRowColumnZero();

	}

}
