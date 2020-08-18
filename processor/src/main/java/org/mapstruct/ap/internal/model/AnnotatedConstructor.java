/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.mapstruct.ap.internal.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.mapstruct.ap.internal.model.common.ModelElement;
import org.mapstruct.ap.internal.model.common.Type;

/**
 * Represents a constructor that is used for constructor injection.
 *
 * @author Kevin Grüneberg
 */
public class AnnotatedConstructor extends ModelElement implements Constructor {

    private String name;
    private final List<AnnotationMapperReference> mapperReferences;
    private final List<Annotation> annotations;
    private final NoArgumentConstructor noArgumentConstructor;
    private final SuperClassConstructor superClassConstructor;
    private final Set<SupportingConstructorFragment> fragments;
    private final List<Field> arguments;

    public static AnnotatedConstructor forComponentModels(String name,
                                                          List<AnnotationMapperReference> mapperReferences,
                                                          List<Annotation> annotations,
                                                          Constructor constructor,
                                                          SuperClassConstructor superClassConstructor,
                                                          boolean includeNoArgConstructor) {
        return new AnnotatedConstructor(
            name,
            mapperReferences,
            annotations,
            noArgumentConstructor( name, includeNoArgConstructor, constructor ),
            includeNoArgConstructor ? null : superClassConstructor,
            getFragments( includeNoArgConstructor, constructor )
        );
    }

    private static NoArgumentConstructor noArgumentConstructor(String name, boolean includeNoArgConstructor,
                                                               Constructor constructor) {
        if ( includeNoArgConstructor ) {
            if ( constructor instanceof NoArgumentConstructor ) {
                return (NoArgumentConstructor) constructor;
            }
            else {
                return new NoArgumentConstructor( name, Collections.emptySet() );
            }
        }
        else {
            return null;
        }
    }

    private static Set<SupportingConstructorFragment> getFragments(boolean includeNoArgConstructor,
                                                                   Constructor constructor) {
        if ( includeNoArgConstructor ) {
            return Collections.emptySet();
        }
        else if ( constructor instanceof NoArgumentConstructor ) {
            return ( (NoArgumentConstructor) constructor ).getFragments();
        }
        else {
            return Collections.emptySet();
        }
    }

    private AnnotatedConstructor(String name, List<AnnotationMapperReference> mapperReferences,
                                 List<Annotation> annotations, NoArgumentConstructor noArgumentConstructor,
                                 SuperClassConstructor superClassConstructor,
                                 Set<SupportingConstructorFragment> fragments) {
        this.name = name;
        this.mapperReferences = mapperReferences;
        this.annotations = annotations;
        this.noArgumentConstructor = noArgumentConstructor;
        this.superClassConstructor = superClassConstructor;
        this.fragments = fragments;
        this.arguments = new LinkedList<>( mapperReferences );

        if ( superClassConstructor != null ) {
            arguments.addAll( superClassConstructor.getArguments() );
        }
    }

    @Override
    public Set<Type> getImportTypes() {
        Set<Type> types = new HashSet<>();

        for ( MapperReference mapperReference : mapperReferences ) {
            types.addAll( mapperReference.getImportTypes() );
        }

        for ( Annotation annotation : annotations ) {
            types.addAll( annotation.getImportTypes() );
        }

        if ( getSuperClassConstructor() != null ) {
            types.addAll( getSuperClassConstructor().getImportTypes() );
        }

        return types;
    }

    @Override
    public String getName() {
        return name;
    }

    public List<AnnotationMapperReference> getMapperReferences() {
        return mapperReferences;
    }

    public List<Field> getArguments() {
        return arguments;
    }

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    public NoArgumentConstructor getNoArgumentConstructor() {
        return noArgumentConstructor;
    }

    public SuperClassConstructor getSuperClassConstructor() {
        return superClassConstructor;
    }

    public Set<SupportingConstructorFragment> getFragments() {
        return fragments;
    }
}
