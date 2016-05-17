package io.attil;

import io.attil.impl.CallbackContext;
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
	
	private void dropNode(LinkedListNode node) {
		if (head == node) {
			head = head.next;
		}
		else {
			LinkedListNode n = head;
			while (n.next != node) {
				n = n.next;
			}
			n.next = node.next;
		}
	}
	
	public void walk(WalkCallback callback) {
		LinkedListNode current = head;
		while (current != null) {
			CallbackContext ctx = new CallbackContext(current);
			callback.processNode(ctx);
			if (current.dropped) {
				LinkedListNode currentTemp = current.next;
				dropNode(current);
				current = currentTemp;
			}
			else {
				current = current.next;
			}
			if (ctx.isStopped()) {
				current = null;		// force iteration to stop
			}
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
