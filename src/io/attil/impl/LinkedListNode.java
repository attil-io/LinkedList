package io.attil.impl;

public class LinkedListNode {
	public LinkedListNode next;
	public Object data;
	public boolean dropped = false;

	
	private long nodeId;
	private static long nodeIdCnt = 0;
	
	public LinkedListNode() {
		nodeId = nodeIdCnt++;
	}
	
	public long getId() {
		return nodeId;
	}
}
