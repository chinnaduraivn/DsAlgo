package com.dsalgo.string;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class StringAlgo.
 */
public class StringAlgo {

	/**
	 * Contains unique characters.
	 *
	 * @param s
	 *            the s
	 * @return true, if successful
	 */
	public boolean containsUniqueCharacters(String s) {
		if (StringUtil.isNullOrEmpty(s))
			return false;

		int bitMap = 0;

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			int index = 1 << c - 'a';
			if ((bitMap & index) > 0)
				return false;
			else
				bitMap = bitMap | index;
		}

		return true;
	}

	/**
	 * Test contains unique characters.
	 */
	@Test
	public void testContainsUniqueCharacters() {

		assertFalse(containsUniqueCharacters(null));
		assertFalse(containsUniqueCharacters(""));
		assertFalse(containsUniqueCharacters("qwertyuioq"));
		assertTrue(containsUniqueCharacters("qwertyuio"));
	}

	/**
	 * Checks if is source permutation of target.
	 *
	 * @param source
	 *            the source
	 * @param target
	 *            the target
	 * @return true, if is source permutation of target
	 */
	public boolean isSourcePermutationOfTarget(String source, String target) {

		if (StringUtil.isNullOrEmpty(source) || StringUtil.isNullOrEmpty(target) || source.length() != target.length())
			return false;
		HashMap<Character, Integer> sourceTracker = new HashMap<>();

		for (char c : source.toCharArray()) {
			if (sourceTracker.containsKey(c))
				sourceTracker.put(c, sourceTracker.get(c) + 1);

			else
				sourceTracker.put(c, 1);
		}

		for (char c : target.toCharArray()) {
			if (!sourceTracker.containsKey(c))
				return false;
			if (sourceTracker.get(c) > 1)
				sourceTracker.put(c, sourceTracker.get(c) - 1);
			else
				sourceTracker.remove(c);

		}

		return true;
	}

	/**
	 * Test is source permutation of target.
	 */
	@Test
	public void testIsSourcePermutationOfTarget() {
		assertFalse(isSourcePermutationOfTarget(null, null));
		assertFalse(isSourcePermutationOfTarget(null, "ABC"));
		assertFalse(isSourcePermutationOfTarget("abc", null));
		assertFalse(isSourcePermutationOfTarget("aabc", "acb"));
		assertFalse(isSourcePermutationOfTarget("aac", "acb"));
		assertTrue(isSourcePermutationOfTarget("qwertyuiop", "poiuytrewq"));
	}

	/**
	 * Checks for palindrom permutation.
	 *
	 * @param s
	 *            the s
	 * @return true, if successful
	 */
	public boolean hasPalindromPermutation(String s) {
		if (StringUtil.isNullOrEmpty(s))
			return false;

		int bitVector = 0;

		for (char c : s.toCharArray()) {

			int val = StringUtil.getCharacterNumber(c);

			if (val < 0)
				continue;

			int index = 1 << (val - 'a');

			if ((bitVector & index) > 0)
				bitVector = bitVector & ~index;
			else
				bitVector |= index;
		}

		return StringUtil.onlyOneBitSet(bitVector);
	}

	/**
	 * Test has palindrom permutation.
	 */
	@Test
	public void testHasPalindromPermutation() {
		assertFalse(hasPalindromPermutation(null));
		assertFalse(hasPalindromPermutation(""));
		assertFalse(hasPalindromPermutation("abcabcda"));
		assertFalse(hasPalindromPermutation("I am IN"));
		assertTrue(hasPalindromPermutation("taco cat"));
		assertTrue(hasPalindromPermutation("I am I am do do"));
	}

	public boolean isSourceOneCharacterAwayFromTarget(String source, String target) {
		if (StringUtil.isNullOrEmpty(source) || StringUtil.isNullOrEmpty(target))
			return false;
		if (Math.abs(source.length() - target.length()) > 1)
			return false;

		String temp = null;
		temp = source.length() > target.length() ? source : target;
		target = target.length() < source.length() ? target : source;
		source = temp;

		int s = 0;
		int t = 0;
		boolean diffFound = false;

		while (s < source.length() && t < target.length()) {

			if (source.charAt(s) == target.charAt(t)) {
				s++;
				t++;
			} else {
				if (diffFound)
					return false;
				diffFound = true;

				if (source.length() == target.length())
					t++;
				s++;

				if (s > source.length() - 1 || t > target.length() - 1)
					return false;
			}

		}
		return true;
	}

	@Test
	public void testIsSourceOneCharacterAwayFromTarget() {
		assertFalse(isSourceOneCharacterAwayFromTarget(null, null));
		assertFalse(isSourceOneCharacterAwayFromTarget(null, "vix"));
		assertFalse(isSourceOneCharacterAwayFromTarget("pun", null));
		assertFalse(isSourceOneCharacterAwayFromTarget("pun", "pnu"));
		assertTrue(isSourceOneCharacterAwayFromTarget("fun", "pun"));
		assertTrue(isSourceOneCharacterAwayFromTarget("king", "wing"));
		assertTrue(isSourceOneCharacterAwayFromTarget("kin", "king"));
	}

	public String compress(String s) {

		if (StringUtil.isNullOrEmpty(s) || s.length() < 3)
			return s;
		StringBuilder builder = new StringBuilder();
		int current = 0;
		int count = 0;

		builder.append(s.charAt(0));
		count++;

		for (int i = 1; i < s.length(); i++) {

			if (s.charAt(current) == s.charAt(i))
				count++;
			else {
				builder.append(count);
				builder.append(s.charAt(i));
				count = 1;
				current = i;
			}

		}

		builder.append(count);

		String result = s.length() > builder.length() ? builder.toString() : s;

		return result;
	}

	@Test
	public void testCompression() {
		assertNull(compress(null));
		assertEquals("aabbcc", compress("aabbcc"));
		assertEquals("a4b2c3", compress("aaaabbccc"));

	}

	public boolean isRotation(String s1, String s2) {
		if (StringUtil.isNullOrEmpty(s1) || StringUtil.isNullOrEmpty(s2))
			return false;
		if (s1.length() != s2.length())
			return false;
		String s = s1 + s1;
		return s.contains(s2);

	}

	@Test
	public void testIsRotation() {

		assertFalse(isRotation(null, null));
		assertFalse(isRotation(null, "waterbottle"));
		assertFalse(isRotation("waterbottle", null));
		assertFalse(isRotation("waterbottle", "erbottlwat"));
		assertTrue(isRotation("waterbottle", "erbottlewat"));

	}

	public static void main(String[] args) {
		System.out.println("Test");
		StringAlgo algo = new StringAlgo();
		algo.isSourceOneCharacterAwayFromTarget("pun", "pnu");
	}
}
