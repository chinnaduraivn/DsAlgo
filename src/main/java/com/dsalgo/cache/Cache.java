package com.dsalgo.cache;

import java.util.HashMap;

import org.junit.Test;

public class Cache {

	private Node head = null;
	private Node tail = null;
	int maxSize = 0;
	int currentSize = 0;
	private HashMap<Integer, Node> map = new HashMap<>();

	public Cache() {

	}

	/*
	 * public Cache(int maxSize) { this.maxSize = maxSize; }
	 */

	public void put(int key, String val) {

		if (map.containsKey(key)) {
			Node current = removeNode(key);
			insertFirst(current);
		}
		currentSize++;

		Node temp = new Node();
		temp.key = key;
		temp.val = val;

		if (head == null) {
			head = temp;
			head.prev = null;
			head.next = null;
			tail = head;
		} else {

			if (currentSize > maxSize)
				removeLast();

			insertFirst(temp);
			map.put(key, temp);
		}

	}

	public String get(int key) {
		if (map.containsKey(key)) {
			if (head.key == key)
				return head.val;
			Node t = removeNode(key);

			insertFirst(t);
			return t.val;
		}
		return null;
	}

	private Node removeNode(int key) {

		Node current = map.get(key);
		if (tail == current)
			tail = current.prev;
		current.prev.next = current.next;
		if (current.next != null)
			current.next.prev = current.prev;
		return current;
	}

	private Node insertFirst(Node newHead) {
		newHead.next = head;
		head.prev = newHead;
		head = newHead;

		return head;
	}

	private Node removeLast() {
		Node t = tail;
		tail = t.prev;
		t.prev = null;
		tail.next = null;
		map.remove(t.key);
		currentSize--;
		return t;
	}

	class Node {
		Node prev;
		Node next;
		int key;
		String val;
	}

	@Test
	public void testCache() {
		Cache cache = new Cache();
		cache.maxSize = 3;
		cache.put(1, "One");
		cache.put(2, "Two");
		cache.put(3, "Three");
		cache.put(4, "Four");
		cache.get(2);
		cache.get(4);
		cache.put(5, "Five");
		System.out.println(cache.get(4));
		cache.put(6, "Six");
		System.out.println(cache.get(5));
	}
}
