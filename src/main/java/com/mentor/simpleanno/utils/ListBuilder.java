package com.mentor.simpleanno.utils;

import com.mentor.simpleanno.annotations.AfterTests;
import com.mentor.simpleanno.annotations.BeforeTests;
import com.mentor.simpleanno.annotations.Ignore;
import com.mentor.simpleanno.annotations.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mykola Khazanovych
 *         02.05.2017;
 *         12:51;
 *         com.mentor;
 */
public class ListBuilder {

    private Class c;

    private List<Method> beforeTestMethodsList;
    private List<Method> testMethodsList;
    private List<Method> ignoredMethodsList;
    private List<Method> afterTestMethodsList;

    public ListBuilder(Class annotatedClass) {
        this.c = annotatedClass;
        this.separateMethodsByAnnotations();
    }

    private void separateMethodsByAnnotations() {

        beforeTestMethodsList = new ArrayList<>();
        testMethodsList = new ArrayList<>();
        ignoredMethodsList = new ArrayList<>();
        afterTestMethodsList = new ArrayList<>();

        for (Method m : c.getMethods()) {
            if(m.isAnnotationPresent(BeforeTests.class)){
                beforeTestMethodsList.add(m);
            }
            if(m.isAnnotationPresent(Test.class) && !m.isAnnotationPresent(Ignore.class)){
                testMethodsList.add(m);
            }
            if(m.isAnnotationPresent(Ignore.class)){
                ignoredMethodsList.add(m);
            }
            if(m.isAnnotationPresent(AfterTests.class)){
                afterTestMethodsList.add(m);
            }
        }
    }

    public List<Method> getBeforeTestsMethodsList() {
        return beforeTestMethodsList;
    }

    public List<Method> getTestMethodsList() {
        return testMethodsList;
    }

    public List<Method> getIgnoredMethodsList() {
        return ignoredMethodsList;
    }

    public List<Method> getAfterTestMethodsList() {
        return afterTestMethodsList;
    }
}
