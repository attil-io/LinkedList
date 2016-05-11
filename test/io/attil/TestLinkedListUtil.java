package io.attil;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.argThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.attil.testhelpers.CallbackContextMatcher;

@RunWith(MockitoJUnitRunner.class)
public class TestLinkedListUtil {
	
	@Mock
	WalkCallback cb;
	
	@Test
	public void deleteHeadNode() {
		LinkedList ll = new LinkedList();
		String hello = "hello";
		ll.add(hello);
		LinkedListUtil.removeNodes(ll, hello);
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
		LinkedListUtil.removeNodes(ll, hello2);
		ll.walk(cb);
		verify(cb, times(1)).processNode(argThat(new CallbackContextMatcher(hello)));
		verify(cb, never()).processNode(argThat(new CallbackContextMatcher(hello2)));
	}
	
	@Test
	public void deleteMoreNodes() {
		LinkedList ll = new LinkedList();
		String hello = "hello";
		ll.add(hello);
		String hello2 = "hello2";
		ll.add(hello2);
		ll.add(hello2);		// twice
		LinkedListUtil.removeNodes(ll, hello2);
		ll.walk(cb);
		verify(cb, times(1)).processNode(argThat(new CallbackContextMatcher(hello)));
		verify(cb, never()).processNode(argThat(new CallbackContextMatcher(hello2)));
	}
}
