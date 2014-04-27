package com.samandmoore.base.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.github.jknack.handlebars.HumanizeHelper;
import com.github.jknack.handlebars.springmvc.HandlebarsViewResolver;
import com.samandmoore.base.web.handlebars.CustomHandlebarsView;

/**
 * @author Sam Moore
 * @since 4/19/14 10:07 PM
 */
@Configuration
@ComponentScan("com.samandmoore")
@EnableAutoConfiguration
public class WebAppConfig extends WebMvcConfigurerAdapter {

    @Value("${views.caching.enabled}")
    private boolean cacheViews;

    @Bean
    public HandlebarsViewResolver viewResolver() {

        final HandlebarsViewResolver resolver = new HandlebarsViewResolver()
                .registerHelpers(HumanizeHelper.class)
                .withoutMessageHelper();

        resolver.setViewClass(CustomHandlebarsView.class);
        resolver.setPrefix("classpath:/views/");
        resolver.setSuffix(".html.hbs");
        resolver.setCache(cacheViews);
        resolver.setOrder(Ordered.HIGHEST_PRECEDENCE);

        return resolver;
    }

    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {

        registry.addViewController("/404");
        registry.addViewController("/500");
    }
}
