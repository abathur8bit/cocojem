package com.axorion.coco;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple CocoJEM.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    public void testChar() {
        int n=65;
        String formatted = String.format("%c",n);
        assertEquals("A",formatted);
    }

    public void testHex() {
        String hex = "0c00";
        int n = Integer.parseInt(hex,16);
        assertTrue(n==0xc00);
    }

    public void testBinary() {
        assertTrue(Integer.parseInt("0101",2)==5);
        assertTrue(Integer.parseInt("0001",2)==1);
        assertTrue(Integer.parseInt("0111",2)==7);
        assertTrue(Integer.parseInt("11111111",2)==0xFF);
    }
}
