package org.jtwig.spring.boot;

import org.jtwig.spring.boot.availability.IsClassPresent;
import org.springframework.boot.autoconfigure.template.TemplateAvailabilityProvider;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;

public class JtwigTemplateAvailabilityProvider implements TemplateAvailabilityProvider {
    private final IsClassPresent isClassPresent;

    public JtwigTemplateAvailabilityProvider(IsClassPresent isClassPresent) {
        this.isClassPresent = isClassPresent;
    }

    public JtwigTemplateAvailabilityProvider() {
        this(new IsClassPresent());
    }

    @Override
    public boolean isTemplateAvailable(String view, Environment environment, ClassLoader classLoader, ResourceLoader resourceLoader) {
        if (!isClassPresent.isPresent(classLoader, "org.jtwig.spring.JtwigViewResolver")
                || !isClassPresent.isPresent(classLoader, "org.jtwig.JtwigTemplate")
                ) {
            return false;
        }

        String prefix = environment.getProperty("jtwig.prefix", JtwigProperties.DEFAULT_PREFIX);
        String suffix = environment.getProperty("jtwig.suffix", JtwigProperties.DEFAULT_SUFFIX);

        return resourceLoader.getResource(prefix + view + suffix).exists();
    }
}
