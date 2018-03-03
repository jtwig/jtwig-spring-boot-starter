package org.jtwig.spring.boot;


import org.jtwig.spring.JtwigViewResolver;
import org.springframework.boot.autoconfigure.template.AbstractTemplateViewResolverProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import static org.springframework.util.Assert.isInstanceOf;

@EnableConfigurationProperties
@ConfigurationProperties(prefix = "jtwig")
public class JtwigProperties extends AbstractTemplateViewResolverProperties {
    static final String DEFAULT_PREFIX = "classpath:/templates/";
    static final String DEFAULT_SUFFIX = ".twig";

    public JtwigProperties() {
        super(DEFAULT_PREFIX, DEFAULT_SUFFIX);
        this.setCache(true);
    }

    @Override
    public void applyToMvcViewResolver(Object viewResolver) {
        super.applyToMvcViewResolver(viewResolver);
        isInstanceOf(JtwigViewResolver.class, viewResolver,
                "ViewResolver is not an instance of JtwigViewResolver :" + viewResolver);
    }

}
