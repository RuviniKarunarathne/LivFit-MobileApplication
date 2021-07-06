package com.example.LivFit;

import android.widget.EditText;

import android.widget.TextView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PushupWorkoutTest {

    private PushupWorkout pushupWorkout;

    @Before
    public void setUp() throws Exception {
        pushupWorkout = new PushupWorkout();
    }

    /*@Test
    public void testcalculateCalBurnt(){
        int result = pushupWorkout.calculateCalBurnt(5,5.0);
        assertEquals(25.0,result);
    }

    @Test
    public void testIncrement(){
        int result = pushupWorkout.increment(10);
        assertEquals(11,result);
    }

    @Test
    public void testdecrement(){
       int result = pushupWorkout.decrement(10);
       assertEquals(9,result);
    }*/

    @Test
    public void testcaldisplay(){
        Double result = pushupWorkout.calDisplay("10.0","10.0");
        assertEquals(java.util.Optional.of(20.0),result);
    }



    @After
    public void tearDown() throws Exception {
        pushupWorkout=null;
    }


import junit.framework.TestCase;

import org.junit.Test;

public class PushupWorkoutTest extends TestCase {

    /*public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    @Test
    public void testIncrement() {
        int duration = 10;
        duration++;
        System.out.println(11);

    }


    @Test
    public void testCalculateCalBurnt() {
        int cvduration = 5;
        int cvcalburn = 5;
        int tot = 5 * 5;
        System.out.println(25);
    }

    @Test
    public void testCalDisplay() {
        int cvcalburned= 10;
        int cvburnedav=10;
        int totcal = 10 * 10;

        System.out.println(100);


    }*/

}