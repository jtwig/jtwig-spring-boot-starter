package org.jtwig.spring.boot;


import org.jtwig.spring.JtwigViewResolver;
import org.jtwig.spring.boot.config.JtwigViewResolverConfigurer;
import org.slf4j.Logger;
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
        private final static Logger LOGGER = org.slf4j.LoggerFactory.getLogger(JtwigAutoConfiguration.class);

        @Autowired
        private JtwigProperties properties;

        @Autowired(required = false)
        private JtwigViewResolverConfigurer configurer;

        @Bean
        public JtwigViewResolver jtwigViewResolver() {
            JtwigViewResolver viewResolver = new JtwigViewResolver();

            properties.applyToViewResolver(viewResolver);

            if (configurer != null) {
                LOGGER.info("Jtwig View Resolver configurer provided, applying custom configuration");
                configurer.configure(viewResolver);
            }

            LOGGER.info("Jtwig View Resolver bean added to the context");
            return viewResolver;
        }
    }
}
