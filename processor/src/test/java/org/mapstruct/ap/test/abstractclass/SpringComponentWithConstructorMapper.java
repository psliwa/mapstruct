/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.mapstruct.ap.test.abstractclass;

import java.math.BigDecimal;
import java.util.List;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

/**
 * @author Piotr Śliwa
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class SpringComponentWithConstructorMapper {
    protected SpringComponentWithConstructorMapper(List<BigDecimal> list, String s) {

    }
}
