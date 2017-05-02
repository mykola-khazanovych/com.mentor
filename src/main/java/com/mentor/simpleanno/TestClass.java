package com.mentor.simpleanno;

import com.mentor.simpleanno.annotations.AfterTests;
import com.mentor.simpleanno.annotations.BeforeTests;
import com.mentor.simpleanno.annotations.Ignore;
import com.mentor.simpleanno.annotations.Test;
import com.mentor.simpleanno.utils.Assert;
import com.mentor.simpleanno.utils.MismatchedDataException;

/**
 * @author Mykola Khazanovych
 *         01.05.2017;
 *         12:11;
 *         com.mentor;
 */

public class TestClass {

    private Integer a;
    private Integer b;
    private Integer c;

    @BeforeTests
    public void init() {

        System.out.println("Data initialization\n");
        this.a = 0;
        this.b = 5;
        this.c = 120;
    }

    @Test
    public void sum() throws MismatchedDataException {

        int result = b + c;
        Assert.assertEquals(125, result);
    }

    @Ignore
    @Test
    public void division() throws MismatchedDataException {
        int result = b / a;
        Assert.assertEquals(0, result);
    }


    @Test
    public void multiply() throws MismatchedDataException {
        int result = b * c;
        Assert.assertEquals(600, result);
    }

    @AfterTests
    public void destroy() {
        System.out.println("\nData utilization");
        this.a = null;
        this.b = null;
        this.c = null;
    }
}


