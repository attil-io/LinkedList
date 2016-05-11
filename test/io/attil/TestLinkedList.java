package io.attil;

import io.attil.impl.CallbackContext;


import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestLinkedList {

	private static final class CallbackContextMatcher extends ArgumentMatcher<CallbackContext> {
		private Object expected;
		
		public CallbackContextMatcher(Object expected) {
			this.expected = expected;
		}
		
		@Override
		public boolean matches(Object argument) {
			return ((CallbackContext) argument).getNodeValue().equals(expected);
		}
	}	
	
	@Mock
	WalkCallback cb;
	
	@Test
	public void emptyList() {
		LinkedList ll = new LinkedList();
		ll.walk(cb);
		verify(cb, never()).processNode(any());
	}

	@Test
	public void oneElementList() {
		LinkedList ll = new LinkedList();
		String hello = "hello";
		ll.add(hello);
		ll.walk(cb);
		verify(cb, times(1)).processNode(argThat(new CallbackContextMatcher(hello)));
	}
/*
	@Test
	public void deleteHeadNode() {
		LinkedList ll = new LinkedList();
		String hello = "hello";
		ll.add(hello);
		ll.deleteNodes(hello);
		ll.walk(cb);
		verify(cb, never()).processNode(any());
	}

	@Test
	public void deleteNonHeadNode() {
		LinkedList ll = new LinkedList();
		String hello = "hello";
		ll.add(hello);
		String hello2 = "hello2";
		ll.add(hello2);
		ll.deleteNodes(hello2);
		ll.walk(cb);
		verify(cb, times(1)).processNode(hello);
		verify(cb, never()).processNode(hello2);
	}

	@Test
	public void deleteMoreNodes() {
		LinkedList ll = new LinkedList();
		String hello = "hello";
		ll.add(hello);
		String hello2 = "hello2";
		ll.add(hello2);
		ll.add(hello2);		// twice
		ll.deleteNodes(hello2);
		ll.walk(cb);
		verify(cb, times(1)).processNode(hello);
		verify(cb, never()).processNode(hello2);
	}
*/

}
