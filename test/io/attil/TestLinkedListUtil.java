package io.attil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.argThat;
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

	
	@Test
	public void testRestEmpty() {
		LinkedList ll = new LinkedList();
		LinkedList rest = LinkedListUtil.rest(ll, 0);
		assertEquals(0, LinkedListUtil.count(rest));
	}
	
	@Test
	public void testRestStart() {
		LinkedList ll = new LinkedList();
		String hello = "hello";
		ll.add(hello);
		ll.add(hello);
		ll.add(hello);
		LinkedList rest = LinkedListUtil.rest(ll, 0);
		assertEquals(3, LinkedListUtil.count(rest));
	}

	@Test
	public void testRestMiddle() {
		LinkedList ll = new LinkedList();
		String hello = "hello";
		ll.add(hello);
		ll.add(hello);
		ll.add(hello);
		LinkedList rest = LinkedListUtil.rest(ll, 1);
		assertEquals(2, LinkedListUtil.count(rest));
	}

	@Test
	public void testRestLast() {
		LinkedList ll = new LinkedList();
		String hello = "hello";
		ll.add(hello);
		ll.add(hello);
		ll.add(hello);
		LinkedList rest = LinkedListUtil.rest(ll, 2);
		assertEquals(1, LinkedListUtil.count(rest));
	}

	@Test
	public void testRestAfterLast() {
		LinkedList ll = new LinkedList();
		String hello = "hello";
		ll.add(hello);
		ll.add(hello);
		ll.add(hello);
		LinkedList rest = LinkedListUtil.rest(ll, 3);
		assertEquals(0, LinkedListUtil.count(rest));
	}

	@Test
	public void testDropAtEmpty() {
		LinkedList ll = new LinkedList();
		Object result = LinkedListUtil.dropNodeAt(ll, 0);
		assertNull(result);
		assertEquals(0, LinkedListUtil.count(ll));
	}
	
	@Test
	public void testDropAtStart() {
		LinkedList ll = new LinkedList();
		ll.add("hello");
		ll.add("hello2");
		ll.add("hello3");
		Object result = LinkedListUtil.dropNodeAt(ll, 0);
		assertEquals("hello", result);
		assertEquals(2, LinkedListUtil.count(ll));
	}

	@Test
	public void testDropAtMiddle() {
		LinkedList ll = new LinkedList();
		ll.add("hello");
		ll.add("hello2");
		ll.add("hello3");
		Object result = LinkedListUtil.dropNodeAt(ll, 1);
		assertEquals("hello2", result);
		assertEquals(2, LinkedListUtil.count(ll));
	}

	@Test
	public void testDropAtLast() {
		LinkedList ll = new LinkedList();
		ll.add("hello");
		ll.add("hello2");
		ll.add("hello3");
		Object result = LinkedListUtil.dropNodeAt(ll, 2);
		assertEquals("hello3", result);
		assertEquals(2, LinkedListUtil.count(ll));
	}

	@Test
	public void testDropAtAfterLast() {
		LinkedList ll = new LinkedList();
		ll.add("hello");
		ll.add("hello2");
		ll.add("hello3");
		Object result = LinkedListUtil.dropNodeAt(ll, 3);
		assertNull(result);
		assertEquals(3, LinkedListUtil.count(ll));
	}
	
	@Test
	public void testPartEmpty() {
		LinkedList ll = new LinkedList();
		LinkedList result = LinkedListUtil.part(ll, 10);
		Object []  expected = {};
		assertTrue(LinkedListUtil.isEqual(result,  expected));
	}

	@Test
	public void testPartOneElement() {
		LinkedList ll = new LinkedList();
		ll.add(5);
		LinkedList result = LinkedListUtil.part(ll, 10);
		Object []  expected = {5};
		assertTrue(LinkedListUtil.isEqual(result,  expected));
	}

	@Test
	public void testPartMoreElements() {
		LinkedList ll = new LinkedList();
		ll.add(5);
		ll.add(1);
		ll.add(1);
		ll.add(9);
		ll.add(11);
		ll.add(2);
		ll.add(3);
		LinkedList result = LinkedListUtil.part(ll, 3);
		Object []  expected = {1, 1, 2, 3, 5, 9, 11};
		assertTrue(LinkedListUtil.isEqual(result,  expected));
	}	
	
	@Test
	public void testEqualsEmpty() {
		LinkedList ll = new LinkedList();
		Object [] compare = {};
		assertTrue(LinkedListUtil.isEqual(ll, compare));
	}

	@Test
	public void testEqualsOneElement() {
		LinkedList ll = new LinkedList();
		ll.add(5);
		Object [] compare = {5};
		assertTrue(LinkedListUtil.isEqual(ll, compare));
	}

	@Test
	public void testEqualsDifferent() {
		LinkedList ll = new LinkedList();
		ll.add(5);
		Object [] compare = {6};
		assertFalse(LinkedListUtil.isEqual(ll, compare));
	}

	@Test
	public void testEqualsTwoElement() {
		LinkedList ll = new LinkedList();
		ll.add(5);
		ll.add(6);
		Object [] compare = {5, 6};
		assertTrue(LinkedListUtil.isEqual(ll, compare));
	}
	
	@Test
	public void testEqualsArrLonger() {
		LinkedList ll = new LinkedList();
		ll.add(5);
		Object [] compare = {5, 6};
		assertFalse(LinkedListUtil.isEqual(ll, compare));
	}

	@Test
	public void testEqualsListLonger() {
		LinkedList ll = new LinkedList();
		ll.add(5);
		ll.add(6);
		Object [] compare = {5};
		assertFalse(LinkedListUtil.isEqual(ll, compare));
	}
	
	@Test
	public void testNthMaxEmpty() {
		LinkedList ll = new LinkedList();
		Object res = LinkedListUtil.nthMax(ll, 1);
		assertNull(res);
	}

	
	@Test
	public void testFirstMax() {
		LinkedList ll = new LinkedList();
		ll.add(1);
		ll.add(3);
		ll.add(2);
		Object res = LinkedListUtil.nthMax(ll, 1);
		assertEquals(3, res);
	}

	@Test
	public void testSecondMax() {
		LinkedList ll = new LinkedList();
		ll.add(1);
		ll.add(3);
		ll.add(2);
		Object res = LinkedListUtil.nthMax(ll, 2);
		assertEquals(2, res);
	}

	@Test
	public void testPalindromeEmpty() {
		LinkedList ll = new LinkedList();
		assertTrue(LinkedListUtil.isPalindrome(ll));
	}

	@Test
	public void testPalindromeOneElement() {
		LinkedList ll = new LinkedList();
		ll.add(1);
		assertTrue(LinkedListUtil.isPalindrome(ll));
	}

	@Test
	public void testPalindromeTrueTwoElements() {
		LinkedList ll = new LinkedList();
		ll.add(1);
		ll.add(1);
		assertTrue(LinkedListUtil.isPalindrome(ll));
	}

	@Test
	public void testPalindromeFalseTwoElements() {
		LinkedList ll = new LinkedList();
		ll.add(1);
		ll.add(2);
		assertFalse(LinkedListUtil.isPalindrome(ll));
	}
	
	@Test
	public void testPalindromeTrueThreeElements() {
		LinkedList ll = new LinkedList();
		ll.add(1);
		ll.add(2);
		ll.add(1);
		assertTrue(LinkedListUtil.isPalindrome(ll));
	}

	@Test
	public void testPalindromeFalseThreeElements() {
		LinkedList ll = new LinkedList();
		ll.add(1);
		ll.add(2);
		ll.add(2);
		assertFalse(LinkedListUtil.isPalindrome(ll));
	}
}
