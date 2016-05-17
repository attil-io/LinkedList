package io.attil.impl;

public class CallbackContext {
	private LinkedListNode node;
	private boolean stopped = false;
	
	public CallbackContext(LinkedListNode node) {
		this.node = node;
	}
	
	public Object getNodeValue() {
		return node.data;
	}
	
	public void dropNode() {
		node.dropped = true;
	}
	
	public void stopWalk() {
		stopped = true;
	}
	
	public boolean isStopped() {
		return stopped;
	}
}
