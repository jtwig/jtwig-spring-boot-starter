package org.jtwig.spring.boot;


import org.jtwig.environment.EnvironmentConfiguration;
import org.jtwig.spring.JtwigViewResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(JtwigProperties.class)
@ConditionalOnWebApplication
public class JtwigAutoConfiguration {

    @Configuration
    protected static class JtwigViewResolverConfiguration {
        @Autowired
        private JtwigProperties properties;

        @Autowired(required = false)
        private EnvironmentConfiguration configuration;

        @Bean
        public JtwigViewResolver jtwigViewResolver() {
            JtwigViewResolver viewResolver = new JtwigViewResolver();
            if (configuration != null) {
                properties.setConfiguration(configuration);
            }
            properties.applyToViewResolver(viewResolver);
            return viewResolver;
        }
    }
}
