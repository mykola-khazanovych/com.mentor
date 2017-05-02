package com.mentor.simpleanno;

import com.mentor.simpleanno.utils.ListBuilder;
import com.mentor.simpleanno.utils.MismatchedDataException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author Mykola Khazanovych
 *         01.05.2017;
 *         13:23;
 *         com.mentor;
 */

class AnnotationHandler {

    private Class annotatedClass;
    private Object annotatedClassInstance;
    private ListBuilder lb;

    void proceedAllAnnotatedMethods(String annotatedClassName) {

        this.initializeClassFields(annotatedClassName);

        this.runSupportMethods(lb.getBeforeTestsMethodsList());
        this.runTestMethods(lb.getTestMethodsList());
        this.runSupportMethods(lb.getAfterTestMethodsList());
    }

    private void initializeClassFields(String annotatedClassName) {

        try {
            this.annotatedClass = Class.forName(annotatedClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            annotatedClassInstance = annotatedClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        lb = new ListBuilder(annotatedClass);
    }

    private void runSupportMethods(List<Method> methodsList) {

        for (Method m : methodsList) {
            try {
                m.invoke(annotatedClassInstance);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    private void runTestMethods(List<Method> testMethodsList) {

        for (Method m : testMethodsList) {

            try {
                m.invoke(annotatedClassInstance);
                System.out.println("Test " + m.getName() + "() is passed!");

            } catch (InvocationTargetException ite) {
                if (ite.getCause() instanceof MismatchedDataException) {
                    System.out.println("Test " + m.getName() + "() is failed!");

                } else {
                    ite.printStackTrace();
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        this.runIgnoredMethodsList(lb.getIgnoredMethodsList());
    }

    private void runIgnoredMethodsList(List<Method> ignoredMethodsList) {

        System.out.println("---------------------------------------------");
        for (Method m : ignoredMethodsList) {
            System.out.println("Test " + m.getName() + "() was ignored!");
        }
    }
}
