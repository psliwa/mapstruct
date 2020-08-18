/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.mapstruct.ap.test.abstractclass;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.ap.testutil.WithClasses;
import org.mapstruct.ap.testutil.runner.AnnotationProcessorTestRunner;
import org.mapstruct.ap.testutil.runner.GeneratedSource;

import static java.lang.System.lineSeparator;

/**
 * @author Piotr Śliwa
 */
@WithClasses({
    SpringComponentWithConstructorMapper.class,
    SpringComponentWithConstructorThrowingExceptionMapper.class
})
@RunWith(AnnotationProcessorTestRunner.class)
public class SpringComponentWithConstructorTest {

    @Rule
    public final GeneratedSource generatedSource = new GeneratedSource();

    @Test
    public void shouldHaveConstructorInjection() {
        generatedSource.forMapper( SpringComponentWithConstructorMapper.class )
            .content()
            .contains( "import java.util.List;" )
            .contains( "import java.math.BigDecimal;" )
            .contains( "public SpringComponentWithConstructorMapperImpl(List<BigDecimal> list, String s) {" +
                lineSeparator() + "        super( list, s );" + lineSeparator() + "    }"
            );
    }

    @Test
    public void shouldHaveConstructorInjectionWithThrowsClause() {
        generatedSource.forMapper( SpringComponentWithConstructorThrowingExceptionMapper.class )
            .content()
            .contains( "import java.util.List;" )
            .contains( "import java.math.BigDecimal;" )
            .contains( "import java.io.IOException;" )
            .contains(
                "public SpringComponentWithConstructorThrowingExceptionMapperImpl(List<BigDecimal> list, String s) " +
                    "throws IOException {" +
                    lineSeparator() + "        super( list, s );" + lineSeparator() + "    }"
            );
    }
}
