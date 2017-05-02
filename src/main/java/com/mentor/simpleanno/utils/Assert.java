package com.mentor.simpleanno.utils;

/**
 * @author Mykola Khazanovych
 *         01.05.2017;
 *         12:24;
 *         com.mentor;
 */
public class Assert {

    public static void assertEquals(int expected, int actual) throws MismatchedDataException {
        if(expected != actual){
            throw new MismatchedDataException();
        }
    }
}
