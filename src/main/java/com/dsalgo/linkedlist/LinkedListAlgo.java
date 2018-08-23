package com.dsalgo.linkedlist;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LinkedListAlgo {

	public Node removeDuplicates(Node head) {

		if (head == null)
			return head;
		Node p1 = head;
		Node p2 = head;

		while (p1 != null) {

			p2 = p1;

			while (p2 != null && p2.next != null) {
				if (p1.data == p2.next.data)
					p2.next = p2.next.next;

				p2 = p2.next;

			}
			p1 = p1.next;
		}
		return head;
	}

	@Test
	public void testRemoveDuplicates() {

		Node head = getSinglyLinkedList();
		head = removeDuplicates(head);
		printLinkedList(head, "testRemoveDuplicates");

	}

	public Node getKthToLast(Node head, int k) {
		if (head == null)
			return head;
		int x = 0;
		Node p1 = head;
		Node p2 = head;

		while (p2 != null && x < k) {
			x++;
			p2 = p2.next;
		}

		while (p2 != null && p2.next != null) {
			p1 = p1.next;
			p2 = p2.next;

		}
		return p1;
	}

	@Test
	public void testGetKthToLast() {
		Node head = getSinglyLinkedList();
		Node n = getKthToLast(head, 3);
		System.out.println("testGetKthToLast " + n.data);
	}

	public void deleteMiddleNode(Node head) {

		if (head == null)
			return;
		int i = 0;
		int j = 0;
		Node n = head;
		Node m = head;

		while (n != null && n.next != null) {

			if (Math.abs(j / 2) > i) {
				i++;
				m = m.next;
			}
			j++;
			n = n.next;
		}
		m.next = m.next.next;
	}

	@Test
	public void testDeleteMiddleNode() {
		Node head = getSinglyLinkedList();
		deleteMiddleNode(head);
		printLinkedList(head, "testDeleteMiddleNode");
	}

	public Node partition(Node head, int pivot) {
		if (head == null)
			return head;
		Node n = head;
		Node e = head;
		while (n != null) {
			Node next = n.next;

			if (n.data < pivot) {
				n.next = head;
				head = n;
			} else {
				e.next = n;
				e = n;
			}
			n = next;
		}
		e.next = null;
		return head;
	}

	@Test
	public void testPartition() {
		Node head = getSinglyLinkedList();
		head = partition(head, 5);
		printLinkedList(head, "testPartition");
	}

	public Node sumOfReverse(Node head1, Node head2) {

		if (head1 == null && head2 == null)
			return head1;
		if (head1 == null)
			return head2;
		if (head2 == null)
			return head1;

		Node sum = null;
		Node newHead = null;
		int carry = 0;
		while (head1 != null || head2 != null) {
			carry = (head1.data + head2.data + carry) / 10;
			int s = (head1.data + head2.data + carry) % 10;
			if (sum == null) {
				sum = new Node(s);
				newHead = sum;
			} else {
				sum.next = new Node(s);
				sum = sum.next;
			}
			head1 = head1.next;
			head2 = head2.next;
		}

		if (head1 == null && head2 == null && carry > 0) {
			sum.next = new Node(carry);
		}
		while (head1 != null) {
			carry = (head1.data + carry) / 10;
			int s = (head1.data + carry) % 10;
			sum.next = new Node(s);
			head1 = head1.next;
		}

		while (head2 != null) {
			carry = (head2.data + carry) / 10;
			int s = (head2.data + carry) % 10;
			sum.next = new Node(s);
			head2 = head2.next;
		}

		return newHead;
	}

	@Test
	public void testSumOfReverse() {
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n4 = new Node(4);
		Node n5 = new Node(5);
		Node n6 = new Node(6);

		Node head1 = n1;
		n1.next = n2;
		n2.next = n3;

		Node head2 = n4;
		n4.next = n5;
		n5.next = n6;

		Node head = sumOfReverse(head1, head2);
		printLinkedList(head, "testSumOfReverse");
	}

	public Node reverse(Node head) {
		if (head == null)
			return head;
		Node n = head;
		Node next = null;
		while (n != null) {
			Node temp = n.next;
			n.next = next;
			next = n;
			n = temp;
		}
		return next;
	}

	@Test
	public void testReverse() {
		Node head = getSinglyLinkedList();
		head = reverse(head);
		printLinkedList(head, "testReverse");
	}

	public Node sum(Node h1, Node h2) {
		Node newHead = null;
		if (h1 == null && h2 == null)
			return h1;
		int len1 = length(h1);
		int len2 = length(h2);

		if (len1 < len2)
			h1 = padZero(h1, len2 - len1);
		if (len1 > len2)
			h2 = padZero(h2, len1 - len2);

		SumNode sum = sumHelper(h1, h2);

		if (sum.getCarry() == 0)
			return sum.getNode();
		else
			newHead = addBefore(sum.getNode(), sum.getCarry());

		return newHead;
	}

	@Test
	public void testSum() {

		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n4 = new Node(4);
		Node n5 = new Node(5);
		Node n6 = new Node(6);
		Node n7 = new Node(7);
		Node n8 = new Node(8);
		Node n9 = new Node(9);

		n1.next = n2;
		n2.next = n3;

		n4.next = n5;
		n5.next = n6;
		n6.next = n7;
		n7.next = n8;

		Node head = sum(n1, n4);
		printLinkedList(head, "testSum");
	}

	public PalyResult isPalindrome(Node head, int size, int count) {
		if (head == null || size < 1)
			return null;
		if (Math.ceil(size / 2) == count) {
			return new PalyResult(head.next, true);
		}
		PalyResult palyResult = isPalindrome(head.next, size, count + 1);
		Node n = palyResult.nextNode;
		boolean pal = palyResult.isPaly && (head.data == n.data);

		return new PalyResult(n.next, pal);

	}

	@Test
	public void testIsPalindrome() {
		Node head = getSinglyLinkedList();
		int size = length(head);
		PalyResult palyResult = isPalindrome(head, size, 0);
		System.out.println("testIsPalindrome " + palyResult.isPaly);
	}

	public Node getIntersectingNode(Node h1, Node h2) {

		if (h1 == null || h2 == null)
			return null;

		Node longest = h1;
		Node shortest = h2;

		TailNode t1 = getTailNodeWithLenght(h1);
		TailNode t2 = getTailNodeWithLenght(h2);

		if (t1.tailNode != t2.tailNode)
			return null;

		if (t1.length != t2.length) {
			int x = Math.abs(t1.length - t2.length);
			if (t1.length < t2.length) {
				longest = h2;
				shortest = h1;
			}

			for (int i = 0; i < x; i++)
				longest = longest.next;
		}

		while (longest != shortest) {
			longest = longest.next;
			shortest = shortest.next;
		}
		return longest;
	}

	private TailNode getTailNodeWithLenght(Node n) {
		TailNode tailNode = null;
		if (n == null)
			return tailNode;
		int len = 1;
		while (n.next != null) {
			len++;
			n = n.next;
		}
		return new TailNode(n, len);
	}

	@Test
	public void testGetIntersectingNode() {

		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n4 = new Node(4);
		Node n5 = new Node(5);
		Node n6 = new Node(4);
		Node n7 = new Node(3);
		Node n8 = new Node(2);
		Node n9 = new Node(1);

		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;

		n6.next = n7;
		n7.next = n8;
		n8.next = n9;
		n9.next = n4;

		Node h1 = n1;
		Node h2 = n6;

		Node interNode = getIntersectingNode(h1, h2);
		System.out.println(interNode.data + " testGetIntersectingNode");
		assertTrue(interNode.data == n4.data);
	}

	public Node getLoopstartNode(Node h) {
		Node loopStart = null;

		if (h == null)
			return loopStart;
		Node slow = h.next;
		Node fast = h.next.next;

		while (slow != fast) {
			if (slow == null || fast == null)
				break;
			slow = slow.next;
			fast = fast.next.next;
		}

		if (slow == null || fast == null)
			return loopStart;

		slow = h;

		while (slow.next != fast.next) {
			slow = slow.next;
			fast = fast.next;
		}

		return slow.next;
	}

	@Test
	public void testGetLoopstartNode() {
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n4 = new Node(4);
		Node n5 = new Node(5);
		Node n6 = new Node(6);
		Node n7 = new Node(7);
		Node n8 = new Node(8);

		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;
		n6.next = n7;
		n7.next = n8;
		n8.next = n5;
		Node n = getLoopstartNode(n1);
		System.out.println("testGetLoopstartNode:");
		System.out.println(n.data);
		assertTrue(n.data == n5.data);

	}

	public class TailNode {
		private Node tailNode;
		private int length;

		public TailNode(Node t, int l) {
			this.tailNode = t;
			this.length = l;
		}

		public Node getTailNode() {
			return tailNode;
		}

		public int getLength() {
			return length;
		}

	}

	public class PalyResult {
		private Node nextNode;
		private boolean isPaly;

		public PalyResult(Node n, boolean isPaly) {
			this.nextNode = n;
			this.isPaly = isPaly;
		}

		public Node getNextNode() {
			return nextNode;
		}

		public boolean isPaly() {
			return isPaly;
		}

	}

	private Node addBefore(Node h, int data) {
		if (h == null)
			return h;
		Node head = new Node(data);
		head.next = h;
		return head;
	}

	private SumNode sumHelper(Node h1, Node h2) {
		if (h1 == null && h2 == null)
			return new SumNode(null, 0);
		SumNode temp = sumHelper(h1.next, h2.next);
		Node n = new Node((h1.data + h2.data + temp.getCarry()) % 10);
		int c = (h1.data + h2.data + temp.getCarry()) / 10;
		n.next = temp.getNode();

		return new SumNode(n, c);
	}

	private Node padZero(Node head, int count) {
		if (head == null)
			return head;
		Node newHead = head;
		for (int i = 0; i < count; i++) {
			Node n = new Node(0);
			n.next = newHead;
			newHead = n;
		}
		return newHead;
	}

	private int length(Node h) {
		int length = 0;
		if (h == null)
			return length;
		while (h != null) {
			length++;
			h = h.next;
		}
		return length;
	}

	public class SumNode {
		private Node n;
		private int c;

		public SumNode(Node n, int c) {
			this.n = n;
			this.c = c;
		}

		public Node getNode() {
			return n;
		}

		public int getCarry() {
			return c;
		}
	}

	private Node getSinglyLinkedList() {
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n4 = new Node(4);
		Node n5 = new Node(5);
		Node n6 = new Node(4);
		Node n7 = new Node(3);
		Node n8 = new Node(2);
		Node n9 = new Node(1);

		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;
		n6.next = n7;
		n7.next = n8;
		n8.next = n9;

		return n1;
	}

	private void printLinkedList(Node head, String methodName) {
		System.out.println(methodName + ":");
		while (head != null) {
			System.out.print(head.data + " ");
			head = head.next;
		}
		System.out.println();
	}

	private class Node {
		int data;
		Node next = null;

		public Node(int data) {
			this.data = data;
		}
	}
}
