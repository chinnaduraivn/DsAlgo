package com.dsalgo.string;

// TODO: Auto-generated Javadoc
/**
 * The Class StringUtil.
 */
public class StringUtil {

	/**
	 * Checks if is null or empty.
	 *
	 * @param s
	 *            the s
	 * @return true, if is null or empty
	 */
	public static boolean isNullOrEmpty(String s) {

		return s == null || s.length() == 0;
	}

	/**
	 * Only one bit set.
	 *
	 * @param x
	 *            the x
	 * @return true, if successful
	 */
	public static boolean onlyOneBitSet(int x) {

		return (x & (x - 1)) == 0;
	}

	/**
	 * Gets the character number.
	 *
	 * @param c
	 *            the c
	 * @return the character number
	 */
	public static int getCharacterNumber(char c) {
		int val = Character.getNumericValue(c);
		int a = Character.getNumericValue('a');
		int z = Character.getNumericValue('z');

		if (a <= val && val <= z)
			return val;
		return -1;
	}

}
