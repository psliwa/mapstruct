/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.mapstruct.ap.internal.model;

import org.mapstruct.ap.internal.model.common.ModelElement;
import org.mapstruct.ap.internal.model.common.Type;
import org.mapstruct.ap.internal.model.source.SourceMethod;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Represents super class constructor
 *
 * @author Piotr Śliwa
 */
public class SuperClassConstructor extends ModelElement implements Constructor {

    private final String name;
    private final List<Field> arguments;
    private final List<Type> thrownTypes;

    public static SuperClassConstructor forComponentModels(String name, SourceMethod sourceConstructor) {
        List<Field> arguments = sourceConstructor.getParameters().stream()
            .map( param -> new Field( param.getType(), param.getName(), true ) ).collect( Collectors.toList() );

        return new SuperClassConstructor( name, arguments, sourceConstructor.getThrownTypes() );
    }

    private SuperClassConstructor(String name, List<Field> arguments, List<Type> thrownTypes) {
        this.name = name;
        this.arguments = arguments;
        this.thrownTypes = thrownTypes;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Set<Type> getImportTypes() {
        return Stream.concat(
            arguments.stream().flatMap( filed -> filed.getType().getImportTypes().stream() ),
            thrownTypes.stream()
        ).collect( Collectors.toSet() );
    }

    public List<Field> getArguments() {
        return arguments;
    }

    public List<Type> getThrownTypes() {
        return thrownTypes;
    }
}
