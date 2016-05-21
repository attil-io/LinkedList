package io.attil;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class TestNthMaxParametrized {

    @Parameterized.Parameters
    public static Collection testData() {
       return Arrays.asList(new Object[][] {
          { 1, 2 },
          { 3, 4 },
          { 5, 6 },
          { 7, 8 },
          { 9, 10 }
       });
    }

    private int numberOne;
    private int numberTwo;
    
    public TestNthMaxParametrized(Integer numberOne, Integer numberTwo) {
    	this.numberOne = numberOne;
    	this.numberTwo = numberTwo;
    }    
    
    @Test
    public void testNthMax() {
    	assertEquals(numberTwo, numberOne + 1);
    }
}
