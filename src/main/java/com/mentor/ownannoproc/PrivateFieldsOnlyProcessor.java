package com.mentor.ownannoproc;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.Diagnostic;
import java.util.Set;

/**
 * @author Mykola Khazanovych
 *         02.05.2017;
 *         15:58;
 *         com.mentor.ownannoproc;
 */

@SupportedAnnotationTypes("PrivateFieldsOnly")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class PrivateFieldsOnlyProcessor extends AbstractProcessor {

    @Override
    public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {

        for (final Element element : roundEnv.getElementsAnnotatedWith(PrivateFieldsOnly.class)) {
            if (element instanceof TypeElement) {
                final TypeElement typeElement = (TypeElement) element;

                for (final Element eclosedElement : typeElement.getEnclosedElements()) {
                    if (eclosedElement instanceof VariableElement) {
                        final VariableElement variableElement = (VariableElement) eclosedElement;

                        if (variableElement.getModifiers().contains(Modifier.PUBLIC)) {
                            processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
                                    String.format("Incapsulation violation! In %s" +
                                                    " Make all public fields private or protected! -> %s",
                                            typeElement.getSimpleName(), variableElement.getSimpleName()
                                    )
                            );
                        }
                    }
                }
            }
        }
        // Claiming that annotations have been processed by this processor
        return true;
    }
}