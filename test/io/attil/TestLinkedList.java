package io.attil;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.attil.testhelpers.CallbackContextMatcher;

@RunWith(MockitoJUnitRunner.class)
public class TestLinkedList {

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
}
