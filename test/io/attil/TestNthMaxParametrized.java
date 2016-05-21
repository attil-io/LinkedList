package io.attil;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class TestNthMaxParametrized {
    @Parameterized.Parameters
    public static Collection testData() {
       return Arrays.asList(new Object[][] {
          { Arrays.asList(),  1, null},
          { Arrays.asList(5),  1, 5},
          { Arrays.asList(3, 5, 9, 1),  1, 9},
          { Arrays.asList(3, 5, 9, 1),  2, 5},
          { Arrays.asList(3, 5, 9, 1),  3, 3},
          { Arrays.asList(3, 5, 9, 1),  4, 1},
          { Arrays.asList(3, 5, 9, 1),  5, null},
          { Arrays.asList(1, 1),  1, 1},
          { Arrays.asList(1, 1),  2, 1},
       });
    }

    private LinkedList ll;
    private int nthMax;
    private Integer expected;
    
    public TestNthMaxParametrized(List<Integer> list, Integer nthMax, Integer expected) {
    	ll = new LinkedList();
    	for (Integer i : list) {
    		ll.add(i);
    	}
    	this.nthMax = nthMax;
   		this.expected = expected;
    }
    
    @Test
    public void testNthMax() {
    	assertEquals(expected, LinkedListUtil.nthMax(ll, nthMax));
    }
}
