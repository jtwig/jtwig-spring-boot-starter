package org.jtwig.spring.boot;

import org.jtwig.spring.boot.availability.IsClassPresent;
import org.junit.Test;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JtwigTemplateAvailabilityProviderTest {
    private final IsClassPresent isClassPresent = mock(IsClassPresent.class);
    private JtwigTemplateAvailabilityProvider underTest = new JtwigTemplateAvailabilityProvider(isClassPresent);

    @Test
    public void isTemplateAvailableNoJtwigViewResolver() throws Exception {
        ClassLoader classLoader = mock(ClassLoader.class);
        ResourceLoader resourceLoader = mock(ResourceLoader.class);
        Environment environment = mock(Environment.class);
        String view = "boop";

        when(isClassPresent.isPresent(classLoader, "org.jtwig.spring.JtwigViewResolver")).thenReturn(false);

        boolean result = underTest.isTemplateAvailable(view, environment, classLoader, resourceLoader);

        assertThat(result, is(false));
    }

    @Test
    public void isTemplateAvailableNoJtwigTempalte() throws Exception {
        ClassLoader classLoader = mock(ClassLoader.class);
        ResourceLoader resourceLoader = mock(ResourceLoader.class);
        Environment environment = mock(Environment.class);
        String view = "boop";

        when(isClassPresent.isPresent(classLoader, "org.jtwig.spring.JtwigViewResolver")).thenReturn(true);
        when(isClassPresent.isPresent(classLoader, "org.jtwig.JtwigTemplate")).thenReturn(false);

        boolean result = underTest.isTemplateAvailable(view, environment, classLoader, resourceLoader);

        assertThat(result, is(false));
    }
}