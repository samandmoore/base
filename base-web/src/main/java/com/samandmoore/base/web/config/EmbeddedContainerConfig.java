package com.samandmoore.base.web.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * @author Sam Moore
 * @since 4/19/14 10:10 PM
 */
@Configuration
public class EmbeddedContainerConfig implements EmbeddedServletContainerCustomizer {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${user.friendly.errors}")
    private boolean showUserFriendlyError;

    @Override
    public void customize(final ConfigurableEmbeddedServletContainer container) {

        if (showUserFriendlyError) {
            logger.info("Enabling user-friendly error pages");

            container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404"));
            container.addErrorPages(new ErrorPage("/500"));
        } else {
            logger.info("Using developer-friendly error pages");
        }
    }
}
