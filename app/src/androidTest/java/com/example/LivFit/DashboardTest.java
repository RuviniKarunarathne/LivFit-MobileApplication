package com.example.LivFit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DashboardTest {

    private Dashboard dashboard;

    @Before
    public void setUp() throws Exception {
        dashboard = new Dashboard();
    }

    @Test
    public void testcalculateRemainingCalories(){
        String result = dashboard.calculateRemainingCalories("1200.00","849.00","239.00");
        assertEquals("590.00",result,0.001);
    }

    @After
    public void tearDown() throws Exception {
        dashboard = null;
    }
}