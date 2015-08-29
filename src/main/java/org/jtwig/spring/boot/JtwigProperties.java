package org.jtwig.spring.boot;


import org.jtwig.environment.EnvironmentConfigurationBuilder;
import org.jtwig.spring.JtwigViewResolver;
import org.jtwig.web.servlet.JtwigRenderer;
import org.springframework.boot.autoconfigure.template.AbstractTemplateViewResolverProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import static org.springframework.util.Assert.isInstanceOf;

@EnableConfigurationProperties
@ConfigurationProperties(prefix = "jtwig")
public class JtwigProperties extends AbstractTemplateViewResolverProperties {
    static final String DEFAULT_PREFIX = "classpath:/templates/";
    static final String DEFAULT_SUFFIX = ".twig";

    private EnvironmentConfigurationBuilder configurationBuilder = EnvironmentConfigurationBuilder.configuration();

    public JtwigProperties() {
        super(DEFAULT_PREFIX, DEFAULT_SUFFIX);
        this.setCache(true);
    }

    public void setConfigurationBuilder(EnvironmentConfigurationBuilder configurationBuilder) {
        this.configurationBuilder = configurationBuilder;
    }

    @Override
    public void applyToViewResolver(Object viewResolver) {
        super.applyToViewResolver(viewResolver);
        isInstanceOf(JtwigViewResolver.class, viewResolver,
                "ViewResolver is not an instance of JtwigViewResolver :" + viewResolver);

        ((JtwigViewResolver)viewResolver).setRenderer(new JtwigRenderer(configurationBuilder));

    }
}
