package io.attil;

import io.attil.impl.LinkedListNode;

public class LinkedList {
	private LinkedListNode head;
	
	public void add(Object obj) {
		LinkedListNode next = new LinkedListNode();
		next.data = obj;
		if (head == null) {
			head = next;
		}
		else {
			LinkedListNode last = findLastNode();
			last.next = next;
		}
	}
	
	public void walk(WalkCallback callback) {
		LinkedListNode current = head;
		while (current != null) {
			callback.processNode(current.data);
			current = current.next;
		}
	}
	
	private LinkedListNode findLastNode() {
		LinkedListNode current = head;
		while (current != null && current.next != null) {
			current = current.next;
		}
		return current;
	}
}
