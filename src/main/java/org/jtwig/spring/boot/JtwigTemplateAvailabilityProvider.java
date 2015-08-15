package org.jtwig.spring.boot;

import org.springframework.boot.autoconfigure.template.TemplateAvailabilityProvider;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ClassUtils;

public class JtwigTemplateAvailabilityProvider implements TemplateAvailabilityProvider {
    @Override
    public boolean isTemplateAvailable(String view, Environment environment, ClassLoader classLoader, ResourceLoader resourceLoader) {
        if (!ClassUtils.isPresent("org.jtwig.spring.JtwigViewResolver", classLoader)
                || !ClassUtils.isPresent("org.jtwig.JtwigTemplate", classLoader)
                ) {
            return false;
        }

        String prefix = environment.getProperty("jtwig.prefix", JtwigProperties.DEFAULT_PREFIX);
        String suffix = environment.getProperty("jtwig.suffix", JtwigProperties.DEFAULT_SUFFIX);

        return resourceLoader.getResource(prefix + view + suffix).exists();
    }
}
