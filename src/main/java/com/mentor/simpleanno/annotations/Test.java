package com.mentor.simpleanno.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Mykola Khazanovych
 *         01.05.2017;
 *         11:48;
 *         com.mentor.simpleanno.annotations;
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Test {

}
