package com.example.LivFit;

import android.widget.EditText;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Register_fitness_dataTest {

    private Register_fitness_data registerFitnessData;

    @Before
    public void setUp() throws Exception {
        registerFitnessData = new Register_fitness_data();
    }

    @Test
    public void testgetWeightType(){
        String result = registerFitnessData.getWeightType(21.35);
        assertEquals("Normal",result,0.001);
    }


    @After
    public void tearDown() throws Exception {
    }
}