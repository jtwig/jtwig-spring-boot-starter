package org.jtwig.spring.boot.config;

import org.jtwig.spring.JtwigViewResolver;

public interface JtwigViewResolverConfigurer {
    void configure (JtwigViewResolver viewResolver);
}
