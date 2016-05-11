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
	
	public void deleteNodes(Object obj) {
		LinkedListNode current = head;
		LinkedListNode prev = null;
		while (current != null) {
			if ((obj == null && current.data == null) || 
					(obj != null && obj.equals(current.data))) {
				if (null == prev) {	// we are on head node
					head = current.next;
					current = head;
					prev = null;
				}
				else {
					prev.next = current.next;
					current = current.next;
					// prev is still the same
				}
			}
			else {
				prev = current;
				current = current.next;
			}
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
