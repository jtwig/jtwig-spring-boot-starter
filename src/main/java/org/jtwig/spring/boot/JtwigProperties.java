package org.jtwig.spring.boot;


import org.jtwig.environment.EnvironmentConfiguration;
import org.jtwig.environment.EnvironmentConfigurationBuilder;
import org.jtwig.extension.Extension;
import org.jtwig.spring.JtwigViewResolver;
import org.jtwig.web.servlet.JtwigRenderer;
import org.slf4j.Logger;
import org.springframework.boot.autoconfigure.template.AbstractTemplateViewResolverProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.Collection;

import static org.springframework.util.Assert.isInstanceOf;

@EnableConfigurationProperties
@ConfigurationProperties(prefix = "jtwig")
public class JtwigProperties extends AbstractTemplateViewResolverProperties {
    private final static Logger LOGGER = org.slf4j.LoggerFactory.getLogger(JtwigProperties.class);

    static final String DEFAULT_PREFIX = "classpath:/templates/";
    static final String DEFAULT_SUFFIX = ".twig";

    private EnvironmentConfiguration environmentConfiguration = EnvironmentConfigurationBuilder.configuration().build();

    public JtwigProperties() {
        super(DEFAULT_PREFIX, DEFAULT_SUFFIX);
        this.setCache(true);
    }

    public void setConfiguration(EnvironmentConfiguration configuration) {
        this.environmentConfiguration = configuration;
    }

    @Override
    public void applyToViewResolver(Object viewResolver) {
        super.applyToViewResolver(viewResolver);
        isInstanceOf(JtwigViewResolver.class, viewResolver,
                "ViewResolver is not an instance of JtwigViewResolver :" + viewResolver);

        ((JtwigViewResolver)viewResolver).setRenderer(new JtwigRenderer(environmentConfiguration));
        logJtwigInitialization();
    }

    private void logJtwigInitialization() {
        Collection<? extends Extension> extensions = environmentConfiguration.getExtensions();
        if (!extensions.isEmpty()) {
            LOGGER.info("Jtwig View Resolver configured with the following extensions:");
            for (Extension extension : extensions) {
                LOGGER.info(String.format(" - %s", extension.getClass().getSimpleName()));
            }
        } else {
            LOGGER.info("Jtwig View Resolver configured without extensions");
        }
    }
}
