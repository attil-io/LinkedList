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

	public static void dropDuplicatesAlternative(final LinkedList ll) {
		ll.walk(new WalkCallback() {
			private long nodeIdx = 0;
			
			@Override
			public void processNode(CallbackContext ctx) {
				final Object actValue = ctx.getNodeValue();
				final long actNodeIdx = nodeIdx;
				
				ll.walk(new WalkCallback() {
					private long nodeIdxInternal = 0;
					
					@Override
					public void processNode(CallbackContext ctx) {
						final Object actValueInternal = ctx.getNodeValue();
						
						if ((nodeIdxInternal > actNodeIdx) && 
								((null == actValueInternal && null == actValue) || 
								 (null != actValue && actValue.equals(actValueInternal)))) {
							ctx.dropNode();
						}
						
						++nodeIdxInternal;
					}
				});
				
				
				++nodeIdx;
			}
		});
		
	}

	private static class RestCallback implements WalkCallback {
		private long start;
		private long cnt = 0;
		private LinkedList restList = new LinkedList();
		
		public RestCallback(long start) {
			this.start = start;
		}
		
		@Override
		public void processNode(CallbackContext ctx) {
			if (cnt >= start) {
				Object actValue = ctx.getNodeValue();
				restList.add(actValue);
			}
			++cnt;
		}
		
		public LinkedList rest() {
			return restList;
		}
	}
	
	public static LinkedList rest(final LinkedList ll, final long start) {
		RestCallback rest = new RestCallback(start);
		ll.walk(rest);
		return rest.rest();
	}

	private static class DropAtCallback implements WalkCallback {
		private long pos;
		private long cnt;
		private Object val;
		
		public DropAtCallback(long pos) {
			this.pos = pos;
		}
		
		@Override
		public void processNode(CallbackContext ctx) {
			if (cnt == pos) {
				val = ctx.getNodeValue();
				ctx.dropNode();
			}
			++cnt;
		}
		
		public Object dropped() {
			return val;
		}
	}	
	
	public static Object dropNodeAt(final LinkedList ll, final long pos) {
		DropAtCallback drop = new DropAtCallback(pos);
		ll.walk(drop);
		return drop.dropped();
	}

	private static class PartCallback implements WalkCallback {
		private LinkedList smaller;
		private LinkedList bigger;
		private Comparable middle;
		
		public PartCallback(final Comparable middle) {
			smaller = new LinkedList();
			bigger = new LinkedList();
			this.middle = middle;
		}
		
		@Override
		public void processNode(CallbackContext ctx) {
			Object val = ctx.getNodeValue();
			final int comparisonResult = middle.compareTo(val);
			if (comparisonResult >= 0) {
				smaller.add(val);
			}
			else {
				bigger.add(val);
			}
		}
		
		public LinkedList getSortedList() {
			// TODO: more optimal merge
			bigger.walk(new WalkCallback() {
				
				@Override
				public void processNode(CallbackContext ctx) {
					smaller.add(ctx.getNodeValue());
				}
			});
			return smaller;
		}
	}
	
	public static LinkedList part(LinkedList ll, final Comparable middle) {
		PartCallback partCallback = new PartCallback(middle);
		ll.walk(partCallback);
		return partCallback.getSortedList();
	}
	
	private static class EqualityCallBack implements WalkCallback {
		private long cnt = 0;
		private Object[] arr;
		private boolean areEqual = true;
		
		public EqualityCallBack(Object [] arr) {
			this.arr = arr;
		}
		
		@Override
		public void processNode(CallbackContext ctx) {
			Object val = ctx.getNodeValue();
			if (cnt >= arr.length) {
				areEqual = false;
				ctx.stopWalk();
			}
			else {
				Object arrVal = arr[(int)cnt];
				if (((val == null) && (arrVal != null)) ||
						(!val.equals(arrVal))) {
					areEqual = false;
					ctx.stopWalk();
				}
			}
			++cnt;
		}
		
		public boolean equal() {
			return (areEqual && (arr.length == cnt));
		}
	}
	
	public static boolean isEqual(LinkedList ll, final Object[] arr) {
		EqualityCallBack eq = new EqualityCallBack(arr);
		ll.walk(eq);
		return eq.equal();
	}

	public static Object nthMax(LinkedList ll, int i) {
		return null;
	}
}
