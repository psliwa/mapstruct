/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.mapstruct.ap.test.injectionstrategy.spring.superclassconstructor;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ap.test.injectionstrategy.shared.CustomerRecordDto;
import org.mapstruct.ap.test.injectionstrategy.shared.CustomerRecordEntity;

/**
 * @author Piotr Śliwa
 */
@Mapper(componentModel = "spring",
    uses = { CustomerSpringConstructorMapper.class },
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class CustomerRecordWithAdditionalDependenciesMapper {
    private RegularSpringComponent regularSpringComponent;

    public CustomerRecordWithAdditionalDependenciesMapper(
        RegularSpringComponent regularSpringComponent) {
        this.regularSpringComponent = regularSpringComponent;
    }

    public abstract CustomerRecordDto asTarget(CustomerRecordEntity entity);
}
