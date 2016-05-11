package io.attil.testhelpers;

import org.mockito.ArgumentMatcher;

import io.attil.impl.CallbackContext;

public final class CallbackContextMatcher extends ArgumentMatcher<CallbackContext> {
	private Object expected;
	
	public CallbackContextMatcher(Object expected) {
		this.expected = expected;
	}
	
	@Override
	public boolean matches(Object argument) {
		return ((CallbackContext) argument).getNodeValue().equals(expected);
	}
}