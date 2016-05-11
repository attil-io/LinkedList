package io.attil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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


	@Test
	public void deleteNonExistingNode() {
		LinkedList ll = new LinkedList();
		String hello = "hello";
		ll.add(hello);
		String hello2 = "hello2";
		LinkedListUtil.removeNodes(ll, hello2);
		ll.walk(cb);
		verify(cb, times(1)).processNode(argThat(new CallbackContextMatcher(hello)));
	}
	
	@Test
	public void dropDuplicatesEmptyList() {
		LinkedList ll = new LinkedList();
		LinkedListUtil.dropDuplicates(ll);
		ll.walk(cb);
		verify(cb, never()).processNode(any());
	}


	@Test
	public void dropDuplicatesTwoDuplicates() {
		LinkedList ll = new LinkedList();
		String hello = "hello";
		ll.add(hello);
		ll.add(hello);
		LinkedListUtil.dropDuplicates(ll);
		ll.walk(cb);
		verify(cb, times(1)).processNode(argThat(new CallbackContextMatcher(hello)));
	}
	
	@Test
	public void testContainsInEmptyList() {
		LinkedList ll = new LinkedList();
		assertFalse(LinkedListUtil.contains(ll, "hello"));
	}

	@Test
	public void testContainsPositive() {
		LinkedList ll = new LinkedList();
		String hello = "hello";
		ll.add(hello);
		assertTrue(LinkedListUtil.contains(ll, "hello"));
	}

	@Test
	public void testContainsNegative() {
		LinkedList ll = new LinkedList();
		String hello = "hello";
		ll.add(hello);
		assertFalse(LinkedListUtil.contains(ll, "hello2"));
	}

	@Test
	public void testContainsTwice() {
		LinkedList ll = new LinkedList();
		String hello = "hello";
		ll.add(hello);
		ll.add(hello);
		assertTrue(LinkedListUtil.contains(ll, "hello"));
	}

	@Test
	public void testCountEmpty() {
		LinkedList ll = new LinkedList();
		assertEquals(0, LinkedListUtil.count(ll));
	}

	@Test
	public void testCountOneElement() {
		LinkedList ll = new LinkedList();
		String hello = "hello";
		ll.add(hello);
		assertEquals(1, LinkedListUtil.count(ll));
	}

	@Test
	public void testCountTwoElements() {
		LinkedList ll = new LinkedList();
		String hello = "hello";
		ll.add(hello);
		ll.add(hello);
		assertEquals(2, LinkedListUtil.count(ll));
	}

	@Test
	public void dropDuplicatesAlternativeEmptyList() {
		LinkedList ll = new LinkedList();
		LinkedListUtil.dropDuplicatesAlternative(ll);
		ll.walk(cb);
		verify(cb, never()).processNode(any());
	}


	@Test
	public void dropDuplicatesAlternativeTwoDuplicates() {
		LinkedList ll = new LinkedList();
		String hello = "hello";
		ll.add(hello);
		ll.add(hello);
		LinkedListUtil.dropDuplicatesAlternative(ll);
		ll.walk(cb);
		verify(cb, times(1)).processNode(argThat(new CallbackContextMatcher(hello)));
	}

	@Test
	public void dropDuplicatesAlternativeTwoDuplicatesAndThird() {
		LinkedList ll = new LinkedList();
		String hello = "hello";
		String hello2 = "hello2";
		ll.add(hello);
		ll.add(hello2);
		ll.add(hello);
		LinkedListUtil.dropDuplicatesAlternative(ll);
		ll.walk(cb);
		verify(cb, times(1)).processNode(argThat(new CallbackContextMatcher(hello)));
		verify(cb, times(1)).processNode(argThat(new CallbackContextMatcher(hello2)));
	}	

}
