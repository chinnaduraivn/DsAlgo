package com.dsalgo.stack;

import java.util.Stack;

import org.junit.Test;

public class StackAlgo {

	@Test
	public void testCalculateExp() {
		String[] exp= {"2","*","3","+","5","/","6","*","3","+","15"};
		System.out.println("testCalculateExp: ");
		double res=calculateExp(exp);
		System.out.println(res);
	}
	public double calculateExp(String[] exp) {
		int i = 0;
		Stack<Double> st1 = new Stack<>();
		Stack<String> st2 = new Stack<>();

		while (i < exp.length) {
			String t = exp[i];
			if (isNumber(t))
				st1.push(toNumber(t));
			else if (t.equals("*") || t.equals("/")) {
				double op1 = st1.pop();
				double op2 = toNumber(exp[i + 1]);
				double res = calculate(op1, op2, t);
				st1.push(res);
				i++;
			} else
				st2.push(t);
			i++;

		}
		double res = calculate(st1, st2);
		return res;
	}

	private double calculate(Stack<Double> st1, Stack<String> st2) {
		double res = 0;
		while (!st2.isEmpty()) {
			double op2 = st1.pop();
			double op1 = st1.pop();
			res = calculate(op1, op2, st2.pop());
			st1.push(res);
		}
		return res;
	}

	private double calculate(double x, double y, String op) {
		switch (op) {
		case "*":
			return x * y;
		case "/":
			return x / y;
		case "+":
			return x + y;
		default:
			return x - y;
		}
	}

	private double toNumber(String s) {
		return Double.parseDouble(s);
	}

	private boolean isNumber(String s) {
		try {
			Double.parseDouble(s);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
