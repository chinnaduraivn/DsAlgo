package com.dsalgo.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class TreeAlgo {

	public void preOrder(Node n) {
		if (n == null)
			return;
		System.out.print(n.data + " ");
		preOrder(n.left);
		preOrder(n.right);
	}

	public void inOrder(Node n) {
		if (n == null)
			return;
		inOrder(n.left);
		System.out.print(n.data + " ");
		inOrder(n.right);
	}

	public void postOrder(Node n) {
		if (n == null)
			return;
		postOrder(n.left);
		postOrder(n.right);
		System.out.print(n.data + " ");
	}

	@Test
	public void treeTraversal() {
		Node root = getTree();
		System.out.println("Pre order:");
		preOrder(root);
		System.out.println();
		System.out.println("In order:");
		inOrder(root);
		System.out.println();
		System.out.println("Post order: ");
		postOrder(root);

	}

	public void printPathToLeaf(Node n, LinkedList<Integer> list) {

		if (n == null)
			return;
		list.add(n.data);

		printPathToLeaf(n.left, list);
		printPathToLeaf(n.right, list);

		if (n.left == null && n.right == null) {

			System.out.println(list.toString());
		}
		list.removeLast();
	}

	@Test
	public void testPrintPathToLeaf() {
		Node root = getTree();
		LinkedList<Integer> list = new LinkedList<>();
		System.out.println("printPathToLeaf: ");
		printPathToLeaf(root, list);
	}

	public List<ListNode> getListFromTree(Node root) {
		List<ListNode> list = new ArrayList<>();
		if (root == null)
			return list;
		LinkedList<Node> q = new LinkedList<>();
		q.addLast(root);
		q.addLast(null);

		ListNode head = null;
		ListNode t_head = null;

		while (!q.isEmpty()) {
			Node n = q.removeFirst();
			if (n != null) {

				if (head == null) {
					head = new ListNode(n.data);
					t_head = head;
				} else {
					t_head.next = new ListNode(n.data);
					t_head = t_head.next;
				}

				if (n.left != null)
					q.addLast(n.left);
				if (n.right != null)
					q.addLast(n.right);

			} else {
				if (q.size() > 0)
					q.addLast(null);
				list.add(head);
				head = null;
				t_head = null;
			}
		}

		return list;
	}

	@Test
	public void testGetListFromTree() {
		Node root = getTree();
		List<ListNode> list = getListFromTree(root);
		System.out.println("testGetListFromTree: ");
		for (ListNode node : list) {
			printLinkedList(node);
			System.out.println();
		}
		System.out.println();
	}

	public Node getInorderSuccessor(Node root,int x) {
		if (root == null )
			return null;
		LinkedList<Node> s1=new LinkedList();
		//LinkedList<Node> s2=new LinkedList();
		Node prev=null;
		s1.addFirst(root);
		
		while (s1.size()>0) {
			Node t=s1.removeFirst();
			
			if(t.left !=null)
				s1.addFirst(t.left);
			if(t.right !=null)
				s1.addFirst(t.right);
			
			if(t.data == x)
				break;
			
			prev=t;
		}
		
		return prev;
	}

	@Test
	public void testGetInorderSuccessor() {
		Node root=getTree();
		Node result= getInorderSuccessor(root,5 );
		System.out.println("testGetInorderSuccessor: "+result.data);
	}
	
	
	public Node commonAncestor(Node x,Node y, Node root) {
		
		if(root ==null)
			return root;
		if(x.data == root.data || y.data == root.data)
			return root;
		Node n=null;
		Node m=null;
		
		if(root.left ==null && root.right==null)
			return null;
		
		if(root.left !=null)
			n=commonAncestor(x, y, root.left);
		if(root.right !=null)
			m=commonAncestor(x, y, root.right);
		
		if(n !=null && m!=null) {
			 System.out.println(root.data);
		}
		
		if(n!=null)
			return n;
		if (m!=null)
			return m;
		return null;
	}
	
public Node commonAncestorNew(Node x,Node y, Node root) {
		
		if(root ==null)
			return root;
		if(x.data == root.data || y.data == root.data)
			return root;
		Node n=null;
		Node m=null;
		
		if(root.left ==null && root.right==null)
			return null;
		
		if(root.left !=null)
			n=commonAncestorNew(x, y, root.left);
		if(root.right !=null)
			m=commonAncestorNew(x, y, root.right);
		
		if(n !=null && m!=null && n.data == m.data) {
			return m;
		}
		
		if(n !=null && m!=null) {
			return root;
		}
		
		if(n!=null)
			return n;
		if (m!=null)
			return m;
		return null;
	}
	
	@Test
	public void testCommonAncestor() {
		System.out.println("testCommonAncestor:");
		Node root=getTree();
		Node x=new Node(5);
		Node y=new Node(9);
		Node n=commonAncestorNew(x, y, root);
	System.out.println("New "+n.data);
	}

	private void printLinkedList(ListNode node) {
		if (node == null)
			return;
		while (node != null) {
			System.out.print(node.data + " ");
			node = node.next;
		}
	}

	private Node getTree() {
		Node root = new Node(6);

		Node left = new Node(5);
		Node right = new Node(4);

		Node r_right = new Node(10);
		Node r_left = new Node(9);

		Node l_left = new Node(7);
		Node l_right = new Node(8);

		Node r_r_right = new Node(1);
		Node r_r_r_right = new Node(2);

		root.left = left;
		root.right = right;

		left.left = l_left;
		left.right = l_right;

		right.right = r_right;
		right.left = r_left;
		
		l_right.left=r_r_r_right;

		/*
		 * right.right.right = r_r_right; right.right.right.right = r_r_r_right;
		 */

		return root;
	}

	public class Node {
		private int data;
		private Node left;
		private Node right;

		public Node(int d) {
			this.data = d;
		}
	}

	public class ListNode {
		private int data;
		private ListNode next;

		public ListNode(int d) {
			this.data = d;
		}
	}

}
