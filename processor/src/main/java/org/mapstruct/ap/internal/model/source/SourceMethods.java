/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.mapstruct.ap.internal.model.source;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Holding source methods instances
 *
 * @author Piotr Śliwa
 */
public class SourceMethods {
    private final List<SourceMethod> methods;
    private final List<SourceMethod> constructors;

    public SourceMethods(List<SourceMethod> methods,
                         List<SourceMethod> constructors) {
        this.methods = new ArrayList<>( methods );
        this.constructors = new ArrayList<>( constructors );
    }

    public List<SourceMethod> getMethods() {
        return Collections.unmodifiableList( methods );
    }

    public List<SourceMethod> getConstructors() {
        return Collections.unmodifiableList( constructors );
    }

    public SourceMethods merge(SourceMethods sourceMethods) {
        return new SourceMethods(
            merge( this.methods, sourceMethods.methods ),
            merge( this.constructors, sourceMethods.constructors )
        );
    }

    private List<SourceMethod> merge(List<SourceMethod> methods1, List<SourceMethod> methods2) {
        List<SourceMethod> methods = new LinkedList<>();
        methods.addAll( methods1 );
        methods.addAll( methods2 );

        return methods;
    }
}
