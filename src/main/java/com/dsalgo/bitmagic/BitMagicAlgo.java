package com.dsalgo.bitmagic;

import org.junit.Test;

public class BitMagicAlgo {

	@Test
	public void testAddWithoutPlus() {
		int c= addWithoutPlus(10, 3);
		System.out.println(c);
	}
	public int addWithoutPlus(int a,int b) {
		if(b ==0)
			return a;
		
		int s= a^ b;
		int c=  (a&b)<<1;
		
		return addWithoutPlus(s, c);
	}
}
