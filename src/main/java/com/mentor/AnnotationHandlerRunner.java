package com.mentor;

/**
 * @author Mykola Khazanovych
 *         02.05.2017;
 *         13:43;
 *         com.mentor;
 */
public class AnnotationHandlerRunner {
    public static void main(String[] args) {

        final String CLASS_WITH_ANNOTATED_METHODS_NAME = "com.mentor.TestClass";

        AnnotationHandler ah = new AnnotationHandler();
        ah.proceedAllAnnotatedMethods(CLASS_WITH_ANNOTATED_METHODS_NAME);
    }
}
