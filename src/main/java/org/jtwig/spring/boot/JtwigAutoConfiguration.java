package org.jtwig.spring.boot;


import org.jtwig.environment.EnvironmentConfigurationBuilder;
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
        private EnvironmentConfigurationBuilder configurationBuilder;

        @Bean
        public JtwigViewResolver jtwigViewResolver() {
            JtwigViewResolver viewResolver = new JtwigViewResolver();
            if (configurationBuilder != null) {
                properties.setConfigurationBuilder(configurationBuilder);
            }
            properties.applyToViewResolver(viewResolver);
            return viewResolver;
        }
    }
}
