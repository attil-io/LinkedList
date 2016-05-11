package io.attil.impl;

public class CallbackContext {
	private LinkedListNode node;
	
	public CallbackContext(LinkedListNode node) {
		this.node = node;
	}
	
	public Object getNodeValue() {
		return node.data;
	}
	
	public void dropNode() {
		node.dropped = true;
	}
	
}
