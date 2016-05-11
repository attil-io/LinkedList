package io.attil;

import io.attil.impl.CallbackContext;

public class LinkedListUtil {
	private static class FinderCallback implements WalkCallback {
		private boolean found = false;
		private Object needle;
		
		public FinderCallback(Object o) {
			needle = o;
		}
		
		@Override
		public void processNode(CallbackContext ctx) {
			Object actValue = ctx.getNodeValue();
			if ((null == actValue && null == needle) || 
					(null != actValue && actValue.equals(needle))) {
				found = true;
			}
		}
		
		public boolean isFound() {
			return found;
		}
	}
	
	public static boolean contains(LinkedList ll, final Object obj) {
		FinderCallback finder = new FinderCallback(obj);
		ll.walk(finder);
		return finder.isFound();
	}

	private static class CounterCallback implements WalkCallback {
		private long cnt = 0;
		
		@Override
		public void processNode(CallbackContext ctx) {
			++cnt;
		}
		
		public long count() {
			return cnt;
		}
	}

	
	public static long count(LinkedList ll) {
		CounterCallback counter = new CounterCallback();
		ll.walk(counter);
		return counter.count();

	}
	
	public static void removeNodes(LinkedList ll, final Object obj) {
		ll.walk(new WalkCallback() {
			
			@Override
			public void processNode(CallbackContext ctx) {
				Object actValue = ctx.getNodeValue();
				if ((null == actValue && null == obj) || 
						(null != actValue && actValue.equals(obj))) {
					ctx.dropNode();
				}
			}
		});
	}

	public static void dropDuplicates(LinkedList ll) {
		ll.walk(new WalkCallback() {
			private LinkedList seen = new LinkedList();
			
			@Override
			public void processNode(CallbackContext ctx) {
				Object actValue = ctx.getNodeValue();
				if (contains(seen, actValue)) {
					ctx.dropNode();
				}
				else {
					seen.add(actValue);
				}
			}
		});
	}

}
