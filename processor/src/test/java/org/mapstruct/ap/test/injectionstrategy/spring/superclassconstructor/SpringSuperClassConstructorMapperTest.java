/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.mapstruct.ap.test.injectionstrategy.spring.superclassconstructor;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.ap.test.injectionstrategy.shared.CustomerDto;
import org.mapstruct.ap.test.injectionstrategy.shared.CustomerEntity;
import org.mapstruct.ap.test.injectionstrategy.shared.CustomerRecordDto;
import org.mapstruct.ap.test.injectionstrategy.shared.CustomerRecordEntity;
import org.mapstruct.ap.test.injectionstrategy.shared.Gender;
import org.mapstruct.ap.test.injectionstrategy.shared.GenderDto;
import org.mapstruct.ap.testutil.WithClasses;
import org.mapstruct.ap.testutil.runner.AnnotationProcessorTestRunner;
import org.mapstruct.ap.testutil.runner.GeneratedSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Piotr Śliwa
 */
@WithClasses({
    CustomerRecordWithAdditionalDependenciesMapper.class,
    CustomerSpringConstructorMapper.class,
    CustomerDto.class,
    GenderDto.class,
    CustomerEntity.class,
    Gender.class,
    CustomerRecordEntity.class,
    CustomerRecordDto.class,
    RegularSpringComponent.class
})
@RunWith(AnnotationProcessorTestRunner.class)
@ComponentScan(basePackageClasses = CustomerRecordWithAdditionalDependenciesMapper.class)
@Configuration
public class SpringSuperClassConstructorMapperTest {

    @Rule
    public final GeneratedSource generatedSource = new GeneratedSource();

    @Autowired
    private CustomerRecordWithAdditionalDependenciesMapper mapper;

    private ConfigurableApplicationContext context;

    @Before
    public void springUp() {
        context = new AnnotationConfigApplicationContext( getClass() );
        context.getAutowireCapableBeanFactory().autowireBean( this );
    }

    @After
    public void springDown() {
        if ( context != null ) {
            context.close();
        }
    }

    @Test
    public void shouldCreateMapperWithAdditionalDependencies() {
        assertThat( mapper ).isNotNull();
        assertThat( mapper ).hasNoNullFieldsOrProperties();
    }
}
